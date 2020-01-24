package io.xiaofeng.fstape.deployer;

import io.xiaofeng.fstape.common.module.Entry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class EntryDeployer {
    public void deploy(Path target, Entry root) {
        deploy(target, root.getEntries());
    }

    private void deploy(Path folder, List<Entry> entries) {
        if (!Files.exists(folder)) {
            try {
                Files.createDirectory(folder);
                log.info("deployed folder {}", folder);
            } catch (IOException e) {
                log.error("Create directory ({}) failed.", e.getMessage());
                return;
            }
        }

        if (CollectionUtils.isNotEmpty(entries)) {
            entries.forEach(entry -> {
                final Path child = folder.resolve(entry.getCurrent());
                try {
                    if (entry.isFolder()) {
                        deploy(child, entry.getEntries());
                    } else {
                        Files.createFile(child);
                        log.info("deployed file {}", entry.getCurrent());
                    }
                } catch (IOException e) {
                    log.error("Create file failed. folder={}, file={}", folder, entry.getCurrent());
                }
            });
        }

    }
}
