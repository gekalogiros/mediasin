package com.gkalogiros.mediasin;

import java.io.File;
import java.io.FileInputStream;

public class FileToBytes {

    private FileToBytes() {}

    public static byte[] convert(final File file) throws Exception {
        final FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[fileInputStream.available()];
        fileInputStream.read(data);
        fileInputStream.close();
        return data;
    }

}
