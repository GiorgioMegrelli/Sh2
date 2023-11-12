package ge.sh2.command.custom.ls;

import ge.sh2.core.annotation.ParameterField;
import ge.sh2.core.command.ParametersWithArguments;

public class LsParameters extends ParametersWithArguments {

    private boolean detailed = false;
    private boolean absolute = false;
    private boolean contentSize = false;

    @ParameterField(name = "d", description = "Detailed information")
    public boolean isDetailed() {
        return detailed;
    }

    public void setDetails(boolean detailed) {
        this.detailed = detailed;
    }

    @ParameterField(name = "a", description = "Use absolute path")
    public boolean isAbsolute() {
        return absolute;
    }

    public void setAbsolute(boolean absolute) {
        this.absolute = absolute;
    }

    @ParameterField(name = "dirContent", description = "Print size of full content in directory")
    public boolean isContentSize() {
        return contentSize;
    }

    public void setContentSize(boolean contentSize) {
        this.contentSize = contentSize;
    }

}
