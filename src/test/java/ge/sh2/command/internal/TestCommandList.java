package ge.sh2.command.internal;

import ge.sh2.command.internal.help.CommandList;
import ge.sh2.command.internal.help.HelpParameters;
import ge.sh2.core.annotation.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ge.sh2.command.CommandHelper.setParameters;

public class TestCommandList extends AbstractCommandTest {

    private static final String CMD_LIST_NAME = "cmd-list";
    private static final String HELP_NAME = "help";

    @Test
    public void testStructure() {
        Command command = CommandList.class.getAnnotation(Command.class);

        Assertions.assertNotNull(command);

        Assertions.assertEquals(CMD_LIST_NAME, command.name());
        Assertions.assertTrue(command.description().isEmpty());

        Assertions.assertTrue(hasCommandInvokable(CommandList.class));
    }

    @Test
    public void test() throws Exception {
        CommandList help = new CommandList();

        Assertions.assertThrowsExactly(NullPointerException.class, help::invoke);

        Assertions.assertTrue(setParameters(help, new HelpParameters()));

        help.invoke();

        String helpString = getTestIO().toString().toLowerCase();
        Assertions.assertFalse(helpString.contains(CMD_LIST_NAME));
        Assertions.assertFalse(helpString.contains(HELP_NAME));
    }

    @Test
    public void testAll() throws Exception {
        HelpParameters parameters = new HelpParameters();
        CommandList help = new CommandList();

        Assertions.assertThrowsExactly(NullPointerException.class, help::invoke);

        Assertions.assertTrue(setParameters(help, parameters));

        parameters.setAll(true);
        help.invoke();

        String helpString = getTestIO().toString().toLowerCase();
        Assertions.assertTrue(helpString.contains(CMD_LIST_NAME));
        Assertions.assertTrue(helpString.contains(HELP_NAME));
    }

}
