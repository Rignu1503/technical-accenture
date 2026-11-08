package com.rigo.dev.technical_accenture.api.exeptions;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String message ) {
        super(message + " already exists");
    }
}
