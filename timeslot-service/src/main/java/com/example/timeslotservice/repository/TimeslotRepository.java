package com.example.timeslotservice.repository;

import com.example.timeslotservice.model.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {
    void deleteTimeslotByArtistName(String artistName);

    void deleteTimeslotById(Long id);
}
