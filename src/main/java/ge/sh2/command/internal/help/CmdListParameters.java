package ge.sh2.command.internal.help;

import ge.sh2.core.annotation.ParameterField;

public class CmdListParameters {

    private boolean all = false;

    @ParameterField(name = "all")
    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

}
