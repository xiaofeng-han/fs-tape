package io.xiaofeng.fstape.common.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Builder(builderMethodName = "entryBuilder")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entry {
    private Path current;
    private boolean isFolder;
    @Builder.Default
    private List<Entry> entries = new ArrayList<>();
}
