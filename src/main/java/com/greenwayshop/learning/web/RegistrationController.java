package com.greenwayshop.learning.web;

import com.greenwayshop.learning.domain.RegistrationForm;
import com.greenwayshop.learning.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping(consumes = "application/json")
    public void register(@RequestBody RegistrationForm registrationForm) {
        registrationService.registerUser(registrationForm);
    }
    @PostMapping(value = "/employee", consumes = "application/json")
    public void registerEmployee(@RequestBody RegistrationForm registrationForm){
        registrationService.registerEmployee(registrationForm);
    }
}
