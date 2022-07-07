package com.activity.four.service;

import com.activity.four.entity.Employee;
import com.activity.four.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public void deleteEmployee(long employeeNumber){
        Employee employee = employeeRepository.findById(employeeNumber).orElseThrow(
                ()->new IllegalStateException("employee with number "+employeeNumber+" does not exist"));
        Optional<Employee> employeeOptional = employeeRepository.findAssignedTicket(employee.getTicket());
        if(employeeOptional.isPresent()){
            throw new IllegalStateException("employee with assigned ticket cannot be deleted");
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
