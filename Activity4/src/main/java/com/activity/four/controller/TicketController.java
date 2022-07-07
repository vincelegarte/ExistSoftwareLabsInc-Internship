package com.activity.four.controller;

import com.activity.four.entity.Ticket;
import com.activity.four.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTickets(){
        return ticketService.getTickets();
    }

    @PostMapping
    public void createTicket(@RequestBody Ticket ticket){
        ticketService.addTicket(ticket);
    }

    @DeleteMapping(path = "/{ticketNumber}")
    public void removeTicket(@PathVariable("ticketNumber") Long ticketNumber){
        ticketService.deleteTicket(ticketNumber);
    }

    @PutMapping(path = "/{ticketNumber}")
    public void changeTicket(
            @PathVariable("ticketNumber") Long ticketNumber,
            @RequestParam(required=false) String title,
            @RequestParam(required=false) String description,
            @RequestParam(required=false) String severity,
            @RequestParam(required=false) String status,
            @RequestParam(required=false) Long employeeNumber){
        ticketService.updateTicket(ticketNumber,title,description,severity,status, employeeNumber);
    }

}
