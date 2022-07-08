package pro.dayx.aks.controllers;

import pro.dayx.aks.payload.request.UserRequest;
import pro.dayx.aks.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/user/{userId}/info")
    public ResponseEntity<?> receiveUserAdditionalInfo(@RequestBody UserRequest userRequest, @PathVariable Long userId) {

        return userService.saveUserInfo(userRequest, userId);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

}
