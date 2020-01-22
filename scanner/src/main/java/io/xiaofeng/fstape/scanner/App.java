package io.xiaofeng.fstape.scanner;

import io.xiaofeng.fstape.common.module.Entry;

import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Entry entry = Entry.entryBuilder().current(Paths.get(System.getProperty("java.io.tmpdir"))).isFolder(false).build();
        System.out.println(entry);
    }
}
