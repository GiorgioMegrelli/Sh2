package ge.sh2.command.custom.cat;

import ge.sh2.core.annotation.ParameterField;
import ge.sh2.core.command.ParametersWithArguments;

public class CatParameters extends ParametersWithArguments {

    private boolean isSize;

    @ParameterField(name = "size")
    public boolean getSize() {
        return isSize;
    }

    public void setSize(boolean size) {
        this.isSize = size;
    }

}
