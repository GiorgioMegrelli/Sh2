package ge.sh2.command.sh2.help;

import ge.sh2.command.sh2.CommandsFilter;
import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;
import ge.sh2.core.object.command.CommandObject;
import ge.sh2.core.object.parameter.IParametersObject;
import ge.sh2.core.object.parameter.ParameterFieldWrapper;
import ge.sh2.utils.Strings;
import ge.sh2.utils.builder.StyledEasyStringBuilder;
import ge.sh2.utils.console.CommonStyles;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

@Command
public class Help implements CommandInvokable {

    @Parameters
    private HelpParameters parameters;

    private static void writeHeader(CommandObject command, StyledEasyStringBuilder sb) {
        String commandName = command.getName().toUpperCase();
        String commandDescription = command.getCommand().description();

        sb.plus(commandName, CommonStyles.BOLD);
        if(Strings.isBlank(commandDescription)) {
            sb.plus(" - No description", CommonStyles.LOW_ITALICS);
        } else {
            sb.plus(" - ", CommonStyles.ITALICS);
            sb.plus(commandDescription, CommonStyles.ITALICS);
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
                .plus("Parameters' field", CommonStyles.ITALICS)
                .newLine();
        sb.tab(2)
                .plus("Type: ", CommonStyles.ITALICS)
                .plus(field.getType().getName(), CommonStyles.ITALICS)
                .newLine();
        sb.tab(2)
                .plus("Name: ", CommonStyles.ITALICS)
                .many('\'', field.getName(), '\'')
                .newLine();

        if(parametersObject != null) {
            Stream<ParameterFieldWrapper> sortedValues = parametersObject
                    .getParameters()
                    .values()
                    .stream()
                    .sorted();

            sb.tab(2)
                    .plus("Values:", CommonStyles.ITALICS)
                    .newLine();
            sortedValues.forEach(wrapper -> {
                sb.tab(3).plus(wrapper.name, CommonStyles.ITALICS);
                if(wrapper.isRequired) {
                    sb.plus(" [Required]");
                }
                if(!Strings.isBlank(wrapper.description)) {
                    sb.plus(' ').plus(wrapper.description, CommonStyles.LOW_ITALICS);
                }
                sb.newLine();
            });
        }
    }

    private static void buildHelp(StyledEasyStringBuilder sb, List<CommandObject> commands) {
        for(CommandObject command: commands) {
            writeHeader(command, sb);
            writeParameters(command, sb);
            sb.newLine();
        }
    }

    private static void buildInternalHelp(StyledEasyStringBuilder sb, List<CommandObject> commands) {
        sb.plus("[[ Internal Commands: ]]", CommonStyles.BOLD).newLine();
        buildHelp(sb, commands);
    }

    private static void buildCustomHelp(StyledEasyStringBuilder sb, List<CommandObject> commands) {
        sb.plus("[[ Custom Commands: ]]", CommonStyles.BOLD).newLine();
        buildHelp(sb, commands);
    }

    @Override
    public void invoke() throws Exception {
        StyledEasyStringBuilder sb = new StyledEasyStringBuilder(1 << 10);
        if(parameters.getAll()) {
            buildInternalHelp(sb, CommandsFilter.getInternalCommands());
        }
        buildCustomHelp(sb, CommandsFilter.getCustomCommands());
        System.out.print(sb);
    }

}
