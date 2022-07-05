package com.activity.four.ticket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TicketConfig {

    @Bean
    CommandLineRunner ticketCommandLineRunner(TicketRepository ticketRepository){
        return args -> {
            Ticket ticket1 = new Ticket("test","test1","Low","Assigned","Employee1","ListofEmployees");
            Ticket ticket2 = new Ticket("test","test2","Normal","New","Employee2","ListofEmployees");
            ticketRepository.saveAll(List.of(ticket1,ticket2));
        };
    }
}
