package com.priyana.controller;

import com.priyana.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/search")
    public Map<String, Object> searchSongs(@RequestParam String query, @RequestParam(defaultValue = "10") int limit) {
        return spotifyService.searchSongs(query, limit);
    }

    @GetMapping("/{spotifyId}")
    public ResponseEntity<Map<String, Object>> getSongDetails(@PathVariable String spotifyId) {
        Map<String, Object> songDetails = spotifyService.getSongDetails(spotifyId);
        return ResponseEntity.ok(songDetails);
    }

}
