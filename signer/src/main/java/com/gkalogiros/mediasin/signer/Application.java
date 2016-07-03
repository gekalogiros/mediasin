package com.gkalogiros.mediasin.signer;

import com.gkalogiros.mediasin.SecurityAlgorithm;
import com.gkalogiros.mediasin.SecurityInfrastructureFactory;
import com.gkalogiros.mediasin.SecurityInfrastructureValidity;
import com.gkalogiros.mediasin.SecurityProvider;

import java.io.File;

public class Application {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("USAGE: signer /path/to/file/to/be/signed");
            return;
        }

        final File file = new File(args[0]);

        if (file.exists()) {

            final SecurityAlgorithm keyGenerationAlgorithm = SecurityInfrastructureFactory.getKeyGenerationSecurityAlgorithm();

            final SecurityAlgorithm signatureGenerationAlgorithm = SecurityInfrastructureFactory.getSignatureGenerationSecurityAlgorithm();

            final SecurityProvider securityProvider = SecurityInfrastructureFactory.getSecurityProvider();

            final SecurityInfrastructureValidity securityInfrastructureValidity = new SecurityInfrastructureValidity();

            if (securityInfrastructureValidity.isValid(keyGenerationAlgorithm, securityProvider) &&
                    securityInfrastructureValidity.isValid(signatureGenerationAlgorithm, securityProvider)) {

                Signer.builder()
                        .withKeyGenerationAlgorithm(keyGenerationAlgorithm)
                        .withSignatureGenerationAlgorithm(signatureGenerationAlgorithm)
                        .withSecurityProvider(securityProvider)
                        .build()
                        .sign(file);
            }
        }
    }
}
