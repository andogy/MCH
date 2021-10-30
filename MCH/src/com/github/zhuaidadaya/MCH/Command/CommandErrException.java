package com.github.zhuaidadaya.MCH.Command;

import java.io.Serial;

@Command
public class CommandErrException extends Exception{
    @Serial
    private static final long serialVersionUID = 0L;

    public CommandErrException(String message) {
        super(message);
    }

    public CommandErrException() {
        super();
    }

    public CommandErrException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandErrException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
