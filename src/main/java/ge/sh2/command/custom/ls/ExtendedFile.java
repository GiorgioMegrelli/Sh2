package ge.sh2.command.custom.ls;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class ExtendedFile {

    private final File file;

    public ExtendedFile(File file) {
        this.file = file;
    }

    public boolean isFile() {
        return file.isFile();
    }

    public boolean isDirectory() {
        return file.isDirectory();
    }

    public long size() throws IOException {
        return Files.size(file.toPath());
    }

    public long recursiveSize() throws IOException {
        if(file.isFile()) {
            return -1;
        }
        return recursiveSize(file);
    }

    public String name() {
        return file.getName();
    }

    public String fullName() {
        return FilenameUtils.normalize(file.getAbsolutePath());
    }

    public String modeString() {
        return (file.isHidden() ? 'H' : ' ') +
                "  " +
                (file.canRead() ? 'R' : ' ') +
                (file.canWrite() ? 'W' : ' ') +
                (file.canExecute() ? 'E' : ' ');
    }

    private static long recursiveSize(File root) throws IOException {
        long result = 0;
        File[] files = root.listFiles();
        if(files != null) {
            for(File f: files) {
                if(f.isDirectory()) {
                    result += recursiveSize(f);
                } else {
                    result += Files.size(f.toPath());
                }
            }
        }
        return result;
    }

}
