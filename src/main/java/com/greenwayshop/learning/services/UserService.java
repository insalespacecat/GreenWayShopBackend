package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.UserRepository;
import com.greenwayshop.learning.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.greenwayshop.learning.services.CheckMethods.checkForEmptyAndThrowResponseTypeExcIfNeeded;
import static com.greenwayshop.learning.services.CheckMethods.checkForNullAndTrowResponseTypeExcIfNeeded;

@Service
public class UserService {

    private UserRepository userRepository;
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserInfoByAuthentication(Authentication authentication){
        checkForNullAndTrowResponseTypeExcIfNeeded(authentication);
        Optional optUser = Optional.ofNullable(userRepository.findUserByUsername(authentication.getName()));
        checkForEmptyAndThrowResponseTypeExcIfNeeded(optUser);
        return (User) optUser.get();
    }
}
