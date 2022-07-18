package com.activity.four.controller;

import com.activity.four.model.Ticket;
import com.activity.four.response.Response;
import com.activity.four.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping(path = "/{ticketNumber}")
    public Ticket getTicket(@PathVariable("ticketNumber") Long ticketNumber) {
        return ticketService.getTicket(ticketNumber);
    }

    @GetMapping(path = "/list-ticket/{employeeNumber}")
    public List<Ticket> listTickets(@PathVariable("employeeNumber") Long employeeNumber) {
        return ticketService.listTickets(employeeNumber);
    }


    @PostMapping
    public Response addTicket(@RequestBody Ticket ticket) {
        ticketService.addTicket(ticket);
        return new Response("ticket " + ticket.getId() + " inserted", Boolean.TRUE);
    }

    @DeleteMapping(path = "/{ticketNumber}")
    public Response deleteTicket(@PathVariable("ticketNumber") Long ticketNumber) {
        ticketService.deleteTicket(ticketNumber);
        return new Response("ticket " + ticketNumber + " deleted", Boolean.TRUE);
    }

    @PutMapping(path = "/{ticketNumber}")
    public Response updateTicket(
            @PathVariable("ticketNumber") Long ticketNumber,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String status) {
        ticketService.updateTicket(ticketNumber, title, description, severity, status);
        return new Response("ticket " + ticketNumber + " updated", Boolean.TRUE);
    }

    @PutMapping(path = "/{ticketNumber}/add-assignee/{employeeNumber}")
    public Response assignTicket(
            @PathVariable("ticketNumber") Long ticketNumber,
            @PathVariable("employeeNumber") Long employeeNumber) {
        ticketService.assignTicket(ticketNumber, employeeNumber);
        return new Response("ticket " + ticketNumber + " assigned to employee " + employeeNumber, Boolean.TRUE);
    }

    @PutMapping(path = "/{ticketNumber}/add-watcher/{employeeNumber}")
    public Response addWatcher(
            @PathVariable("ticketNumber") Long ticketNumber,
            @PathVariable("employeeNumber") Long employeeNumber) {
        ticketService.addWatcher(ticketNumber, employeeNumber);
        return new Response("employee " + employeeNumber + " added to ticket " + ticketNumber + " as watcher", Boolean.TRUE);
    }

}