package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.UserRepository;
import com.greenwayshop.learning.domain.RegistrationForm;
import com.greenwayshop.learning.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
public class RegistrationService {
    UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    RegistrationService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegistrationForm registrationForm){
        Optional<User> user = Optional.ofNullable(userRepository.findUserByUsername(registrationForm.getUsername()));
        if(user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is occupied");
        }

        User newUser = User.newCustomerFromRegistrationForm(passwordEncoder, registrationForm);
        userRepository.save(newUser);
    }

}