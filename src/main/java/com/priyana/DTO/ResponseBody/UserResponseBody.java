package com.priyana.DTO.ResponseBody;


import com.priyana.ENUM.Gender;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseBody {
    String userName;
    String emailId;
    Gender gender;
}
