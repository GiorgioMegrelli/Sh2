package ge.core.parameters;

import ge.core.annotation.ParameterField;

public class CatParameters {

    private boolean isSize;
    private String[] arguments;

    @ParameterField(name = "size")
    public boolean getSize() {
        return isSize;
    }

    public void setSize(boolean size) {
        this.isSize = size;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

}
