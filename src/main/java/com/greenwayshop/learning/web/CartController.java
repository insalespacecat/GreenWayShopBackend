package com.greenwayshop.learning.web;


import com.greenwayshop.learning.domain.CartItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.session.Session;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
@Slf4j
public class CartController {


    @GetMapping("/syncGet")
    public Object syncGet(HttpSession session) {
        <ArrayList<CartItem> cartBody = (ArrayList<CartItem>)session.getAttribute("cartBody");
        log.info("Sync get request for cart of session " + session.getId());
        log.info("Returning cart " + cartBody.toString());
        return cartBody;
    }

    @PostMapping(value = "/syncPost", consumes = "Application/JSON")
    public void syncPost(HttpSession session, @RequestBody ArrayList<CartItem> cartBody) {
        session.setAttribute("cartBody", cartBody);
        log.info("Cart is saved for session " + session.getId());
        log.info("Cart body: " + session.getAttribute("cartBody").toString());
    }

}
