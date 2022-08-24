package com.example.Admin.exception;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(Integer id) {
        super("Could not find admin " + id);
    }
}
