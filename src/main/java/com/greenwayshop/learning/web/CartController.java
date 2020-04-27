package com.greenwayshop.learning.web;


import com.greenwayshop.learning.domain.CartItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;



@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @GetMapping("/syncGet")
    public ArrayList<CartItem> syncGet(HttpSession session) {
        ArrayList<CartItem> cartBody = (ArrayList<CartItem>)session.getAttribute("cartBody");
        log.info("Sync get request for cart of session " + session.getId());
        log.info("Returning cart " + cartBody.toString());
        return cartBody;
    }

    @PostMapping(value = "/syncPost", consumes = "Application/JSON")
    public void syncPost(HttpServletRequest request, @RequestBody ArrayList<CartItem> cartBody) {
        request.getSession().setAttribute("cartBody", cartBody);
        log.info("Cart is saved for session " + request.getSession().getId());
        log.info("Cart body: " + request.getSession().getAttribute("cartBody").toString());
    }
    @PatchMapping("/syncPatch")
    public ArrayList<CartItem> syncPatch(HttpServletRequest request, @RequestBody ArrayList<CartItem> cartBody) {
        request.getSession().removeAttribute("cartBody");
        request.getSession().setAttribute("carrBody", cartBody);
        return cartBody;
    }
    @DeleteMapping("/syncDelete")
    public ArrayList<CartItem> syncDelete(HttpServletRequest request){
        request.getSession().removeAttribute("cartBody");
        request.getSession().setAttribute("cartBody", new ArrayList<CartItem>());
        return new ArrayList<CartItem>();
    }

}
