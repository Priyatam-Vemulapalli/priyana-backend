package com.priyana.DTO.RequestBody;


import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Builder
public class UserLoginBody {
    String loginId;
    String password;
}
