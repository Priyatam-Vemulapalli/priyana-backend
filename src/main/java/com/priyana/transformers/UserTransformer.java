package com.priyana.transformers;

import com.priyana.DTO.RequestBody.UserRequestBody;
import com.priyana.DTO.ResponseBody.UserResponseBody;
import com.priyana.model.User;

public class UserTransformer {
    public static UserResponseBody UserToUserResponseBody(User user){

       return UserResponseBody.builder()
                .userName(user.getUsername())
                .emailId(user.getEmail())
                .build();
    }

    public static User UserRequestToUser(UserRequestBody userRequestBody) {

        return User.builder()
                .Username(userRequestBody.getUserName())
                .email(userRequestBody.getEmailId())
                .build();
    }
}
