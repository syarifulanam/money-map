package com.syarifulanam.moneymap.controller;

import com.syarifulanam.moneymap.entity.User;
import com.syarifulanam.moneymap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(name = "id") long id) {
        return userService.getUserById(id);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "id") long id, @RequestBody User user) {
        User updateUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(name = "id") long id) {
        userService.deleteUser(id);
    }

}
