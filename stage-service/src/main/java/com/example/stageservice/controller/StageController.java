package com.example.stageservice.controller;

import com.example.stageservice.dto.StageRequest;
import com.example.stageservice.dto.StageResponse;
import com.example.stageservice.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stage")
@RequiredArgsConstructor
public class StageController {

    private final StageService stageService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StageResponse> isRemaining (@RequestParam List<String> name) {
        return stageService.isRemaining(name);
    }

    @RequestMapping("/all")
    public List<StageResponse> allStages() {
        return stageService.allStages();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createStage (@RequestBody StageRequest stageRequest) {
        stageService.createStage(stageRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteStage (@RequestParam String name) {
        stageService.deleteStage(name);
    }
}
