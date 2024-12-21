package com.example.stageservice;

import com.example.stageservice.dto.StageRequest;
import com.example.stageservice.dto.StageResponse;
import com.example.stageservice.model.Stage;
import com.example.stageservice.repository.StageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.stageservice.service.StageService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StageServiceUnitTests {

    @InjectMocks
    private StageService stageService;

    @Mock
    private StageRepository stageRepository;

    @Test
    public void testIsRemaining() {
        // Arrange
        List<String> names = Arrays.asList("Main Stage", "Side Stage");
        Stage stage1 = new Stage(1L, "Main Stage", true);
        Stage stage2 = new Stage(2L, "Side Stage", false);
        List<Stage> stages = Arrays.asList(stage1, stage2);

        when(stageRepository.findByNameIn(names)).thenReturn(stages);

        // Act
        List<StageResponse> stageResponses = stageService.isRemaining(names);

        // Assert
        assertEquals(2, stageResponses.size());
        assertEquals("Main Stage", stageResponses.get(0).getName());
        assertTrue(stageResponses.get(0).isRemaining());
        assertEquals("Side Stage", stageResponses.get(1).getName());
        assertFalse(stageResponses.get(1).isRemaining());

        verify(stageRepository, times(1)).findByNameIn(names);
    }

    @Test
    public void testAllStages() {
        // Arrange
        Stage stage1 = new Stage(1L, "Main Stage", true);
        Stage stage2 = new Stage(2L, "Side Stage", false);
        List<Stage> stages = Arrays.asList(stage1, stage2);

        when(stageRepository.findAll()).thenReturn(stages);

        // Act
        List<StageResponse> stageResponses = stageService.allStages();
        assertEquals("Main Stage", stageResponses.get(0).getName());
        assertTrue(stageResponses.get(0).isRemaining());
        assertEquals("Side Stage", stageResponses.get(1).getName());
        assertFalse(stageResponses.get(1).isRemaining());

        // Assert
        assertEquals(2, stageResponses.size());

        verify(stageRepository, times(1)).findAll();
    }

    @Test
    public void testCreateStage() {
        // Arrange
        StageRequest stageRequest = new StageRequest();
        stageRequest.setRemaining(true);
        stageRequest.setName("Test Stage");

        // Act
        stageService.createStage(stageRequest);

        // Assert
        verify(stageRepository, times(1)).save(any(Stage.class));
    }

    @Test
    public void testDeleteStage() {
        // Arrange
        StageRequest stageRequest = new StageRequest();
        stageRequest.setRemaining(true);
        stageRequest.setName("Test Stage");

        // Act
        stageService.createStage(stageRequest);
        stageService.deleteStage("Test Stage");

        // Assert
        verify(stageRepository, times(1)).deleteStagesByName("Test Stage");
    }
}
