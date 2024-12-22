package com.example.timeslotservice;

import com.example.timeslotservice.dto.ArtistResponse;
import com.example.timeslotservice.dto.StageResponse;
import com.example.timeslotservice.dto.TimeslotDto;
import com.example.timeslotservice.model.Timeslot;
import com.example.timeslotservice.repository.TimeslotRepository;
import com.example.timeslotservice.service.TimeslotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TimeslotServiceUnitTests {
    @InjectMocks
    private TimeslotService timeslotService;

    @Mock
    private TimeslotRepository timeslotRepository;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(timeslotService, "stageServiceBaseUrl", "http://localhost:8080");
        ReflectionTestUtils.setField(timeslotService, "artistServiceBaseUrl", "http://localhost:8082");
    }

    @Test
    public void testGetAllTimeslots() {
        // Arrange
        Timeslot timeslot1 = new Timeslot();
        timeslot1.setStageName("Main Stage");
        timeslot1.setArtistName("Pommelien");
        Timeslot timeslot2 = new Timeslot();
        timeslot2.setStageName("Side Stage");
        timeslot2.setArtistName("Acid");
        List<Timeslot> timeslots = Arrays.asList(timeslot1, timeslot2);

        when(timeslotRepository.findAll()).thenReturn(timeslots);

        // Act
        List<Timeslot> timeslotResponses = timeslotService.getAllTimeslots();

        // ASSERT
        assertEquals(2, timeslotResponses.size());
        assertEquals("Main Stage", timeslotResponses.get(0).getStageName());
        assertEquals("Pommelien", timeslotResponses.get(0).getArtistName());
        assertEquals("Side Stage", timeslotResponses.get(1).getStageName());
        assertEquals("Acid", timeslotResponses.get(1).getArtistName());

        verify(timeslotRepository, times(1)).findAll();
    }

    @Test
    public void testAddTimeslot() {
        // Arrange
        TimeslotDto timeslot = new TimeslotDto();
        timeslot.setStageName("Test Stage");
        timeslot.setArtistName("Marnick");

        StageResponse stageResponse = new StageResponse();
        // populate stageResponse with test data
        stageResponse.setName("Main Stage");
        stageResponse.setRemaining(true);

        ArtistResponse artistResponse = new ArtistResponse();
        // populate artistResponse with test data
        artistResponse.setName("Acid");
        artistResponse.setDescription("description");

        lenient().when(webClient.get()).thenReturn(requestHeadersUriSpec);
        lenient().when(requestHeadersUriSpec.uri(anyString(),  any(Function.class))).thenReturn(requestHeadersSpec);
        lenient().when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        lenient().when(responseSpec.bodyToMono(StageResponse[].class)).thenReturn(Mono.just(new StageResponse[]{stageResponse}));
        lenient().when(responseSpec.bodyToMono(ArtistResponse[].class)).thenReturn(Mono.just(new ArtistResponse[]{artistResponse}));

        // Act
        String result = timeslotService.addTimeslot(timeslot);

        // Assert
        assertEquals("Successfully added timeslot", result);
        verify(timeslotRepository, times(1)).save(any(Timeslot.class));
    }

    @Test
    public void testDeleteStage() {
        // Arrange
        TimeslotDto timeslot = new TimeslotDto();
        timeslot.setStageName("Test Stage");
        timeslot.setArtistName("Marnick");

        StageResponse stageResponse = new StageResponse();
        // populate stageResponse with test data
        stageResponse.setName("Main Stage");
        stageResponse.setRemaining(true);

        ArtistResponse artistResponse = new ArtistResponse();
        // populate artistResponse with test data
        artistResponse.setName("Acid");
        artistResponse.setDescription("description");

        lenient().when(webClient.get()).thenReturn(requestHeadersUriSpec);
        lenient().when(requestHeadersUriSpec.uri(anyString(),  any(Function.class))).thenReturn(requestHeadersSpec);
        lenient().when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        lenient().when(responseSpec.bodyToMono(StageResponse[].class)).thenReturn(Mono.just(new StageResponse[]{stageResponse}));
        lenient().when(responseSpec.bodyToMono(ArtistResponse[].class)).thenReturn(Mono.just(new ArtistResponse[]{artistResponse}));

        // Act
        timeslotService.addTimeslot(timeslot);
        timeslotService.deleteTimeslot(1L);

        // Assert
        verify(timeslotRepository, times(1)).deleteTimeslotById(1L);

    }
}
