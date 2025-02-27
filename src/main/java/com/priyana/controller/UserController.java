package com.priyana.controller;

import com.priyana.DTO.RequestBody.UserLoginBody;
import com.priyana.DTO.RequestBody.UserRequestBody;
import com.priyana.DTO.ResponseBody.UserResponseBody;
import com.priyana.model.User;
import com.priyana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    private ResponseEntity<UserResponseBody> RegisterUser(@RequestBody User user){
        UserResponseBody userResponseBody = userService.RegisterUser(user);
        return new ResponseEntity<>(userResponseBody, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserResponseBody> getUser(@PathVariable UUID userid){

        UserResponseBody userResponseBody = userService.findUserById(userid);
        return new ResponseEntity<>(userResponseBody,HttpStatus.OK);
    }

    // finish the endpoint to update the user details.
    @PostMapping("/update/{id}")
    private ResponseEntity<UserResponseBody> updateUserDetails(@RequestBody User user, @PathVariable UUID id){
        return ResponseEntity.ok(null);
    }

}
