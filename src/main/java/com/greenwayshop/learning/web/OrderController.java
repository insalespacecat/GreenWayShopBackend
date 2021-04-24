package com.greenwayshop.learning.web;

import com.greenwayshop.learning.domain.Order;
import com.greenwayshop.learning.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
@CrossOrigin
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping(consumes = "Application/JSON")
    public void placeOrder(@RequestBody Order order){
        orderService.placeOrder(order);
    }

    //TODO: Pagination here!
    @GetMapping(value = "/getAll", produces = "Application/JSON")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/getOperatorSlice", produces = "Application/JSON")
    public Page<Order> getOperatorSlice(){
        return orderService.getTopSliceForOperatorView();
    }

    @GetMapping(value = "/getAllOrdersByUsername/{username}")
    public List<Order> getAllOrdersByUser(@PathVariable String username){
        return orderService.getAllOrdersByUsername(username);
    }

    @GetMapping(value = "/last", produces = "Application/JSON")
    public Order getLastOrder(){
        return orderService.getTopOrderByIdDesc();
    }
}
