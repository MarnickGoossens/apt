package com.example.timeslotservice.controller;

import com.example.timeslotservice.dto.TimeslotDto;
import com.example.timeslotservice.service.TimeslotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timeslot")
@RequiredArgsConstructor
public class TimeslotController {
    private final TimeslotService timeslotService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TimeslotDto> getAllTimeslots() {
        return timeslotService.getAllTimeslots();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String addTimeslot(@RequestBody TimeslotDto timeslotDto) {
        boolean result = timeslotService.addTimeslot(timeslotDto);
        return (result ? "timeslot published successfully" : "timeslot placement failed");
    }
}
