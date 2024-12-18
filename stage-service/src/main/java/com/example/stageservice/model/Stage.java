package com.example.stageservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean remaining;
}
