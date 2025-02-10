package com.priyana.DTO.RequestBody;

import com.priyana.ENUM.Gender;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestBody {
    String userName;
    int age;
    Gender gender;
    String emailId;
    String password;
}
