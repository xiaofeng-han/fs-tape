package io.xiaofeng.fstape.scanner;

import picocli.CommandLine;

import java.nio.file.Path;

public class ScannerOptions {
    @CommandLine.Parameters
    Path rootFolder;
}
