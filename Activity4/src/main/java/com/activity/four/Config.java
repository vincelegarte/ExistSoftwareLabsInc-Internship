package com.activity.four;

import com.activity.four.entity.Employee;
import com.activity.four.entity.Ticket;
import com.activity.four.repository.EmployeeRepository;
import com.activity.four.repository.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(
            EmployeeRepository employeeRepository,
            TicketRepository ticketRepository){
        return args -> {
            Employee employee1 = new Employee("Oscar", "Dyer", "Clan", "IT");
            Employee employee2 = new Employee("Danna", "Duran", "Briggs", "ADMIN");
            Employee employee3 = new Employee("Taniya", "Hays", "Larsen", "HR");
            Employee employee4 = new Employee("Jaylynn", "Oconnor", "Hopkins", "SALES");

            Ticket ticket1 = new Ticket("Test1", "1Test", "Low", "New");
            Ticket ticket2 = new Ticket("Test2", "2Test", "Major", "New");
            Ticket ticket3 = new Ticket("Test3", "3Test", "Normal", "Closed");
            Ticket ticket4 = new Ticket("Test4", "4Test", "Critical", "Assigned");

            employeeRepository.saveAll(List.of(employee1,employee2,employee3,employee4));
            ticketRepository.saveAll(List.of(ticket1,ticket2,ticket3,ticket4));
        };
    }
}
