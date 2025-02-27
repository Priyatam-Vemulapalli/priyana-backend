package com.priyana.DTO.RequestBody;


import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserLoginBody {
    String loginId;
    String password;

}
