package com.activity.four.repository;

import com.activity.four.entity.Employee;
import com.activity.four.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT e FROM Employee e WHERE e.ticket =?1")
    Optional<Employee> findAssignedTicket(Ticket ticket);

}
