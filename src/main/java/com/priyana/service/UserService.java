package com.priyana.service;


import com.priyana.DTO.RequestBody.UserLoginBody;
import com.priyana.DTO.RequestBody.UserRequestBody;
import com.priyana.DTO.ResponseBody.UserResponseBody;
import com.priyana.exception.UserAlreadyExistsException;
import com.priyana.model.User;
import com.priyana.repository.UserRepository;
import com.priyana.transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponseBody RegisterUser(User user){

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("This Email Exists in database try logging in.");
        }

        User savedUser = userRepository.save(user);
        return UserTransformer.UserToUserResponseBody(savedUser);
    }

    public UserResponseBody LoginUser(UserLoginBody userLoginBody) {

        Optional<User> foundUser =userRepository.findByEmail(userLoginBody.getLoginId());

        User user = null;
        if(foundUser.isPresent()){
            user = foundUser.get();
            return UserTransformer.UserToUserResponseBody(user);
        }
        else{
            return new UserResponseBody();
        }

    }

    public UserResponseBody findUserById(UUID userid) {
        Optional<User> foundUser = userRepository.findById(userid);
        User user = null;
        if(foundUser.isPresent()){
            user = foundUser.get();
            return UserTransformer.UserToUserResponseBody(user);
        }
        else{
            return new UserResponseBody();
        }
    }
}
