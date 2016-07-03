package com.gkalogiros.mediasin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.*;

public class FileSigningImpl implements FileSigning {

    private final SecurityAlgorithm securityAlgorithm;
    private final SecurityProvider securityProvider;

    public FileSigningImpl(SecurityAlgorithm securityAlgorithm, SecurityProvider securityProvider) {
        this.securityAlgorithm = securityAlgorithm;
        this.securityProvider = securityProvider;
    }

    public DigitalSignature sign(final File file, PrivateKey privateKey) throws Exception {

        final Signature signature = getSignature();

        signature.initSign(privateKey);

        applySignature(file, signature);

        return new DigitalSignature(signature.sign());
    }

    public boolean verify(final File file, final PublicKey publicKey, final DigitalSignature digitalSignature) throws Exception {

        final Signature signature = getSignature();

        signature.initVerify(publicKey);

        applySignature(file, signature);

        return signature.verify(digitalSignature.get());
    }

    private void applySignature(final File file, final Signature signature) throws Exception {

        final FileInputStream fileInputStream = new FileInputStream(file);

        final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        byte[] buffer = new byte[1024];

        int len;

        while ((len = bufferedInputStream.read(buffer)) >= 0) {
            signature.update(buffer, 0, len);
        }

        bufferedInputStream.close();
    }

    private Signature getSignature() throws Exception {
        return Signature.getInstance(algorithm(), provider());
    }

    private String algorithm() {
        return this.securityAlgorithm.getKey();
    }

    private String provider() {
        return this.securityProvider.getKey();
    }
}
