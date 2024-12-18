package com.example.artistservice.controller;

import com.example.artistservice.dto.ArtistResponse;
import com.example.artistservice.model.Artist;
import com.example.artistservice.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artist")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistResponse> isArtist
            (@RequestParam List<String> name) {
        return artistService.isArtist(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createArtist (@RequestBody Artist artist) {
        artistService.createArtist(artist);
    }

}
