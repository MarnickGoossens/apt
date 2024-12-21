package com.example.tickerservice.service;

import com.example.tickerservice.model.Ticket;
import com.example.tickerservice.repository.TicketRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    @PostConstruct
    public void loadData() {
        if(ticketRepository.count() <= 0){
            Ticket ticket1 = new Ticket();
            ticket1.setName("Marnick Goossens");
            ticket1.setEmail("marnick@example.com");

            ticketRepository.save(ticket1);
        }
    }

    @Transactional(readOnly = true)
    public List<Ticket> getTickets() {
        return ticketRepository.findAll().stream().toList();
    }

    public void createTicket(Ticket ticket) {
        Ticket ticket1 = Ticket.builder()
                .name(ticket.getName())
                .email(ticket.getEmail())
                .build();

        ticketRepository.save(ticket1);
    }
}
