package com.example.stageservice.service;

import com.example.stageservice.dto.StageRequest;
import com.example.stageservice.dto.StageResponse;
import com.example.stageservice.model.Stage;
import com.example.stageservice.repository.StageRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StageService {

    private final StageRepository stageRepository;

    @PostConstruct
    public void loadData() {
        if(stageRepository.count() <= 0){
            Stage stage = new Stage();
            stage.setName("Main Stage");
            stage.setRemaining(true);

            Stage stage1 = new Stage();
            stage1.setName("Side Stage");
            stage1.setRemaining(false);

            stageRepository.save(stage);
            stageRepository.save(stage1);
        }
    }

    @Transactional(readOnly = true)
    public List<StageResponse> isRemaining(List<String> name) {

        return stageRepository.findByNameIn(name).stream()
        .map(stage ->
                StageResponse.builder()
                        .name(stage.getName())
                        .isRemaining(stage.isRemaining())
                        .build()
        ).toList();
    }

    public List<StageResponse> allStages() {

        return stageRepository.findAll().stream()
                .map(stage ->
                        StageResponse.builder()
                                .name(stage.getName())
                                .isRemaining(stage.isRemaining())
                                .build()
                ).toList();
    }

    public void createStage(StageRequest stageRequest){
        Stage stage = Stage.builder()
                .name(stageRequest.getName())
                .remaining(stageRequest.isRemaining())
                .build();

        stageRepository.save(stage);
    }

    public void deleteStage(String name){
        stageRepository.deleteStagesByName(name);
    }
}
