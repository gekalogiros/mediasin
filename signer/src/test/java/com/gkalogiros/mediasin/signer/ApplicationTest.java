package com.gkalogiros.mediasin.signer;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import static java.io.File.createTempFile;
import static java.io.File.separator;
import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ApplicationTest {

    @Test
    public void testApplication() throws Exception {

        final File file = createTempFile("sign", "txt");
        file.deleteOnExit();

        final PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.write("Hello, World!");
        writer.close();

        final String[] args = new String[]{
                file.getAbsolutePath()
        };

        Application.main(args);

        final String userDirectory = System.getProperty("user.dir");

        final File privateKey = new File(format("%s%s%s", userDirectory, separator, "privatekey"));
        privateKey.deleteOnExit();

        final File publicKey = new File(format("%s%s%s", userDirectory, separator, "publickey"));
        publicKey.deleteOnExit();

        final File signature = new File(format("%s%s%s", userDirectory, separator, "digitalsignature"));
        signature.deleteOnExit();

        assertThat(privateKey.exists(), is(true));

        assertThat(publicKey.exists(), is(true));

        assertThat(signature.exists(), is(true));
    }
}