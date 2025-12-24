package com.io.tedtalks.exception;

public class TedTalkNotFoundException extends RuntimeException {
    
    public TedTalkNotFoundException(String message) {
        super(message);
    }
    
    public TedTalkNotFoundException(Long id) {
        super("TedTalk not found with id: " + id);
    }
}

