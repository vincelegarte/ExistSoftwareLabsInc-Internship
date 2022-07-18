package com.activity.four.repository;

import com.activity.four.model.Employee;
import com.activity.four.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    //CHECK IF A TICKET IS ASSIGNED TO EMPLOYEE
    @Query("SELECT t FROM Ticket t WHERE t.assignee =?1")
    Optional<Ticket> findTicketByEmployee(Employee employee);
}