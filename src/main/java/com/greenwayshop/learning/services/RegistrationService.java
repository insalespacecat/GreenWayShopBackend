package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.UserRepository;
import com.greenwayshop.learning.domain.RegistrationForm;
import com.greenwayshop.learning.domain.AppUser;
import com.greenwayshop.learning.enums.Authority;
import com.greenwayshop.learning.properties.AdministrationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class RegistrationService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private AdministrationProperties administrationProperties;

    public void registerUser(RegistrationForm registrationForm){
        Optional<AppUser> user = Optional.ofNullable(userRepository.findUserByUsername(registrationForm.getUsername()));
        if(user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is occupied");
        }
        AppUser userr = userRepository.save(RegistrationFormToUser(registrationForm));
        log.info(userr.toString());
    }
    public void registerEmployee(RegistrationForm registrationForm){
        Optional<AppUser> user = Optional.ofNullable(userRepository.findUserByUsername(registrationForm.getUsername()));
        if(user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is occupied");
        }
        userRepository.save(RegistrationFormToEmployee(registrationForm));
    }


    private AppUser RegistrationFormToUser(RegistrationForm registrationForm) {
        AppUser user = new AppUser(
                null, registrationForm.getUsername(), passwordEncoder.encode(registrationForm.getPassword()),
                registrationForm.getName(), true, registrationForm.getPhoneNumber(),
                registrationForm.getShippingAddress(), 0.0, 5.0, Collections.singleton(Authority.CUSTOMER)
        );
        //user.grantCustomerAuthority();
        return user;
    }

    private AppUser RegistrationFormToEmployee(RegistrationForm registrationForm){
        if(!administrationProperties.getEmployeeKey().equals(registrationForm.getEmployeeKey())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Employee key is not right!");
        }
        AppUser user = new AppUser(
                null, registrationForm.getUsername(), passwordEncoder.encode(registrationForm.getPassword()),
                registrationForm.getName(), true, registrationForm.getPhoneNumber(),
                registrationForm.getShippingAddress(), 0.0, 7.0, Collections.singleton(Authority.EMPLOYEE)
        );
        return user;
    }

}