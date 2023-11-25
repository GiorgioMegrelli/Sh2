package ge.sh2.command.custom.find;

import ge.sh2.core.context.Sh2Context;
import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.util.Collection;

@Command
public class Find implements CommandInvokable {

    @Parameters
    private FindParameters parameters;

    private IOFileFilter getFileFilter() {
        String pattern = parameters.getFilePattern();
        if(pattern == null) {
            return TrueFileFilter.INSTANCE;
        }
        return WildcardFileFilter.builder().setWildcards(pattern).get();
    }

    @Override
    public void invoke() throws Exception {
        String[] arguments = parameters.getArguments();
        if(arguments == null || arguments.length == 0) {
            Sh2Context.IO().err().println("No Arguments");
            return;
        }

        File root = new File(arguments[0]);
        Collection<File> files = FileUtils.listFiles(root, getFileFilter(), TrueFileFilter.INSTANCE);
        for(File file: files) {
            String absName = FilenameUtils.normalize(file.getAbsolutePath());
            Sh2Context.IO().err().println(absName);
        }
    }

}
