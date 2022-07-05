package com.activity.four.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner employeeCommandLineRunner(EmployeeRepository employeeRepository){
        return args -> {
            Employee freddy = new Employee("Freddy","Cameron","Jackson","IT");
            Employee tiana = new Employee("Tiana","Weaver","Wu","HR");
            Employee jadyn = new Employee("Jadyn","Morse","Page","SALES");
            Employee sean = new Employee("Sean","Franco","West","ADMIN");
            employeeRepository.saveAll(List.of(freddy,tiana,jadyn,sean));
        };
    }
}
