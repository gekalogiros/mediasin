package com.gkalogiros.mediasin;

public enum SecurityAlgorithm {
    DSA("DSA"),
    SUN("SUN"),
    AES("AES"),
    DES_EDE("DESede"),
    HMAC_SHA_1("HmacSHA1"),
    HMAC_SHA_256("HmacSHA256"),
    SHA_1_WITH_DSA("SHA1withDSA");

    public final String key;

    SecurityAlgorithm(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
