package ge.sh2.command.type;

import ge.sh2.command.CommandInvokable;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;
import ge.sh2.core.parameters.CatParameters;

@Command
public class Cat implements CommandInvokable {

    @Parameters
    private CatParameters parameters;

    @Override
    public void invoke() {
        System.out.println(parameters);
    }

}
