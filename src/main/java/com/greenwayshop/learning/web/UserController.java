package com.greenwayshop.learning.web;

import com.greenwayshop.learning.domain.AppUser;
import com.greenwayshop.learning.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/userInfoForSession")
@AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping(produces = "application/json")
    public AppUser getUserInfo(Authentication authentication) {
        return userService.getUserInfoByAuthentication(authentication);
    }

    @PostMapping(value = "/{username}", consumes = "application/json")
    public void patchUserInfo(@PathVariable String username, @RequestBody AppUser userToPatch){
        userService.patchUserInfo(userToPatch, username);
    }
}
