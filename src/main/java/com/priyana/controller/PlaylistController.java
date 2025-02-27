package com.priyana.controller;


import com.priyana.DTO.RequestBody.PlaylistRequest;
import com.priyana.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    PlaylistService playlistService;


    // this endpoint creates  playlist for a user
    @PostMapping
    public ResponseEntity<String> createPlaylist(@RequestBody PlaylistRequest request) {
        playlistService.createPlaylist(request);
        return ResponseEntity.ok("Playlist created successfully.");
    }

    @DeleteMapping
    public ResponseEntity<String> removePlaylist(@RequestBody PlaylistRequest request){
        playlistService.removePlaylist(request);
        return ResponseEntity.ok("Playlist has been removed.");

    }

    //this endpoint returns list of strings of playlist names for now
    @GetMapping("{userId}")
    public ResponseEntity<List<String>> getPlaylists(@PathVariable UUID userId){
        List<String> playlists = playlistService.getPlaylists(userId);
        return ResponseEntity.ok(playlists);
    }


    /*

        major improvement can be done in updating the playlist in real time instead of returning a string simply.
    */
    // adds a song to a playlist of a user (user auth is not added yet... any user can modify the playlists) fix it later
    @PostMapping("/{playlistId}/add")
    public ResponseEntity<String> addSongToPlaylist(@PathVariable UUID playlistId, @RequestParam String songId) {
        playlistService.addSongToPlaylist(playlistId, songId);
        return ResponseEntity.ok("Song added to playlist.");
    }

    // removes a song from playlist
    @PostMapping("/{playlistId}/remove")
    public ResponseEntity<String> removeSongFromPlaylist(@PathVariable UUID playlistId, @RequestParam String songId){
        playlistService.removeSongFromPlaylist(playlistId, songId);
        return ResponseEntity.ok("Song removed from playlist.");
    }
}
