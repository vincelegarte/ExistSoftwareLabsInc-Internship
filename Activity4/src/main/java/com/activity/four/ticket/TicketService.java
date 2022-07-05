package com.activity.four.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }

    public void addNewTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void deleteTicket(Long ticketNumber){
        boolean exists = ticketRepository.existsById(ticketNumber);

        if(!exists){
            throw new IllegalStateException("ticket with id "+ticketNumber+" does not exist");
        }

        ticketRepository.deleteById(ticketNumber);
    }

    @Transactional
    public void updateTicket(Long ticketNumber, String title, String description, String severity, String status, String asignee, String watchers){

        Ticket ticket = ticketRepository.findById(ticketNumber).orElseThrow(
                ()->new IllegalStateException("ticket with id "+ticketNumber+" does not exist"));

        if (title != null && title.length() > 0 && !Objects.equals(ticket.getTitle(),title)){
            ticket.setTitle(title);
        }

        if (description != null && description.length() > 0 && !Objects.equals(ticket.getDescription(),description)){
            ticket.setDescription(description);
        }

        if (severity != null && severity.length() > 0 && !Objects.equals(ticket.getSeverity(),severity)){
            ticket.setSeverity(severity);
        }

        if (status != null && status.length() > 0 && !Objects.equals(ticket.getStatus(),status)){
            ticket.setStatus(status);
        }

        if (asignee != null && asignee.length() > 0 && !Objects.equals(ticket.getAsignee(),asignee)){
            ticket.setAsignee(asignee);
        }

        if (watchers != null && watchers.length() > 0 && !Objects.equals(ticket.getWatchers(),watchers)){
            ticket.setWatchers(watchers);
        }
    }
}
