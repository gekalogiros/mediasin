package com.gkalogiros.mediasin.signer;

import com.gkalogiros.mediasin.*;
import com.google.common.collect.ImmutableList;

import java.io.File;
import java.security.*;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class Signer {

    private final SecurityAlgorithm keyGenerationAlgorithm;

    private final SecurityAlgorithm signatureGenerationAlgorithm;

    private final SecurityProvider securityProvider;

    public Signer(final SecurityAlgorithm keyGenerationAlgorithm,
                  final SecurityAlgorithm signatureGenerationAlgorithm,
                  final SecurityProvider securityProvider) {

        checkArgument((this.keyGenerationAlgorithm = keyGenerationAlgorithm) != null);
        checkArgument((this.signatureGenerationAlgorithm = signatureGenerationAlgorithm) != null);
        checkArgument((this.securityProvider = securityProvider) != null);
    }

    public void sign(final File file) {

        try {

            final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyGenerationAlgorithm.key, securityProvider.key);

            keyPairGenerator.initialize(1024, SecureRandom.getInstanceStrong());

            final KeyPair keyPair = keyPairGenerator.generateKeyPair();

            final PrivateKey privateKey = keyPair.getPrivate();

            final PublicKey publicKey = keyPair.getPublic();

            final FileSigningImpl fileSigning = new FileSigningImpl(signatureGenerationAlgorithm, securityProvider);

            final DigitalSignature digitalSignature = fileSigning.sign(file, privateKey);

            final SecurityContext privateKeyContext = new SecurityContext("privatekey", privateKey.getEncoded());

            final SecurityContext publicKeyContext = new SecurityContext("publickey", publicKey.getEncoded());

            final SecurityContext signatureDataContext = new SecurityContext("digitalsignature", digitalSignature.get());

            final List<SecurityContext> contextSegments = ImmutableList.of(privateKeyContext, publicKeyContext, signatureDataContext);

            final SecurityContextExport securityContextExport = new SecurityContextExport(contextSegments);

            securityContextExport.export();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SignerBuilder builder(){
        return new SignerBuilder();
    }
}
