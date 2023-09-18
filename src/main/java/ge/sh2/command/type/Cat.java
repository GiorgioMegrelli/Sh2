package ge.sh2.command.type;

import ge.sh2.command.CommandInvokable;
import ge.sh2.core.Sh2Context;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;
import ge.sh2.command.parameters.CatParameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Command
public class Cat implements CommandInvokable {

    private static final int BUFFER_SIZE = 1 << 8;

    @Parameters
    private CatParameters parameters;

    private void checkFile(File file) {
        if(!file.exists()) {
            throw new RuntimeException("File doesn't exist: " + file);
        } else if(file.isDirectory()) {
            throw new RuntimeException(file + " is directory");
        }
    }

    private void printFile(File file) throws IOException {
        try(InputStreamReader streamReader = new InputStreamReader(new FileInputStream(file))) {
            char[] data = new char[BUFFER_SIZE];
            while(true) {
                int readN = streamReader.read(data, 0, BUFFER_SIZE);
                if(readN == 0) break;
                if(readN < BUFFER_SIZE) {
                    data = Arrays.copyOf(data, readN);
                }
                Sh2Context.stdout().print(data);
                if(readN < BUFFER_SIZE) {
                    break;
                }
            }
        }
    }

    @Override
    public void invoke() throws Exception {
        String[] args = parameters.getArguments();
        if(args.length == 0) {
            throw new RuntimeException("No argument is provided");
        }

        File filePath = new File(args[0]);
        checkFile(filePath);
        printFile(filePath);
    }

}
