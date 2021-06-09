package project.Community.Command;

import java.io.Serial;

public class CommandOverException extends Exception{
    @Serial
    private static final long serialVersionUID = 0L;

    public CommandOverException(String message) {
        super(message);
    }

    public CommandOverException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandOverException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
