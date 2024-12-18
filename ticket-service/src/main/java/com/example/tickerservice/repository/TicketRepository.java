package com.example.tickerservice.repository;

import com.example.tickerservice.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, Long> {
}
