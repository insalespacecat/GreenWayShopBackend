package com.greenwayshop.learning.web;


import com.greenwayshop.learning.api.UserRepository;
import com.greenwayshop.learning.domain.RegistrationForm;

import com.greenwayshop.learning.domain.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    RegistrationController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public void register(@RequestBody RegistrationForm registrationForm) throws UsernameNotFoundException {
        Optional<User> user = Optional.of(userRepository.findUserByUsername(registrationForm.getUsername()));
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User" + registrationForm.getName() + "not found");
        }
        userRepository.save(registrationForm.toUser(passwordEncoder));
    }
}
