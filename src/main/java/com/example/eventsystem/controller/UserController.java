package com.example.eventsystem.controller;

import com.example.eventsystem.model.User;
import com.example.eventsystem.service.UserService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "eventsystem/users")
public class UserController {

    private final UserService userService; // beam

    // dependency injection
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping(path = "{userId}")
    public User getSingleUser(@PathVariable("userId") Long userId) {return userService.getSingleUser(userId);}


    @PostMapping
    public User registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
        return userService.getSingleUser(user.getId());
    }

    @DeleteMapping (path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public User updateUser (
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String email) {
        userService.updateUser(userId,lastName, firstName, password,email);
        return userService.getSingleUser(userId);

    }
}
