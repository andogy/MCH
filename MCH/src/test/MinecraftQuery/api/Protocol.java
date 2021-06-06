package test.MinecraftQuery.api;

public enum Protocol {
    TCP(47), TCP_DEPRECIATED(74), UDP_BASIC(1), UDP_FULL(2), NULL(0);

    private final int value;

    Protocol(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}