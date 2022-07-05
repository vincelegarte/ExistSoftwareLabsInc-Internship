package com.activity.four.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

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

    public void addNewEmployee(Employee employee){

        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId){
        boolean exists = employeeRepository.existsById(employeeId);

        if(!exists){
            throw new IllegalStateException("employee with id "+employeeId+" does not exist");
        }

        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public void updateEmployee(Long employeeId, String firstName, String middleName,
                               String lastName, String department) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new IllegalStateException("employee with id " + employeeId + " does not exist"));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(employee.getFirstName(), firstName)) {
            employee.setFirstName(firstName);
        }

        if (middleName != null && middleName.length() > 0 && !Objects.equals(employee.getMiddleName(), middleName)) {
            employee.setMiddleName(middleName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(employee.getLastName(), lastName)) {
            employee.setLastName(lastName);
        }

        if (department != null && department.length() > 0 && !Objects.equals(employee.getDepartment(), department)) {
            employee.setDepartment(department);
        }
    }

}
