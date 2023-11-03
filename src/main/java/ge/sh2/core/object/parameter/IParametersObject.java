package ge.sh2.core.object.parameter;

import java.util.Map;

public interface IParametersObject {

    Object parse(String[] args) throws Exception;

    Map<String, ParameterFieldWrapper> getParameters();

}
