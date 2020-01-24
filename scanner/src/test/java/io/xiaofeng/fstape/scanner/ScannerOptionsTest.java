package io.xiaofeng.fstape.scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.nio.file.Files;

public class ScannerOptionsTest {
    ScannerOptions options;
    CommandLine commandLine;

    @BeforeEach
    public void setup() {
        options = new ScannerOptions();
        commandLine = new CommandLine(options);
    }

    @Test
    public void rootFolder() {
        final String[] args = {"c:\\"};
        commandLine.parseArgs(args);
        System.out.println(options.rootFolder);
        System.out.println(Files.exists(options.rootFolder));
    }
}
