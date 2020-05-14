package com.greenwayshop.learning.web;

import com.greenwayshop.learning.api.UserRepository;
import com.greenwayshop.learning.domain.User;
import com.greenwayshop.learning.exceptions.NotAuthenticatedException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/userInfoForSession")
public class UserInfoController {
    UserRepository userRepository;
    UserInfoController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @GetMapping
    public User getUserInfo(Authentication authentication) throws NotAuthenticatedException {
        if(authentication == null){
            throw new NotAuthenticatedException();
        }
        User user =  userRepository.findUserByUsername(authentication.getName());
        log.info("Returning user: " + user);
        return user;
    }
}
