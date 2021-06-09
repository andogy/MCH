package project.Community.Command;

public enum limitedTypes {
    BEDROCK(0),EDU(1),WS_SERVER(2),JAVA(3),OP(-1),ALL_PLAYER(-2);

    private final int value;

    limitedTypes(final int typeValue) {
        value = typeValue;
    }

    public int getValue() {
        return value;
    }
}
