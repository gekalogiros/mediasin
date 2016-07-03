package com.gkalogiros.mediasin;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class RestorePublicKey {

    private final SecurityAlgorithm securityAlgorithm;
    private final SecurityProvider securityProvider;

    public RestorePublicKey(SecurityAlgorithm securityAlgorithm, SecurityProvider securityProvider) {
        this.securityAlgorithm = securityAlgorithm;
        this.securityProvider = securityProvider;
    }

    public PublicKey restore(final byte[] publicKeyData) throws Exception {
        final X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicKeyData);
        final KeyFactory keyFactory = KeyFactory.getInstance(securityAlgorithm.getKey(), securityProvider.getKey());
        return keyFactory.generatePublic(pubKeySpec);
    }

}
