package com.example.tickerservice.controller;

import com.example.tickerservice.model.Ticket;
import com.example.tickerservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ticket> getTickets () {
        return ticketService.getTickets();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createArtist (@RequestBody Ticket ticket) {
        ticketService.createTicket(ticket);
    }
}
