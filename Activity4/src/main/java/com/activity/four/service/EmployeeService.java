package com.activity.four.service;

import com.activity.four.entity.Employee;
import com.activity.four.repository.EmployeeRepository;
import com.activity.four.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, TicketRepository ticketRepository){
        this.employeeRepository = employeeRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public void deleteEmployee(long employeeNumber){

        boolean exists = employeeRepository.existsById(employeeNumber);
        if(!exists){
            throw new IllegalStateException("employee with number "+employeeNumber+" does not exist");
        }

        employeeRepository.deleteById(employeeNumber);
    }

    @Transactional
    public void updateEmployee(Long employeeNumber, String firstName, String middleName, String lastName, String department){

        Employee employee = employeeRepository.findById(employeeNumber).orElseThrow(
                ()->new IllegalStateException("employee with number "+employeeNumber+" does not exist"));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(employee.getFirstName(), firstName)){
            employee.setFirstName(firstName);
        }

        if (middleName != null && middleName.length() > 0 && !Objects.equals(employee.getMiddleName(), middleName)){
            employee.setMiddleName(middleName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(employee.getLastName(), lastName)){
            employee.setLastName(lastName);
        }

        if (department != null && department.length() > 0 && !Objects.equals(employee.getDepartment(), department)){
            employee.setDepartment(department);
        }
    }

}
