package com.is442.oop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.is442.oop.daos.UserDAOInt;
import com.is442.oop.data.models.User;
import com.is442.oop.data.payloads.request.CreateUserRequest;
import com.is442.oop.data.payloads.request.UpdateUserRequest;
import com.is442.oop.data.payloads.response.MessageResponse;


import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserDAOInt UserDAOInt;
    
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = UserDAOInt.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        User user = null;
        try {
            user = UserDAOInt.getUser(id);
        } catch (Exception e) {
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        MessageResponse newUser = UserDAOInt.createUser(createUserRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody UpdateUserRequest updateUserRequest) {
        User updateUser =null;
        try {
            updateUser = UserDAOInt.updateUser(id, updateUserRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(updateUser, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        try {
            UserDAOInt.deleteUser(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}