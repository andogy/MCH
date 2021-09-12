package com.github.zhuaidadaya.MCH.Command;

public enum limitedTypes {
    BEDROCK(3), EDU(1), WS_SERVER(2), JAVA(3), OP(-1), ALL_PLAYER(-2), NULL(0), ALL_EDITION(5),BDS(6);

    private final int value;

    limitedTypes(final int typeValue) {
        value = typeValue;
    }

    public int getValue() {
        return value;
    }
}
