package com.gkalogiros.mediasin;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface FileSigning {

    public DigitalSignature sign(final File file, final PrivateKey privateKey) throws Exception;

    public boolean verify(final File file, final PublicKey publicKey, final DigitalSignature digitalSignature) throws Exception;

}
