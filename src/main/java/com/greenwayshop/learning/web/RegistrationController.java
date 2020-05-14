package com.greenwayshop.learning.web;


import com.greenwayshop.learning.domain.RegistrationForm;

import com.greenwayshop.learning.exceptions.UserAlreadyExistsException;
import com.greenwayshop.learning.services.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private RegistrationService registrationService;
    RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping(consumes = "Application/JSON")
    public void register(@RequestBody RegistrationForm registrationForm,  HttpServletResponse httpServletResponse) throws UserAlreadyExistsException, IOException {
        Boolean registrationResult = this.registrationService.register(registrationForm);
        if(registrationResult){
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setStatus(201);
            httpServletResponse.getWriter().append(this.registrationService.getSuccessfulRegistrationResponseBody());
        }
        else {
            httpServletResponse.setStatus(500);
        }
    }


}
