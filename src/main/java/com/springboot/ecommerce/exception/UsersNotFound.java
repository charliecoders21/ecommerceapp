package com.springboot.ecommerce.exception;

public class UsersNotFound extends RuntimeException{
    public UsersNotFound(String message){
        super(message);
    }
}
