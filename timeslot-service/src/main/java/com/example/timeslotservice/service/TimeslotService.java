package com.example.timeslotservice.service;

import com.example.timeslotservice.dto.ArtistResponse;
import com.example.timeslotservice.dto.StageResponse;
import com.example.timeslotservice.dto.TimeslotDto;
import com.example.timeslotservice.model.Timeslot;
import com.example.timeslotservice.repository.TimeslotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TimeslotService {
    private final TimeslotRepository timeslotRepository;
    private final WebClient webClient;

    @Value("${stageservice.baseurl}")
    private String stageServiceBaseUrl;

    @Value("${artistservice.baseurl}")
    private String artistServiceBaseUrl;

    public List<TimeslotDto> getAllTimeslots() {
        List<Timeslot> timeslots = timeslotRepository.findAll();

        return timeslots.stream()
                .map(timeslot -> new TimeslotDto(
                        timeslot.getStageName(),
                        timeslot.getArtistName()
                ))
                .collect(Collectors.toList());
    }

    public boolean addTimeslot(TimeslotDto timeslotDto) {
        System.out.println(timeslotDto);
        System.out.println(stageServiceBaseUrl);
        System.out.println(artistServiceBaseUrl);

        // Controleer of de stage "remaining" is
        if (!isStageRemaining(timeslotDto.getStageName())) {
            return false; // Stage is niet "remaining", dus voeg geen timeslot toe
        }

        // Controleer of de stage "remaining" is
        if (!existArtist(timeslotDto.getArtistName())) {
            return false; // Stage is niet "remaining", dus voeg geen timeslot toe
        }

        Timeslot timeslot = new Timeslot();
        timeslot.setStageName(timeslotDto.getStageName());
        timeslot.setArtistName(timeslotDto.getArtistName());

        timeslotRepository.save(timeslot);
        return true;
    }

    public boolean isStageRemaining(String stageName) {
        StageResponse[] response = webClient.get()
                .uri("http://" + stageServiceBaseUrl + "/api/stage",
                        uriBuilder -> uriBuilder.queryParam("name", stageName).build())
                .retrieve()
                .bodyToMono(StageResponse[].class)
                .block(); // Blokkeert tot de respons is ontvangen

        if (response != null && response.length > 0) {
            return response[0].isRemaining(); // Neem aan dat de stage naam uniek is
        }
        return false; // Stage niet gevonden of geen response
    }

    public boolean existArtist(String artistName) {
        StageResponse[] response = webClient.get()
                .uri("http://" + artistServiceBaseUrl + "/api/artist",
                        uriBuilder -> uriBuilder.queryParam("name", artistName).build())
                .retrieve()
                .bodyToMono(StageResponse[].class)
                .block(); // Blokkeert tot de respons is ontvangen

        return response != null && response.length > 0;// Stage niet gevonden of geen response
    }
}
