package com.example.artistservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "artist")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Artist {
    private String name;
    private String description;
}
