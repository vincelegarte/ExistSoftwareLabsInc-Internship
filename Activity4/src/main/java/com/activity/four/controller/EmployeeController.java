package com.activity.four.controller;

import com.activity.four.entity.Employee;
import com.activity.four.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployee(){
        return employeeService.getEmployees();
    }

    @PostMapping
    public void createEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }

    @DeleteMapping(path = "{employeeNumber}")
    public void removeEmployee(@PathVariable("employeeNumber") Long employeeNumber){
        employeeService.deleteEmployee(employeeNumber);
    }

    @PutMapping(path = "{employeeNumber}")
    public void changeEmployee(
            @PathVariable("employeeNumber") Long employeeNumber,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String middleName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String department){
        employeeService.updateEmployee(employeeNumber, firstName,middleName,lastName,department);
    }

}
