package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.UserRepository;
import com.greenwayshop.learning.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.greenwayshop.learning.services.CheckMethods.checkForEmptyAndThrowResponseTypeExcIfNeeded;
import static com.greenwayshop.learning.services.CheckMethods.checkForNullAndTrowResponseTypeExcIfNeeded;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User getUserInfoByAuthentication(Authentication authentication){
        checkForNullAndTrowResponseTypeExcIfNeeded(authentication);
        Optional optUser = Optional.ofNullable(userRepository.findUserByUsername(authentication.getName()));
        checkForEmptyAndThrowResponseTypeExcIfNeeded(optUser);
        return (User) optUser.get();
    }

    public User getUserInfoByUsername(String username){
        checkForNullAndTrowResponseTypeExcIfNeeded(username);
        Optional optUser = Optional.ofNullable(userRepository.findUserByUsername(username));
        checkForEmptyAndThrowResponseTypeExcIfNeeded(optUser);
        return (User) optUser.get();
    }
}
