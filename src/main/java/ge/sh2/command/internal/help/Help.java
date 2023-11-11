package ge.sh2.command.internal.help;

import ge.sh2.core.Sh2Context;
import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;
import ge.sh2.core.object.command.CommandObject;
import ge.sh2.core.object.parameter.IParametersObject;
import ge.sh2.core.object.parameter.ParameterFieldWrapper;
import ge.sh2.utils.Strings;
import ge.sh2.utils.builder.StyledEasyStringBuilder;
import ge.sh2.core.console.style.CommonStyles;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

@Command(description = "Prints information about commands")
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

        if(field == null) {
            sb.tab()
                .plus("Parameters' field doesn't exist", CommonStyles.ITALICS)
                .newLine();
            return;
        }

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
                if(wrapper.isRequired || !Strings.isBlank(wrapper.description)) {
                    sb.plus(" -", CommonStyles.LOW_ITALICS);
                }
                if(wrapper.isRequired) {
                    sb.plus(" [Required]", CommonStyles.LOW_ITALICS);
                }
                if(!Strings.isBlank(wrapper.description)) {
                    sb.plus(' ').plus(wrapper.description, CommonStyles.LOW_ITALICS);
                }
                sb.newLine();
            });
        }
    }

    private static void buildHelp(StyledEasyStringBuilder sb, CommandObject command) {
        writeHeader(command, sb);
        writeParameters(command, sb);
        sb.newLine();
    }

    private static void buildHelp(StyledEasyStringBuilder sb, List<CommandObject> commands) {
        for(CommandObject command: commands) {
            buildHelp(sb, command);
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
        String[] arguments = parameters.getArguments();
        if(arguments == null || arguments.length == 0) {
            if(parameters.getAll()) {
                buildInternalHelp(sb, Sh2Context.getCommands().getInternals());
            }
            buildCustomHelp(sb, Sh2Context.getCommands().getCustoms());
        } else {
            for(String arg: arguments) {
                CommandObject cmd = Sh2Context.getCommands().get(arg);
                if(cmd != null) {
                    buildHelp(sb, cmd);
                }
            }
        }
        Sh2Context.getIO().print(sb);
    }

}
