package com.gkalogiros.mediasin.verifier;

import com.gkalogiros.mediasin.*;

import java.io.File;
import java.security.PublicKey;

public class Application {

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("USAGE: verifier <path/to/file> </path/to/public/key> <path/to/signature>");
            return;
        }

        try {

            final File file = new File(args[0]);

            final File publicKeyFile = new File(args[1]);

            final File signatureFile = new File(args[2]);

            if (file.exists() && publicKeyFile.exists() && signatureFile.exists()) {

                final SecurityAlgorithm keyGenerationAlgorithm = SecurityInfrastructureFactory.getKeyGenerationSecurityAlgorithm();

                final SecurityAlgorithm signatureGenerationAlgorithm = SecurityInfrastructureFactory.getSignatureGenerationSecurityAlgorithm();

                final SecurityProvider securityProvider = SecurityInfrastructureFactory.getSecurityProvider();

                final SecurityInfrastructureValidity securityInfrastructureValidity = new SecurityInfrastructureValidity();

                if (securityInfrastructureValidity.isValid(keyGenerationAlgorithm, securityProvider) &&
                        securityInfrastructureValidity.isValid(signatureGenerationAlgorithm, securityProvider)) {

                    final RestorePublicKey restorePublicKey = new RestorePublicKey(keyGenerationAlgorithm, securityProvider);

                    final PublicKey publicKey = restorePublicKey.restore(FileToBytes.convert(publicKeyFile));

                    final DigitalSignature digitalSignature = new DigitalSignature(FileToBytes.convert(signatureFile));

                    final boolean verified =
                            Verifier.builder()
                                    .withSignatureGenerationAlgorithm(signatureGenerationAlgorithm)
                                    .withSecurityProvider(securityProvider)
                                    .build()
                                    .verify(file, publicKey, digitalSignature);

                    System.out.println(verified);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
