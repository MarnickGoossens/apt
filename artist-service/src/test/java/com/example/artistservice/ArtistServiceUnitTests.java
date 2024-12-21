package com.example.artistservice;

import com.example.artistservice.dto.ArtistResponse;
import com.example.artistservice.model.Artist;
import com.example.artistservice.repository.ArtistRepository;
import com.example.artistservice.service.ArtistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceUnitTests {
    @InjectMocks
    private ArtistService artistService;

    @Mock
    private ArtistRepository artistRepository;

    @Test
    public void testIsArtist() {
        // Arrange
        List<String> names = Arrays.asList("artist1", "artist2");
        Artist artis1 = new Artist("artist1", "description1");
        Artist artis2 = new Artist("artist2", "description2");
        List<Artist> artists = Arrays.asList(artis1, artis2);

        when(artistRepository.findByNameIn(names)).thenReturn(artists);

        // Act
        List<ArtistResponse> artistResponses = artistService.isArtist(names);

        // ASSERT
        assertEquals(2, artistResponses.size());
        assertEquals("artist1", artistResponses.get(0).getName());
        assertEquals("description1", artistResponses.get(0).getDescription());
        assertEquals("artist2", artistResponses.get(1).getName());
        assertEquals("description2", artistResponses.get(1).getDescription());

        verify(artistRepository, times(1)).findByNameIn(names);
    }

    @Test
    public void testAllArtists() {
        // Arrange
        Artist artis1 = new Artist("artist1", "description1");
        Artist artis2 = new Artist("artist2", "description2");
        List<Artist> artists = Arrays.asList(artis1, artis2);

        when(artistRepository.findAll()).thenReturn(artists);

        // Act
        List<ArtistResponse> artistResponses = artistService.allArtists();

        // ASSERT
        assertEquals(2, artistResponses.size());
        assertEquals("artist1", artistResponses.get(0).getName());
        assertEquals("description1", artistResponses.get(0).getDescription());
        assertEquals("artist2", artistResponses.get(1).getName());
        assertEquals("description2", artistResponses.get(1).getDescription());

        verify(artistRepository, times(1)).findAll();
    }

    @Test
    public void testCreateArtist() {
        // Arrange
        Artist artist = new Artist();
        artist.setName("Test Stage");
        artist.setDescription("description");

        // Act
        artistService.createArtist(artist);

        // Assert
        verify(artistRepository, times(1)).save(any(Artist.class));
    }
}
