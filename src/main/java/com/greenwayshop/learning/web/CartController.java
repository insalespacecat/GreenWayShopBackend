package com.greenwayshop.learning.web;

import com.greenwayshop.learning.domain.CartItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@Slf4j
@CrossOrigin
public class CartController {
    @GetMapping(value = "/syncGet", produces = "Application/JSON")
    public ArrayList<CartItem> syncGet(HttpSession session) {
        ArrayList<CartItem> cartBody;
        Optional optCartBody = Optional.ofNullable(session.getAttribute("cartBody"));
        if(optCartBody.isPresent()) {
            cartBody = (ArrayList<CartItem>) session.getAttribute("cartBody");
        } else {
            cartBody = new ArrayList<>();
        }
        return cartBody;
    }

    @PostMapping(value = "/syncPost", consumes = "Application/JSON")
    public void syncPost(HttpServletRequest request, @RequestBody ArrayList<CartItem> cartBody) {
        request.getSession().setAttribute("cartBody", cartBody);
    }

    @PatchMapping(value = "/syncPatch", consumes = "Application/JSON")
    public ArrayList<CartItem> syncPatch(HttpServletRequest request, @RequestBody ArrayList<CartItem> cartBody) { ;
        return cartBody;
    }

    @DeleteMapping("/syncDelete")
    public ArrayList<CartItem> syncDelete(HttpServletRequest request){
        return new ArrayList<CartItem>();
    }
}
