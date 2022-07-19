package pro.dayx.aks.controllers;

import org.springframework.security.access.annotation.Secured;
import pro.dayx.aks.models.AddressEntity;
import pro.dayx.aks.models.OrderEntity;
import pro.dayx.aks.payload.request.AddressRequest;
import pro.dayx.aks.payload.request.OrderRequest;
import pro.dayx.aks.security.services.UserEntity;
import pro.dayx.aks.services.AddressService;
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
    private final AddressService addressService;
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService, AddressService addressService){
        this.orderService = orderService;
        this.addressService = addressService;
    }

    @DeleteMapping("order/{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        orderService.deleteById(orderId);
    }
    @PostMapping("order")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequest orderRequest, @AuthenticationPrincipal UserDetails userDetails){
        System.out.println(orderRequest.toString());
        return orderService.addOrder(orderRequest, userDetails);
    }
    @GetMapping("user/addresses")
    public ResponseEntity<?> getUserAddresses(@AuthenticationPrincipal UserDetails userDetails){
        return addressService.getUserAddresses(userDetails);
    }
    @PostMapping("user/address")
    public ResponseEntity<?> addAddress(@AuthenticationPrincipal UserDetails details, @RequestBody AddressRequest addressRequest){
        System.out.println(addressRequest.toString());
        return addressService.addAddress(details, addressRequest);
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping("orders")
    public ResponseEntity<?> getAllOrders(){
        return this.orderService.getAllOrders();
    }
    @Secured({"ROLE_CLIENT","ROLE_ADMIN"})
    @GetMapping("orders/client/{clientId}")
    public ResponseEntity<?> getAllClientOrders(@PathVariable Long clientId, @AuthenticationPrincipal UserDetails userDetails){
        return this.orderService.getAllClientOrders(clientId, userDetails);
    }
    @Secured({"ROLE_SUPPLIER","ROLE_ADMIN"})
    @GetMapping("orders/supplier/{supplierId}")
    public ResponseEntity<?> getAllSupplierOrders(@PathVariable Long supplierId, @AuthenticationPrincipal UserDetails userDetails){
        return this.orderService.getAllSupplierOrders(supplierId, userDetails);
    }
}
