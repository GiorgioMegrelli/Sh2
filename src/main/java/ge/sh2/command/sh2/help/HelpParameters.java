package ge.sh2.command.sh2.help;

import ge.sh2.core.annotation.ParameterField;

public class HelpParameters {

    private boolean all = false;

    @ParameterField(name = "all")
    public boolean getAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

}
