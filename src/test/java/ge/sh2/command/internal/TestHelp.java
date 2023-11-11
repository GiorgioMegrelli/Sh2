package ge.sh2.command.internal;

import ge.sh2.command.internal.help.Help;
import ge.sh2.command.internal.help.HelpParameters;
import ge.sh2.core.annotation.Command;
import ge.sh2.utils.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ge.sh2.command.CommandHelper.setParameters;

public class TestHelp extends AbstractCommandTest {

    private static final String CMD_LIST_NAME = "cmd-list";
    private static final String HELP_NAME = "help";
    private static final String HELP_PARAMS_FULL_NAME;

    static {
        HELP_PARAMS_FULL_NAME = HelpParameters.class.getName().toLowerCase();
    }

    @Test
    public void testStructure() {
        Command command = Help.class.getAnnotation(Command.class);

        Assertions.assertNotNull(command);

        Assertions.assertTrue(command.name().isEmpty());
        Assertions.assertFalse(command.description().isEmpty());

        Assertions.assertTrue(hasCommandInvokable(Help.class));
    }

    @Test
    public void test() throws Exception {
        Help help = new Help();

        Assertions.assertThrowsExactly(NullPointerException.class, help::invoke);

        Assertions.assertTrue(setParameters(help, new HelpParameters()));

        help.invoke();

        String helpString = getTestIO().toString().toLowerCase();
        Assertions.assertFalse(helpString.contains(CMD_LIST_NAME));
        Assertions.assertFalse(helpString.contains(HELP_NAME));
        Assertions.assertEquals(0, Strings.countMatches(helpString, HELP_PARAMS_FULL_NAME));
    }

    @Test
    public void testAll() throws Exception {
        HelpParameters parameters = new HelpParameters();
        Help help = new Help();

        Assertions.assertThrowsExactly(NullPointerException.class, help::invoke);

        Assertions.assertTrue(setParameters(help, parameters));

        parameters.setAll(true);
        help.invoke();

        String helpString = getTestIO().toString().toLowerCase();
        Assertions.assertTrue(helpString.contains(CMD_LIST_NAME));
        Assertions.assertTrue(helpString.contains(HELP_NAME));
        Assertions.assertEquals(2, Strings.countMatches(helpString, HELP_PARAMS_FULL_NAME));
    }

}
