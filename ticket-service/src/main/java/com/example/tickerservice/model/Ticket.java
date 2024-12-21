package com.example.tickerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Ticket {
    private String name;
    private String email;
}
