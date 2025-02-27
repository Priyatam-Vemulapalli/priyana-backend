package com.priyana.controller;


import com.priyana.DTO.RequestBody.RatingRequest;
import com.priyana.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping("/rate")
    public ResponseEntity<String> rateSong(@RequestBody RatingRequest request) {
        ratingService.rateSong(request);
        return ResponseEntity.ok("Rating saved successfully.");
    }

    // get the Double value of average ratings of a particular song
    @GetMapping("/{spotifyId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable String spotifyId) {
        double avgRating = ratingService.getAverageRating(spotifyId);
        return ResponseEntity.ok(avgRating);
    }
}
