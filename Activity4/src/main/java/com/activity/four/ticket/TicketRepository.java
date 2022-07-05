package com.activity.four.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
