package com.example.timeslotservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "timeslots")
@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timeslot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stageName;
    private String artistName;
}
