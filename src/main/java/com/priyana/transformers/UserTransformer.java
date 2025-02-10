package com.priyana.transformers;

import com.priyana.DTO.RequestBody.UserRequestBody;
import com.priyana.DTO.ResponseBody.UserResponseBody;
import com.priyana.model.User;

public class UserTransformer {
    public static UserResponseBody UserToUserResponseBody(User user){

       return UserResponseBody.builder()
                .userName(user.getUserName())
                .emailId(user.getEmailId())
                .gender(user.getGender())
                .build();
    }

    public static User UserRequestToUser(UserRequestBody userRequestBody) {

        return User.builder()
                .username(userRequestBody.getUserName())
                .email(userRequestBody.getEmailId())
                .build();
    }
}
