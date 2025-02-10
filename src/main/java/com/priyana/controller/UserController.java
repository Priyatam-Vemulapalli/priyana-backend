package com.priyana.controller;

import com.priyana.DTO.RequestBody.UserLoginBody;
import com.priyana.DTO.RequestBody.UserRequestBody;
import com.priyana.DTO.ResponseBody.UserResponseBody;
import com.priyana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    private ResponseEntity<UserResponseBody> RegisterUser(@RequestBody UserRequestBody userRequestBody){


        return new ResponseEntity<>(userService.RegisterUser(userRequestBody), HttpStatus.OK);

    }

    @PostMapping("/login")
    private ResponseEntity<UserResponseBody> LoginUser(@RequestBody UserLoginBody userLoginBody){

        return new ResponseEntity<>(userService.LoginUser(userLoginBody), HttpStatus.FOUND);
    }

}
