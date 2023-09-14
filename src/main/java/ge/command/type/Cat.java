package ge.command.type;

import ge.command.CommandInvokable;
import ge.core.annotation.Command;
import ge.core.annotation.Parameters;
import ge.core.parameters.CatParameters;

@Command
public class Cat implements CommandInvokable {

    @Parameters
    private CatParameters parameters;

    @Override
    public void invoke() {
        System.out.println(parameters);
    }

}
