package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.UserRepository;
import com.greenwayshop.learning.domain.RegistrationForm;
import com.greenwayshop.learning.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class RegistrationService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    //TODO: Replace Boilerplate with properties
    private String BoilerplateEmployeeKey = "EmpKey1894";

    public void registerUser(RegistrationForm registrationForm){
        Optional<User> user = Optional.ofNullable(userRepository.findUserByUsername(registrationForm.getUsername()));
        if(user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is occupied");
        }
        userRepository.save(RegistrationFormToUser(registrationForm));
    }
    public void registerEmployee(RegistrationForm registrationForm){
        Optional<User> user = Optional.ofNullable(userRepository.findUserByUsername(registrationForm.getUsername()));
        if(user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is occupied");
        }
        userRepository.save(RegistrationFormToEmployee(registrationForm));
    }


    private User RegistrationFormToUser(RegistrationForm registrationForm) {
        User user = new User(
                null, registrationForm.getUsername(), passwordEncoder.encode(registrationForm.getPassword()),
                registrationForm.getName(), true, registrationForm.getPhoneNumber(),
                registrationForm.getShippingAddress(), 0.0, 5.0, null
        );
        user.grantCustomerAuthority();
        return user;
    }

    private User RegistrationFormToEmployee(RegistrationForm registrationForm){
        if(!BoilerplateEmployeeKey.equals(registrationForm.getEmployeeKey())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Employee key is not right!");
        }
        User user = new User(
                null, registrationForm.getUsername(), passwordEncoder.encode(registrationForm.getPassword()),
                registrationForm.getName(), true, registrationForm.getPhoneNumber(),
                registrationForm.getShippingAddress(), 0.0, 7.0, null
        );
        user.grantCustomerAuthority();
        user.grantEmployeeAuthority();
        return user;
    }

}