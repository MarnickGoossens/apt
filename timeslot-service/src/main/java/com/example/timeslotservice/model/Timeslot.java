package com.example.timeslotservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "timeslots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timeslot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stageName;
    private String artistName;
}
