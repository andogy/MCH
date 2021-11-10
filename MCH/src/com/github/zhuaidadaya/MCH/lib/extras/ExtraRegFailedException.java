package com.github.zhuaidadaya.MCH.lib.extras;

import java.io.Serial;

public class ExtraRegFailedException extends Exception{
    @Serial
    private static final long serialVersionUID = 0L;

    public ExtraRegFailedException(String message) {
        super(message);
    }

    public ExtraRegFailedException() {
        super();
    }

    public ExtraRegFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExtraRegFailedException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

}
