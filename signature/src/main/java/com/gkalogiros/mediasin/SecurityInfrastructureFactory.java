package com.gkalogiros.mediasin;

public class SecurityInfrastructureFactory {

    private SecurityInfrastructureFactory(){}

    public static SecurityProvider getSecurityProvider() {
       return  SecurityProvider.SUN;
    }

    public static SecurityAlgorithm getKeyGenerationSecurityAlgorithm() {
        return  SecurityAlgorithm.DSA;
    }

    public static SecurityAlgorithm getSignatureGenerationSecurityAlgorithm() {
        return SecurityAlgorithm.SHA_1_WITH_DSA;
    }

}
