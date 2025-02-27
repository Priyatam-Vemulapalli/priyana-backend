package com.priyana.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "songs")
public class Song {

    public Song(String spotifyId){
        this.spotifyId = spotifyId;
    }
    public Song(String spotifyId, String title, String artist, String thumbnail){
        this.spotifyId = spotifyId;
        this.title = title;
        this.artist = artist;
        this.thumbnail = thumbnail;
    }
    @Id
    private String spotifyId; // Use Spotify ID as primary key

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    private String thumbnail; // URL from Spotify API

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Discussion> discussions;
}
