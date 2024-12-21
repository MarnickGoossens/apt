package com.example.timeslotservice.controller;

import com.example.timeslotservice.dto.TimeslotDto;
import com.example.timeslotservice.model.Timeslot;
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
    public List<Timeslot> getAllTimeslots() {
        return timeslotService.getAllTimeslots();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String addTimeslot(@RequestBody TimeslotDto timeslotDto) {
        return timeslotService.addTimeslot(timeslotDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteTimeslot (@RequestParam Long id) {
        timeslotService.deleteTimeslot(id);
    }
}
