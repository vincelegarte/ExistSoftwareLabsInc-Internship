package com.activity.four.ticket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTicket(){
        return ticketService.getTickets();
    }

    @PostMapping
    public void addNewTicket(@RequestBody Ticket ticket){
        ticketService.addNewTicket(ticket);
    }

    @DeleteMapping(path = "{ticketNumber}")
    public void deleteTicket(@PathVariable("ticketNumber") Long ticketNumber){
        ticketService.deleteTicket(ticketNumber);
    }

    @PutMapping(path = "{ticketNumber}")
    public void updateTicket(
            @PathVariable("ticketNumber") Long ticketNumber,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String asignee,
            @RequestParam(required = false) String watchers){
        ticketService.updateTicket(ticketNumber,title,description,severity,status,asignee,watchers);
    }
}
