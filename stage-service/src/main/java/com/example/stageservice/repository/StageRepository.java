package com.example.stageservice.repository;

import com.example.stageservice.model.Stage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByNameIn(List<String> name);
}
