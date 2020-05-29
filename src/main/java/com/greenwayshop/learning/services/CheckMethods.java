package com.greenwayshop.learning.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

class CheckMethods {
    static void checkForNullAndTrowResponseTypeExcIfNeeded(Object objToCheck) throws ResponseStatusException{
        if (objToCheck == null) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "null is passed to OrderService logic!");
        }
    }
    static void checkForEmptyAndThrowResponseTypeExcIfNeeded(Optional optToCheck) throws ResponseStatusException{
        if(optToCheck.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
