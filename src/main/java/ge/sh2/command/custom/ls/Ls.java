package ge.sh2.command.custom.ls;

import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;
import ge.sh2.core.console.io.SlowStandardInputOutput;
import ge.sh2.core.console.io.SlowStandardInputOutputFactory;
import ge.sh2.core.console.style.CommonStyles;
import ge.sh2.core.console.style.ConsoleStyle;
import ge.sh2.utils.Pair;
import ge.sh2.utils.Strings;
import ge.sh2.utils.builder.ReadableBytesFormatter;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Command(description = "Lists files in a directory")
public class Ls implements CommandInvokable {

    private static final int FMT_BYTES_SIZE = 8;
    private static final ReadableBytesFormatter BYTES_FORMATTER = new ReadableBytesFormatter();

    @Parameters
    private LsParameters parameters;

    @Override
    public void invoke() throws Exception {
        String[] arguments = parameters.getArguments();
        String root;
        if(arguments == null || arguments.length == 0) {
            root = ".";
        } else {
            root = arguments[0];
        }

        File[] childFiles = new File(root).listFiles();
        if(childFiles == null || childFiles.length == 0) {
            return;
        }

        SlowStandardInputOutput io = new SlowStandardInputOutputFactory().build();

        List<Pair<String, ExtendedFile>> files = Arrays.stream(childFiles)
                .map(ExtendedFile::new)
                .map(file -> {
                    String name = (parameters.isAbsolute())? file.fullName(): file.name();
                    return new Pair<>(name, file);
                })
                .toList();

        int maxLength = -1;
        if(parameters.isDetailed()) {
            maxLength = files.stream()
                    .map(pair -> pair.getFirst().length())
                    .max(Integer::compareTo)
                    .orElse(0);
        }
        maxLength += (parameters.isAbsolute())? 2: 4;

        StringBuilder columns = new StringBuilder();
        columns.append("Name");
        if(parameters.isDetailed()) {
            columns.append(" ".repeat(maxLength - 5));
            columns.append("Type");
            columns.append(" ".repeat(6));
            columns.append("Size    Mode");
            if(parameters.isContentSize()) {
                columns.append("  Content_Size");
            }
        }
        io.println(ConsoleStyle.stylize(columns.toString(), CommonStyles.BOLD));

        for(Pair<String, ExtendedFile> entry: files) {
            String name = entry.getFirst();
            ExtendedFile file = entry.getSecond();
            io.print(name);
            if(parameters.isDetailed()) {
                String fmtSize = BYTES_FORMATTER.format(file.size());
                io.print(" ".repeat(maxLength - name.length()));
                io.print("   ");
                io.print((file.isFile())? 'F': 'D');
                io.print(" ");
                io.print(
                        Strings.padStart(fmtSize, " ", FMT_BYTES_SIZE)
                );
                io.print("  ");
                io.print(file.modeString());
                if(file.isDirectory() && parameters.isContentSize()) {
                    String fmtRecSize = BYTES_FORMATTER.format(file.recursiveSize());
                    io.print(
                            Strings.padStart(fmtRecSize, " ", FMT_BYTES_SIZE + 6)
                    );
                }
            }
            io.println();
        }
    }

}
