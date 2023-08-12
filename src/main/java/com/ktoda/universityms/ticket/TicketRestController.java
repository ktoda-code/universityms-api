package com.ktoda.universityms.ticket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@Slf4j
public class TicketRestController {
    private final TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }

    @GetMapping("{staffId}")
    public List<Ticket> findAllTicketsAssignedToStaff(@PathVariable long staffId) {
        return ticketService.findAllTicketsAssignedToStaff(staffId);
    }

    @GetMapping("open/{staffId}")
    public List<Ticket> findAllOpenTicketsAssignedToStaff(@PathVariable long staffId) {
        return ticketService.findAllOpenTicketsAssignedToStaff(staffId);
    }

    @PostMapping
    public Ticket createTicket(@RequestBody TicketRegistrationRequest ticketDTO) {
        log.info("Creating Ticket with: " + ticketDTO.toString());
        return ticketService.createTicket(
                ticketDTO.title(),
                ticketDTO.description(),
                ticketDTO.userId()
        );
    }

    @PutMapping("{ticketId}")
    public void closeTicket(@PathVariable long ticketId) {
        ticketService.closeTicket(ticketId);
    }

    @DeleteMapping("{ticketId}")
    public void deleteTicketById(@PathVariable long ticketId) {
        ticketService.deleteTicketById(ticketId);
    }

}
