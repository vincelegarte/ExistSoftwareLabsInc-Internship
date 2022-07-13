package com.activity.four;

import com.activity.four.entity.Employee;
import com.activity.four.entity.Ticket;
import com.activity.four.repository.EmployeeRepository;
import com.activity.four.repository.TicketRepository;
import com.activity.four.security.model.Users;
import com.activity.four.security.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class MainConfiguration {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MainConfiguration(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner commandLineRunner(
            EmployeeRepository employeeRepository,
            TicketRepository ticketRepository,
            UsersRepository usersRepository){
        return args -> {

            Users user = new Users("user",passwordEncoder.encode("user"), "USER");
            Users admin = new Users("admin",passwordEncoder.encode("admin"),"ADMIN");

            Employee employee1 = new Employee("Oscar", "Dyer", "Clan", "IT");
            Employee employee2 = new Employee("Danna", "Duran", "Briggs", "ADMIN");
            Employee employee3 = new Employee("Taniya", "Hays", "Larsen", "HR");
            Employee employee4 = new Employee("Jaylynn", "Oconnor", "Hopkins", "SALES");

            Ticket ticket1 = new Ticket("Test1", "1Test", "Low", "New");
            Ticket ticket2 = new Ticket("Test2", "2Test", "Major", "New");
            Ticket ticket3 = new Ticket("Test3", "3Test", "Normal", "Closed");
            Ticket ticket4 = new Ticket("Test4", "4Test", "Critical", "Assigned");

            usersRepository.saveAll(List.of(user,admin));
            employeeRepository.saveAll(List.of(employee1,employee2,employee3,employee4));
            ticketRepository.saveAll(List.of(ticket1,ticket2,ticket3,ticket4));
        };
    }
}
