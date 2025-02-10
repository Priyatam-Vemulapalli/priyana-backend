package com.priyana.service;


import com.priyana.DTO.RequestBody.UserLoginBody;
import com.priyana.DTO.RequestBody.UserRequestBody;
import com.priyana.DTO.ResponseBody.UserResponseBody;
import com.priyana.model.User;
import com.priyana.repository.UserRepository;
import com.priyana.transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponseBody RegisterUser(UserRequestBody userRequestBody){

        User user = UserTransformer.UserRequestToUser(userRequestBody);
        User savedUser = userRepository.save(user);
        return UserTransformer.UserToUserResponseBody(savedUser);

    }

    public UserResponseBody LoginUser(UserLoginBody userLoginBody) {

        Optional<User> foundUser = Optional.ofNullable(userRepository.findByemailId(userLoginBody.getLoginId()));

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
