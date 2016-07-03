package com.gkalogiros.mediasin.verifier;

import com.gkalogiros.mediasin.SecurityAlgorithm;
import com.gkalogiros.mediasin.SecurityProvider;

public class VerifierBuilder {

    private SecurityAlgorithm signatureGenerationAlgorithm;
    private SecurityProvider securityProvider;

    protected VerifierBuilder() {}

    public VerifierBuilder withSignatureGenerationAlgorithm(final SecurityAlgorithm signatureGenerationAlgorithm) {
        this.signatureGenerationAlgorithm = signatureGenerationAlgorithm;
        return this;
    }

    public VerifierBuilder withSecurityProvider(final SecurityProvider securityProvider) {
        this.securityProvider = securityProvider;
        return this;
    }

    public Verifier build() {
        return new Verifier(signatureGenerationAlgorithm, securityProvider);
    }

}
