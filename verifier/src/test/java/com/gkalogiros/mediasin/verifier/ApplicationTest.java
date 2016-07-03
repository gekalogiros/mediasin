package com.gkalogiros.mediasin.verifier;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.stream.Collectors;

import static java.io.File.createTempFile;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;

public class ApplicationTest {

    @Test
    public void testVerifier() throws Exception {

        final File file = createTempFile("sign", "txt");
        file.deleteOnExit();

        final PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.write("Hello, World!");
        writer.close();

        final File publicKey = new File("src/test/resources/publickey");

        final File digitalSignature = new File("src/test/resources/digitalsignature");

        String[] args = new String[]{
                file.getAbsolutePath(),
                publicKey.getAbsolutePath(),
                digitalSignature.getAbsolutePath()
        };

        final File stdoutFile = createTempFile("stdout", "out");
        stdoutFile.deleteOnExit();

        final PrintStream stdout = System.out;

        System.setOut(new PrintStream(stdoutFile));

        Application.main(args);

        System.setOut(stdout);

        final String stdoutAsString = Files.lines(stdoutFile.toPath()).collect(Collectors.joining());

        assertThat(stdoutAsString, is("true"));
    }
}