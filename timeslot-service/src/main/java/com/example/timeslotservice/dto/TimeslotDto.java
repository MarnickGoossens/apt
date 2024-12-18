package com.example.timeslotservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeslotDto {
    private String stageName;
    private String artistName;
}
