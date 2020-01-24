package io.xiaofeng.fstape.scanner;

import io.xiaofeng.fstape.common.module.Entry;
import picocli.CommandLine;

import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class Scanner
{
    public static void main( String[] args )
    {
        ScannerOptions options = new ScannerOptions();
        new CommandLine(options).parseArgs(args);
    }
}
