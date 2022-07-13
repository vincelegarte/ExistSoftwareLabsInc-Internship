package com.activity.four.service;

import com.activity.four.entity.Employee;
import com.activity.four.entity.Ticket;
import com.activity.four.repository.EmployeeRepository;
import com.activity.four.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TicketService{

    private TicketRepository ticketRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, EmployeeRepository employeeRepository) {
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Ticket> getTickets(){
        return  ticketRepository.findAll();
    }

    public void addTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void deleteTicket(Long ticketNumber){
        boolean exists = ticketRepository.existsById(ticketNumber);
        if(!exists){
            throw new IllegalStateException("ticket with id number of "+ticketNumber+" does not exist");
        }
        ticketRepository.deleteById(ticketNumber);
    }

    @Transactional
    public void changeTicket(Long ticketNumber, String title, String description, String severity, String status) {
        Ticket ticket = ticketRepository.findById(ticketNumber).orElseThrow(
                ()->new IllegalStateException("ticket with id number of "+ticketNumber+" does not exist"));

        if (title != null && title.length() > 0 && !Objects.equals(ticket.getTitle(), title)) {
            ticket.setTitle(title);
        }

        if (description != null && description.length() > 0 && !Objects.equals(ticket.getDescription(), description)) {
            ticket.setDescription(description);
        }

        if (severity != null && severity.length() > 0 && !Objects.equals(ticket.getSeverity(), severity)) {
            ticket.setSeverity(severity);
        }

        if (status != null && status.length() > 0 && !Objects.equals(ticket.getStatus(), status)) {
            ticket.setStatus(status);
        }
    }

    @Transactional
    public void assignTicket(Long ticketNumber, Long employeeNumber){
        Ticket ticket = ticketRepository.findById(ticketNumber).orElseThrow(
                ()->new IllegalStateException("ticket with id number of "+ticketNumber+" does not exist"));
        Employee employee = employeeRepository.findById(employeeNumber).orElseThrow(
                ()->new IllegalStateException("employee with id number of "+employeeNumber+" does not exist"));
        Optional<Ticket> ticketOptional = ticketRepository.findTicketByEmployee(ticket.getAssignee());
        if(ticketOptional.isPresent()){
            throw new IllegalStateException("ticket with id number of "+ticketNumber+" is already taken");
        }
        employee.setAssigned(ticket);
        ticket.setAssignee(employee);
    }

    @Transactional
    public void addWatcher(Long ticketNumber, Long employeeNumber){
        Ticket ticket = ticketRepository.findById(ticketNumber).orElseThrow(
                ()->new IllegalStateException("ticket with id number of "+ticketNumber+" does not exist"));
        Employee employee = employeeRepository.findById(employeeNumber).orElseThrow(
                ()->new IllegalStateException("employee with id number of "+employeeNumber+" does not exist"));
        if(ticket.getWatchers().contains(employee)){
            throw new IllegalStateException("employee is already on watchers");
        }
        ticket.setWatchers(employee);
    }

}