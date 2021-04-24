package com.greenwayshop.learning.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

class CheckMethods {
    static void checkForNullAndTrowResponseTypeExcIfRequired(Object objToCheck) throws ResponseStatusException{
        if (objToCheck == null) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "null is passed to OrderService logic!");
        }
    }
    static void checkForEmptyAndThrowResponseTypeExcIfRequired(Optional optToCheck) throws ResponseStatusException{
        if(!optToCheck.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
