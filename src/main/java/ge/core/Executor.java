package ge.core;

import ge.core.command.CommandLoader;

public class Executor {

    private static final String PACKAGE_NAME = "ge.command.type";

    public void execute(String[] args) throws Exception {
        CommandLoader.loadValidCommands(PACKAGE_NAME);
    }

}
