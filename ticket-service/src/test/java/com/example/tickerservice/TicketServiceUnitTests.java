package com.example.tickerservice;

import com.example.tickerservice.model.Ticket;
import com.example.tickerservice.repository.TicketRepository;
import com.example.tickerservice.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceUnitTests {
    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Test
    public void testGetTickets() {
        // Arrange
        Ticket ticket1 = new Ticket("name1", "email1");
        Ticket ticket2 = new Ticket("name2", "email2");
        List<Ticket> tickets = Arrays.asList(ticket1, ticket2);

        when(ticketRepository.findAll()).thenReturn(tickets);

        // Act
        List<Ticket> artistResponses = ticketService.getTickets();

        // ASSERT
        assertEquals(2, artistResponses.size());
        assertEquals("name1", artistResponses.get(0).getName());
        assertEquals("name2", artistResponses.get(1).getName());
        assertEquals("email1", artistResponses.get(0).getEmail());
        assertEquals("email2", artistResponses.get(1).getEmail());

        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void testCreateTicket() {
        // Arrange
        Ticket ticket = new Ticket();
        ticket.setName("marnick");
        ticket.setEmail("marnick@test.com");

        // Act
        ticketService.createTicket(ticket);

        // Assert
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }
}
