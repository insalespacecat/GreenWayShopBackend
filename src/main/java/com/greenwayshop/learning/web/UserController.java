package com.greenwayshop.learning.web;

import com.greenwayshop.learning.domain.User;
import com.greenwayshop.learning.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/userInfoForSession")
@AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping
    public User getUserInfo(Authentication authentication) {
        return userService.getUserInfoByAuthentication(authentication);
    }
}
