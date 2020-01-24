package io.xiaofeng.fstape.scanner;

import io.xiaofeng.fstape.common.module.Entry;
import lombok.Builder;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Stack;

@Builder
public class ScanVisitor extends SimpleFileVisitor<Path> {
    Entry root;

    @Builder.Default
    Stack<Entry> entries = new Stack<>();

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        FileVisitResult result = super.preVisitDirectory(dir, attrs);
        // if determined should not continue, skip
        if (!FileVisitResult.CONTINUE.equals(result)) {
           return result;
        }

        if (Files.isWritable(dir)) {
            result = FileVisitResult.CONTINUE;
        } else {
            result = FileVisitResult.SKIP_SUBTREE;
        }

        if (FileVisitResult.CONTINUE.equals(result)) {
            if (Files.isSameFile(dir, root.getCurrent())) {
                entries.push(root);
            } else {
                if (entries.isEmpty()) {
                    return FileVisitResult.TERMINATE;
                }

                Entry entry = entries.pop();
                Entry self = Entry.entryBuilder().current(dir.getFileName()).isFolder(Files.isDirectory(dir)).build();
                entry.getEntries().add(self);
                entries.push(entry);
                entries.push(self);
            }
        }
        return result;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileVisitResult result = super.visitFile(file, attrs);
        if (FileVisitResult.CONTINUE.equals(result)) {
            Entry entry = entries.pop();
            entry.getEntries().add(Entry.entryBuilder().current(file.getFileName()).build());
            entries.push(entry);
        }
        return result;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        FileVisitResult result = super.postVisitDirectory(dir, exc);
        if (FileVisitResult.CONTINUE.equals(result)) {
            entries.pop();
        }

        return result;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.SKIP_SUBTREE;
    }
}
