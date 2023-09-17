package ge.sh2.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;

import static ge.sh2.utils.Paths.pathSysJoin;
import static ge.sh2.utils.Paths.walkFiles;

public class TestPaths {

    @Test
    public void testWalkFiles() {
        final String testRootDir = pathSysJoin("src", "test", "java");
        String root = testRootDir + File.separator;
        Set<String> foundFiles = walkFiles(new File(testRootDir))
                .stream()
                .map(File::toString)
                .map(file -> {
                    file = Strings.replaceStart(file, root);
                    file = file.replaceAll("\\.java", "");
                    file = Strings.replaceAll(file, File.separatorChar, Classes.PACKAGE_NAME_SEP);
                    return file;
                })
                .collect(Collectors.toUnmodifiableSet());

        Assertions.assertTrue(foundFiles.contains(TestPaths.class.getName()));
    }

}
