package com.example.artistservice.repository;

import com.example.artistservice.model.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArtistRepository extends MongoRepository<Artist, Long> {
    List<Artist> findByNameIn(List<String> name);

    void deleteArtsByName(String name);

    void deleteArtistByName(String name);
}
