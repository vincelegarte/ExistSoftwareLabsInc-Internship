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
public class TicketService {

    private TicketRepository ticketRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, EmployeeRepository employeeRepository){
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }

    public void addTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void deleteTicket(Long ticket_number){
        boolean exists = ticketRepository.existsById(ticket_number);
        if(!exists){
            throw new IllegalStateException("ticket with number "+ticket_number+" does not exist");
        }
        ticketRepository.deleteById(ticket_number);
    }

    @Transactional
    public void updateTicket(Long ticketNumber, String title, String description, String severity, String status, Long employeeNumber){
        Ticket ticket = ticketRepository.findById(ticketNumber).orElseThrow(
                ()->new IllegalStateException("ticket with number "+ticketNumber+" does not exist"));

        if(title != null && title.length() > 0 && !Objects.equals(ticket.getTitle(),title)){
            ticket.setTitle(title);
        }

        if(description != null && description.length() > 0 && !Objects.equals(ticket.getDescription(),description)){
            ticket.setDescription(description);
        }

        if(severity != null && severity.length() > 0 && !Objects.equals(ticket.getSeverity(),severity)){
            ticket.setSeverity(severity);
        }

        if(status != null && status.length() > 0 && !Objects.equals(ticket.getStatus(),status)){
            ticket.setStatus(status);
        }

        Employee employee = employeeRepository.findById(employeeNumber).orElseThrow(
                ()->new IllegalStateException("employee with number "+employeeNumber+" does not exist"));

        Optional<Ticket> ticketOptional = ticketRepository.findAssignedTicket(ticket.getEmployee());

        if(employee != null && employeeNumber > 0){
            if(ticketOptional.isPresent()){
                throw new IllegalStateException("ticket is already taken");
            }
            ticket.setEmployee(employee);
            employee.setTicket(ticket);
            ticket.setEmployees(employee);
        }
    }

}
