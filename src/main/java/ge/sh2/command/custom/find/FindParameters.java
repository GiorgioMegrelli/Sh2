package ge.sh2.command.custom.find;

import ge.sh2.core.annotation.ParameterField;
import ge.sh2.core.command.ParametersWithArguments;

public class FindParameters extends ParametersWithArguments {

    private String filePattern = null;

    @ParameterField(name = "p", description = "Pattern to find. Try to pass pattern with equation mark")
    public String getFilePattern() {
        return filePattern;
    }

    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }

}
