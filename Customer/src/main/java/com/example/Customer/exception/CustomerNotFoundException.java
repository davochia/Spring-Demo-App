package com.example.Customer.exception;

/**
 * @ id requested customer id
 */
public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Integer id) {
        super("Could not find customer " + id);
    }
}
