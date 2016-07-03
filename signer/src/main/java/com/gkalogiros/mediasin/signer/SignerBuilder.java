package com.gkalogiros.mediasin.signer;

import com.gkalogiros.mediasin.SecurityAlgorithm;
import com.gkalogiros.mediasin.SecurityProvider;

public class SignerBuilder {

    private SecurityAlgorithm keyGenerationAlgorithm;
    private SecurityAlgorithm signatureGenerationAlgorithm;
    private SecurityProvider securityProvider;

    protected SignerBuilder() {}

    public SignerBuilder withKeyGenerationAlgorithm(final SecurityAlgorithm keyGenerationAlgorithm) {
        this.keyGenerationAlgorithm = keyGenerationAlgorithm;
        return this;
    }

    public SignerBuilder withSignatureGenerationAlgorithm(final SecurityAlgorithm signatureGenerationAlgorithm) {
        this.signatureGenerationAlgorithm = signatureGenerationAlgorithm;
        return this;
    }

    public SignerBuilder withSecurityProvider(final SecurityProvider securityProvider) {
        this.securityProvider = securityProvider;
        return this;
    }

    public Signer build() {
        return new Signer(keyGenerationAlgorithm, signatureGenerationAlgorithm, securityProvider);
    }

}
