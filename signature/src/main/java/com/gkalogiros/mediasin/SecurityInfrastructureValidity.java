package com.gkalogiros.mediasin;

public class SecurityInfrastructureValidity {

    public boolean isValid(final SecurityAlgorithm securityAlgorithm, final SecurityProvider securityProvider) {
        return securityProvider.isAvailable() &&
                securityProvider.supportsAlgorithm(securityAlgorithm);
    }

}
