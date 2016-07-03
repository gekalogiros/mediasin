package com.gkalogiros.mediasin;

import java.security.Provider;
import java.security.Security;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Arrays.*;

public enum SecurityProvider {

    SUN("SUN");

    private Predicate<String> isCurrentKey;
    private Predicate<Provider> isCurrentProvider;
    private Function<Provider, String> toProviderName;

    public String key;

    SecurityProvider(String key) {
        this.key = key;
        this.isCurrentKey = providerName -> providerName.equals(key);
        this.isCurrentProvider = provider -> provider.getName().equals(key);
        this.toProviderName = Provider::getName;
    }

    public String getKey() {
        return key;
    }

    public boolean isAvailable() {
        return getAvailableSecurityProviders().stream().map(toProviderName).filter(isCurrentKey).findAny().isPresent();
    }

    public boolean supportsAlgorithm(final SecurityAlgorithm securityAlgorithm) {
        final Optional<Provider> maybeProvider = getAvailableSecurityProviders().stream().filter(isCurrentProvider).findAny();
        return maybeProvider.isPresent() && maybeProvider.get().getServices().stream().map(Provider.Service::getAlgorithm).filter(algorithm -> securityAlgorithm.getKey().equals(algorithm)).findAny().isPresent();
    }

    private List<Provider> getAvailableSecurityProviders() {
        return asList(Security.getProviders());
    }
}
