package ge.utils.filter;

import java.io.File;

public class TruePathFilter implements IPathFilter {
    @Override
    public boolean accept(File file) {
        return true;
    }
}
