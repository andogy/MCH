package test.MinecraftQuery.api;

import java.io.Serial;

public class QueryException extends Exception {

    @Serial
    private static final long serialVersionUID = 6650853868480690342L;

    public enum ErrorType {
        GENERIC,
        HOST_NOT_FOUND,
        NETWORK_PROBLEM,
        INVALID_RESPONSE,
        SEND_FAILED,
        TIMEOUT_REACHED,
    }

    private final ErrorType errorType;

    public QueryException() {
        super();
        this.errorType = ErrorType.GENERIC;
    }

    public QueryException(ErrorType errorType) {
        super();
        this.errorType = errorType;
    }

    public QueryException(String message) {
        super(message);
        this.errorType = ErrorType.GENERIC;
    }

    public QueryException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

}
