package com.priyana.service;

import com.priyana.DTO.RequestBody.RatingRequest;
import com.priyana.exception.ResourceNotFoundException;
import com.priyana.model.Rating;
import com.priyana.model.Song;
import com.priyana.model.User;
import com.priyana.repository.RatingRepository;
import com.priyana.repository.SongRepository;
import com.priyana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    SpotifyService spotifyService;

    public void rateSong(RatingRequest request) {
        Rating rating = new Rating();
        User user = userRepository.findById(request.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Try to fetch the song from the database
        Song song = songRepository.findById(request.getSongId()).orElse(null);

        if (song == null) {
            // Fetch song details from Spotify API if it doesn't exist
            song = spotifyService.fetchSongFromSpotify(request.getSongId());
            // Save the new song to the database
            songRepository.save(song);
        }
        rating.setUser(user);
        rating.setRating(request.getRating());
        rating.setSong(song);
        rating.setReview(request.getReview());

        ratingRepository.save(rating);
    }

    public double getAverageRating(String spotifyId) {
        List<Rating> ratings = ratingRepository.findBySong_SpotifyId(spotifyId);
        return ratings.stream().mapToInt(Rating::getRating).average().orElse(0.0);
    }
}
