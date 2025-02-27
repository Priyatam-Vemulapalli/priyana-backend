package com.priyana.DTO.RequestBody;

import com.priyana.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {
    private User user;
    private String songId;
    private int rating;
    private String review;
}
