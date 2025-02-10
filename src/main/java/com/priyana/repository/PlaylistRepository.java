package com.priyana.repository;

import com.priyana.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {
    List<Playlist> findByUser_Id(UUID userId);
}
