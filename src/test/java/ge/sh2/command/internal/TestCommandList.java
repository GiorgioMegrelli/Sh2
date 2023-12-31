package ge.sh2.command.internal;

import ge.sh2.command.internal.help.CmdListParameters;
import ge.sh2.command.internal.help.CommandList;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.context.Sh2Context;
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
        Sh2Context.IO().use((io) -> {
            io.setOut(testIO);

            CommandList help = new CommandList();

            Assertions.assertThrowsExactly(NullPointerException.class, help::invoke);

            Assertions.assertTrue(setParameters(help, new CmdListParameters()));

            help.invoke();

            String helpString = testIO.toString().toLowerCase();
            Assertions.assertFalse(helpString.contains(CMD_LIST_NAME));
            Assertions.assertFalse(helpString.contains(HELP_NAME));
        });
    }

    @Test
    public void testAll() throws Exception {
        Sh2Context.IO().use((io) -> {
            io.setOut(testIO);

            CmdListParameters parameters = new CmdListParameters();
            CommandList help = new CommandList();

            Assertions.assertThrowsExactly(NullPointerException.class, help::invoke);

            Assertions.assertTrue(setParameters(help, parameters));

            parameters.setAll(true);
            help.invoke();

            String helpString = testIO.toString().toLowerCase();
            Assertions.assertTrue(helpString.contains(CMD_LIST_NAME));
            Assertions.assertTrue(helpString.contains(HELP_NAME));
        });
    }

}
