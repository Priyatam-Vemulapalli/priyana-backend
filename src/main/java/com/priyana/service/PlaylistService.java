package com.priyana.service;


import com.priyana.DTO.RequestBody.PlaylistRequest;
import com.priyana.exception.ResourceNotFoundException;
import com.priyana.model.Playlist;
import com.priyana.model.Song;
import com.priyana.model.User;
import com.priyana.repository.PlaylistRepository;
import com.priyana.repository.SongRepository;
import com.priyana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlaylistService {

    @Autowired
    PlaylistRepository playlistRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    SpotifyService spotifyService;

    // creates a playlist for a user and maps it
    public void createPlaylist(PlaylistRequest request) {
        Playlist playlist = new Playlist();

        // Validate if the user exist in the DB to make and map a playlist to user.
        User user = userRepository.findById(request.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // create a playlist and update the DB.
        playlist.setUser(request.getUser());
        playlist.setName(request.getName());
        playlistRepository.save(playlist);
    }

    public void removePlaylist(PlaylistRequest request) {
        Playlist playlist = playlistRepository.findById(request.getName());

        if(playlist == null){
            new ResourceNotFoundException("Playlist not found");
        }

        User user = userRepository.findById(request.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.getPlaylists().remove(playlist);
        userRepository.save(user);
        assert playlist != null;
        playlistRepository.delete(playlist);
    }

    // gets the list of strings (playlist names) instead of List<Playlist>
    public List<String> getPlaylists(UUID userId) {

        // validate the user and return all the playlist names mapped to the user.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return playlistRepository.findByUser_Id(userId).stream()
                .map(Playlist::getName)
                .collect(Collectors.toList());
    }

    public void addSongToPlaylist(UUID playlistId, String songId) {

        // verify if the playlist exist in the database
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        // if yes verify if the song exist in the DB
        Song song = songRepository.findById(songId).orElse(null);

        // if song doesn't exist, just create a new song in the DB
        if (song == null) {
            // Fetch song details from Spotify API if it doesn't exist
            song = spotifyService.fetchSongFromSpotify(songId);
            // Save the new song to the database
            songRepository.save(song);
        }

        // fetch the playlist and add the song to it and update the DB.
        playlist.getSongs().add(song);
        playlistRepository.save(playlist);

    }

    public void removeSongFromPlaylist(UUID playlistId, String songId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
        Song song = songRepository.findById(songId).orElse(null);

        // if song doesn't exist, just create a new song in the DB
        if (song == null) {
            // Fetch song details from Spotify API if it doesn't exist
            song = spotifyService.fetchSongFromSpotify(songId);
            // Save the new song to the database
            songRepository.save(song);
        }

        // fetch the playlist and add the song to it and update the DB.
        playlist.getSongs().remove(song);
        playlistRepository.save(playlist);

    }


}
