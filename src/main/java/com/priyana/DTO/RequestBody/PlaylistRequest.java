package com.priyana.DTO.RequestBody;

import com.priyana.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistRequest {
    User user;
    String name;
}
