package com.greenwayshop.learning.services;

import com.greenwayshop.learning.api.UserRepository;
import com.greenwayshop.learning.domain.RegistrationForm;
import com.greenwayshop.learning.domain.User;
import com.greenwayshop.learning.exceptions.JSONCreationFailException;
import com.greenwayshop.learning.exceptions.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public Boolean register(RegistrationForm registrationForm){
        log.info("Sign up request for form " + registrationForm.toString());
        try {
            Optional<User> user = Optional.of(userRepository.findUserByUsername(registrationForm.getUsername()));
            if(user.isPresent()) {
                throw new UserAlreadyExistsException();
            }
        } catch (NullPointerException exc) {
            log.info("User is not presented in the database");
        }
        User newUser = User.newCustomerFromRegistrationForm(passwordEncoder, registrationForm);
        try {
            userRepository.save(newUser);
            log.info("User is saved  " + newUser.toString());
            return true;
        }
        catch(Exception exc){
            return false;
        }
    }
    public String getSuccessfulRegistrationResponseBody(){
        JSONObject jsonAnswer = new JSONObject();
        try {
            jsonAnswer.put("status", "created")
                    .put("code", 201)
                    .put("details", "Successful registration");
        } catch (JSONException ex) {
            throw new JSONCreationFailException();
        }
        String stringAnswer = jsonAnswer.toString();
        return stringAnswer;
    }

}