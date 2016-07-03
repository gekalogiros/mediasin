package com.gkalogiros.mediasin;

import java.io.File;
import java.io.FileOutputStream;

public class SecurityContext {

    private final File file;
    private final byte[] data;

    public SecurityContext(final String filename, final byte[] data) {
        this.file = new File(filename);
        this.data = data;
    }

    public File save() throws Exception {
        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();

        return file;
    }
}
