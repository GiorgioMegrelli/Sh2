package ge.sh2.command.internal.help;

import ge.sh2.core.annotation.ParameterField;
import ge.sh2.core.command.ParametersWithArguments;

public class HelpParameters extends ParametersWithArguments {

    private boolean all = false;

    @ParameterField(name = "all")
    public boolean getAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

}
