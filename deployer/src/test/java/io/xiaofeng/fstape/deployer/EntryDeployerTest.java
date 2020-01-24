package io.xiaofeng.fstape.deployer;

import com.google.common.collect.ImmutableList;
import io.xiaofeng.fstape.common.module.Entry;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

public class EntryDeployerTest {

    @TempDir
    Path sourceDir;

    @TempDir
    Path destDir;

    @Test
    @SneakyThrows
    public void tempDeploy() {
        Entry firstLayerFile1 = Entry.Builder().current(Path.of("layer1_file1.txt")).isFolder(false).build();

        Entry secondLayerFile1 = Entry.Builder().current(Path.of("layer2_file1.txt")).isFolder(false).build();
        Entry subFolder = Entry.Builder().current(Path.of("sub-folder")).isFolder(true).entries(ImmutableList.of(secondLayerFile1)).build();

        Entry root = Entry.Builder().current(Path.of("root")).isFolder(true).entries(ImmutableList.of(firstLayerFile1, subFolder)).build();
        new EntryDeployer().deploy(destDir, root);
        Files.walk(destDir).forEach(System.out::println);
    }
}
