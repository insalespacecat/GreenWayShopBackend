package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.UserRepository;
import com.greenwayshop.learning.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.Optional;

import static com.greenwayshop.learning.services.CheckMethods.checkForEmptyAndThrowResponseTypeExcIfRequired;
import static com.greenwayshop.learning.services.CheckMethods.checkForNullAndTrowResponseTypeExcIfRequired;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public AppUser getUserInfoByAuthentication(Authentication authentication){
        checkForNullAndTrowResponseTypeExcIfRequired(authentication);
        Optional optUser = Optional.ofNullable(userRepository.findUserByUsername(authentication.getName()));
        checkForEmptyAndThrowResponseTypeExcIfRequired(optUser);
        return (AppUser) optUser.get();
    }

    public AppUser getUserInfoByUsername(String username){
        log.info("USERNAME in getUserInfoByUsername is " + username);
        checkForNullAndTrowResponseTypeExcIfRequired(username);
        Optional optUser = Optional.ofNullable(userRepository.findUserByUsername(username));
        checkForEmptyAndThrowResponseTypeExcIfRequired(optUser);
        return (AppUser) optUser.get();
    }

    public AppUser patchUserInfo(AppUser patchedUser, String username){
        checkForNullAndTrowResponseTypeExcIfRequired(patchedUser);
        Optional optUser = Optional.ofNullable(userRepository.findUserByUsername(username));
        checkForEmptyAndThrowResponseTypeExcIfRequired(optUser);
        AppUser user = (AppUser) optUser.get();
        if(patchedUser.getShippingAddress() != user.getShippingAddress()){
            user.setShippingAddress(patchedUser.getShippingAddress());
        }
        if(patchedUser.getName() != user.getName()){
            user.setName(patchedUser.getName());
        }
        if(patchedUser.getPhoneNumber() != user.getPhoneNumber()){
            user.setPhoneNumber(patchedUser.getPhoneNumber());
        }
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser user = getUserInfoByUsername(s);
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
