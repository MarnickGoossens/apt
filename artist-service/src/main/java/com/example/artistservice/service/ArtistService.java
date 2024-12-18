package com.example.artistservice.service;

import com.example.artistservice.dto.ArtistResponse;
import com.example.artistservice.model.Artist;
import com.example.artistservice.repository.ArtistRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    @PostConstruct
    public void loadData() {
        if(artistRepository.count() <= 0){
            Artist artist2 = new Artist();
            artist2.setName("Adele");
            artist2.setDescription("Zangeres en songwriter, bekend om haar krachtige stem en emotionele ballades.");

            Artist artist3 = new Artist();
            artist3.setName("Ed Sheeran");
            artist3.setDescription("Singer-songwriter, beroemd om zijn mix van pop, folk en hiphop, met hits als 'Shape of You'.");

            Artist artist4 = new Artist();
            artist4.setName("Beyoncé");
            artist4.setDescription("Zangeres, actrice en producer, een van de meest invloedrijke vrouwelijke artiesten in de muziekgeschiedenis.");

            Artist artist5 = new Artist();
            artist5.setName("The Weeknd");
            artist5.setDescription("Canadese zanger en songwriter, bekend om zijn unieke stem en innovatieve benadering van R&B en popmuziek.");

            artistRepository.save(artist2);
            artistRepository.save(artist3);
            artistRepository.save(artist4);
            artistRepository.save(artist5);
        }
    }

    @Transactional(readOnly = true)
    public List<ArtistResponse> isArtist(List<String> name) {

        return artistRepository.findByNameIn(name).stream()
                .map(artist ->
                        ArtistResponse.builder()
                                .name(artist.getName())
                                .description(artist.getDescription())
                                .build()
                ).toList();
    }

    public void createArtist(Artist artist){
        Artist artist1 = Artist.builder()
                .name(artist.getName())
                .description(artist.getDescription())
                .build();

        artistRepository.save(artist1);
    }
}
