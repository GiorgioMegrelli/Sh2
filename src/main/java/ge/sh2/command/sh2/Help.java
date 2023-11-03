package ge.sh2.command.sh2;

import ge.sh2.command.CommandInvokable;
import ge.sh2.core.Sh2Context;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.object.command.CommandObject;
import ge.sh2.core.object.parameter.IParametersObject;
import ge.sh2.core.object.parameter.ParameterFieldWrapper;
import ge.sh2.utils.Strings;
import ge.sh2.utils.builder.StyledEasyStringBuilder;
import ge.sh2.utils.console.ConsoleStyle;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

@Command
public class Help implements CommandInvokable {

    private static final ConsoleStyle.Describer[] STYLE_BOLD = {
            ConsoleStyle.FontType.BOLD,
    };
    private static final ConsoleStyle.Describer[] STYLE_ITALICS = {
            ConsoleStyle.FontType.ITALICS,
    };
    private static final ConsoleStyle.Describer[] STYLE_LOW = {
            ConsoleStyle.FontType.ITALICS,
            ConsoleStyle.FontType.LOW,
    };

    private static List<CommandObject> getCustomCommands() {
        return Sh2Context.getCommands()
                .getAll()
                .stream()
                .filter(CommandObject::isCustomCommand)
                .toList();
    }

    private static void writeHeader(CommandObject command, StyledEasyStringBuilder sb) {
        String commandName = command.getName().toUpperCase();
        String commandDescription = command.getCommand().description();

        sb.plus(commandName, STYLE_BOLD);
        if(Strings.isBlank(commandDescription)) {
            sb.plus(" - No description", STYLE_LOW);
        } else {
            sb.plus(" - ", STYLE_ITALICS);
            sb.plus(commandDescription, STYLE_ITALICS);
        }
        sb.newLine();
    }

    private static void writeParameters(CommandObject command, StyledEasyStringBuilder sb) {
        Field field = command.getParametersField();
        IParametersObject parametersObject = null;
        try {
            parametersObject = command.getParametersObject();
        } catch (Exception ignored) {}

        sb.tab()
                .plus("Parameters' field", STYLE_ITALICS)
                .newLine();
        sb.tab(2)
                .plus("Type: ", STYLE_ITALICS)
                .plus(field.getType().getName(), STYLE_ITALICS)
                .newLine();
        sb.tab(2)
                .plus("Name: ", STYLE_ITALICS)
                .many('\'', field.getName(), '\'')
                .newLine();

        if(parametersObject != null) {
            Stream<ParameterFieldWrapper> sortedValues = parametersObject
                    .getParameters()
                    .values()
                    .stream()
                    .sorted();

            sb.tab(2)
                    .plus("Values:", STYLE_ITALICS)
                    .newLine();
            sortedValues.forEach(wrapper -> {
                sb.tab(3).plus(wrapper.name, STYLE_ITALICS);
                if(wrapper.isRequired) {
                    sb.plus(" [Required]");
                }
                if(!Strings.isBlank(wrapper.description)) {
                    sb.plus(' ').plus(wrapper.description, STYLE_LOW);
                }
                sb.newLine();
            });
        }
    }

    private static String buildHelp() {
        StyledEasyStringBuilder sb = new StyledEasyStringBuilder(1 << 10);

        for(CommandObject command: getCustomCommands()) {
            writeHeader(command, sb);
            writeParameters(command, sb);
            sb.newLine();
        }

        return sb.toString();
    }

    @Override
    public void invoke() throws Exception {
        System.out.print(buildHelp());
    }

}
