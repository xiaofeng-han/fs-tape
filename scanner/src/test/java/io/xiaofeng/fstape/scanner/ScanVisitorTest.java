package io.xiaofeng.fstape.scanner;

import io.xiaofeng.fstape.common.module.Entry;
import io.xiaofeng.fstape.scanner.ScanVisitor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ScanVisitorTest {
    @Test
    public void print() throws IOException {
        Path tmpDir = Paths.get(System.getProperty("java.io.tmpdir"));
        Entry root = Entry.entryBuilder().current(tmpDir).isFolder(true).build();
        Files.walkFileTree(tmpDir, ScanVisitor.builder().root(root).build());
        printEntry(root);
    }

    void printEntry(Entry entry) {
        System.out.println(entry.getCurrent() + ":(" + entry.isFolder() + ")");
        List<Entry> entries = entry.getEntries();
        if (entries != null && entries.size() > 0) {
            System.out.println("Under " + entry.getCurrent());
            entries.forEach(this::printEntry);
        }
    }
}
