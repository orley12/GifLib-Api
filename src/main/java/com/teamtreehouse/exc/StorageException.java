package com.teamtreehouse.exc;

import java.io.IOException;

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Exception cause) {
        super(message, cause);

    }
}
