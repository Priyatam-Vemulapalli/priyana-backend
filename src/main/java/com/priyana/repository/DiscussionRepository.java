package com.priyana.repository;

import com.priyana.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DiscussionRepository extends JpaRepository<Discussion, UUID> {
    List<Discussion> findBySong_SpotifyId(String songId);
}
