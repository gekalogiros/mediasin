package com.gkalogiros.mediasin;

public class DigitalSignature {

    private byte[] data;

    public DigitalSignature(byte[] data) {
        this.data = data;
    }

    public byte[] get() {
        return data;
    }
}
