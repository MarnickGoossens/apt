package com.example.timeslotservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StageResponse {
    private String id;
    private String name;
    private boolean isRemaining;
}
