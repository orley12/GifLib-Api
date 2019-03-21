package com.teamtreehouse.exc;

import java.net.MalformedURLException;

public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }
    public StorageFileNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
