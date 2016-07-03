package com.gkalogiros.mediasin.verifier;

import com.gkalogiros.mediasin.*;

import java.io.File;
import java.security.PublicKey;

import static com.google.common.base.Preconditions.checkArgument;

public class Verifier {

    private final SecurityAlgorithm signatureGenerationAlgorithm;

    private final SecurityProvider securityProvider;

    public Verifier(final SecurityAlgorithm signatureGenerationAlgorithm,
                    final SecurityProvider securityProvider) {
        checkArgument((this.signatureGenerationAlgorithm = signatureGenerationAlgorithm) != null);
        checkArgument((this.securityProvider = securityProvider) != null);
    }

    public boolean verify(final File file, final PublicKey publicKey, final DigitalSignature digitalSignature) throws Exception {
        final FileSigning signing = new FileSigningImpl(signatureGenerationAlgorithm, securityProvider);
        return signing.verify(file, publicKey, digitalSignature);
    }

    public static VerifierBuilder builder() {
        return new VerifierBuilder();
    }
}
