package ge.sh2.core.object.parameter;

import ge.sh2.core.annotation.ParameterField;
import ge.sh2.core.parameters.GetterAndSetter;
import ge.sh2.core.parameters.ParametersUtils;
import ge.sh2.utils.MethodUtils;
import ge.sh2.utils.Types;
import ge.sh2.exception.ParsingException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static ge.sh2.core.parameters.ParametersUtils.GETTER_ARGUMENT_NAME;
import static ge.sh2.utils.Strings.*;

public class DefaultParametersObject implements IParametersObject {

    private static final CommandLineParser PARSER = new DefaultParser();

    private final Map<String, ParameterFieldWrapper> parameterFields = new HashMap<>();
    private Method argumentSetter = null;
    private final Constructor<?> constructor;

    public <T> DefaultParametersObject(Field field) throws Exception {
        Class<?> optionsType = field.getType();
        Map<String, GetterAndSetter> getterAndSetters = ParametersUtils.findValidGettersSetters(optionsType);
        for(GetterAndSetter getterAndSetter: getterAndSetters.values()) {
            Method getter = getterAndSetter.getter;
            Method setter = getterAndSetter.setter;

            if(getter.getName().equals(GETTER_ARGUMENT_NAME)) {
                argumentSetter = setter;
                continue;
            }

            ParameterField parameterField = getter.getAnnotation(ParameterField.class);
            String fieldName;
            if(isBlank(parameterField.name())) {
                fieldName = camelCaseToSnakeCase(
                        replaceStart(getter.getName(), MethodUtils.GET_PREFIX), '-'
                );
            } else {
                fieldName = parameterField.name();
            }

            ParameterFieldWrapper fieldWrapper = new ParameterFieldWrapper(
                    fieldName, parameterField.description(), getter.getReturnType(),
                    getter, setter, parameterField.isRequired()
            );
            parameterFields.put(fieldName, fieldWrapper);
        }
        constructor = optionsType.getDeclaredConstructor();
    }

    @Override
    public boolean hasArguments() {
        return argumentSetter != null;
    }

    @Override
    public Map<String, ParameterFieldWrapper> getParameters() {
        return parameterFields;
    }

    private Options getOptions() {
        Options options = new Options();
        parameterFields.forEach((name, wrapper) -> {
            options.addOption(
                    name, name, !boolean.class.equals(wrapper.type), wrapper.description
            );
        });
        return options;
    }

    private void checkRequiredOpts(CommandLine cmd) throws Exception {
        Set<String> optionsNames = Arrays.stream(cmd.getOptions())
                .map(Option::getArgName)
                .collect(Collectors.toSet());
        Set<String> requiredNames = parameterFields.values()
                .stream()
                .filter(w -> w.isRequired)
                .map(w -> w.name)
                .collect(Collectors.toUnmodifiableSet());
        for(String required: requiredNames) {
            if(!optionsNames.contains(required)) {
                throw new ParsingException("Missed required option: " + required);
            }
        }
    }

    private Map<String, Option> asMap(CommandLine cmd) {
        Map<String, Option> map = new HashMap<>();
        for(Option option: cmd.getOptions()) {
            map.put(option.getOpt(), option);
        }
        return map;
    }

    @Override
    public Object parse(String[] args) throws Exception {
        CommandLine cmd = PARSER.parse(getOptions(), args);
        checkRequiredOpts(cmd);
        Object params = constructor.newInstance();

        if(argumentSetter != null) {
            String[] freeArgs = cmd.getArgs();
            argumentSetter.invoke(params, (Object) freeArgs);
        }

        Map<String, Option> map = asMap(cmd);
        for(String name: parameterFields.keySet()) {
            ParameterFieldWrapper wrapper = parameterFields.get(name);
            if(boolean.class.equals(wrapper.type)) {
                wrapper.setter.invoke(params, map.containsKey(name));
            } else {
                if(map.containsKey(name)) {
                    String value = map.get(name).getValue();
                    wrapper.setter.invoke(params, Types.toType(value, wrapper.type));
                }
            }
        }

        return params;
    }

}
