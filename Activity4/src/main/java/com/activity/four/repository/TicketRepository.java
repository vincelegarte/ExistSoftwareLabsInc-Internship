package com.activity.four.repository;

import com.activity.four.entity.Employee;
import com.activity.four.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository <Ticket,Long> {

    @Query("SELECT t FROM Ticket t WHERE t.employee =?1")
    Optional<Ticket> findAssignedTicket(Employee employee);
}
