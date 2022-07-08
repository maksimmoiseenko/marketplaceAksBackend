package pro.dayx.aks.controllers;

import pro.dayx.aks.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    @GetMapping("user/{userId}/orders")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long userId){
        return orderService.getOrdersByUserId(userId);
    }
    @DeleteMapping("order/{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        orderService.deleteById(orderId);
    }
    @PostMapping("order")
    public ResponseEntity<?> addOrder(@RequestBody Long suggestionId, @AuthenticationPrincipal UserDetails userDetails){
        return orderService.addOrder(suggestionId, userDetails);
    }
}
