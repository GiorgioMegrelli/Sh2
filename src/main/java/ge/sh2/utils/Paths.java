package ge.sh2.utils;

import ge.sh2.utils.filter.IPathFilter;
import ge.sh2.utils.filter.TruePathFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Paths {

    private static final TruePathFilter TRUE_PATH_FILTER = new TruePathFilter();

    public static String pathSysJoin(String... subDirs) {
        return String.join(File.separator, subDirs);
    }

    public static List<File> walkFiles(File dir) {
        return walkFiles(dir, TRUE_PATH_FILTER);
    }

    public static List<File> walkFiles(File dir, IPathFilter filter) {
        List<File> files = new ArrayList<>();
        walkFilesRec(dir, filter, files);
        return files;
    }

    private static void walkFilesRec(File dir, IPathFilter filter, List<File> files) {
        File[] dirFiles = dir.listFiles();
        if(dirFiles != null) {
            for(File file: dirFiles) {
                if(file == null) {
                    continue;
                }
                if(file.isFile() && filter.accept(file)) {
                    files.add(file);
                } else if(file.isDirectory()) {
                    walkFilesRec(file, filter, files);
                }
            }
        }
    }

}
