package com.activity.four.controller;

import com.activity.four.model.Employee;
import com.activity.four.response.Response;
import com.activity.four.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployee() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "/{employeeNumber}")
    public Employee getEmployee(@PathVariable("employeeNumber") Long employeeNumber) {
        return employeeService.getEmployee(employeeNumber);
    }

    @PostMapping
    public Response addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return new Response("employee " + employee.getId() + " inserted", Boolean.TRUE);
    }

    @DeleteMapping(path = "/{employeeNumber}")
    public Response deleteEmployee(@PathVariable("employeeNumber") Long employeeNumber) {
        employeeService.deleteEmployee(employeeNumber);
        return new Response("employee " + employeeNumber + " deleted", Boolean.TRUE);
    }

    @PutMapping(path = "/{employeeNumber}")
    public Response updateEmployee(
            @PathVariable("employeeNumber") Long employeeNumber,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String middleName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String department) {
        employeeService.updateEmployee(employeeNumber, firstName, middleName, lastName, department);
        return new Response("employee " + employeeNumber + " updated", Boolean.TRUE);
    }

    @PutMapping(path = "/{employeeNumber}/remove-ticket/{ticketNumber}")
    public Response removeTicket(
            @PathVariable("employeeNumber") Long employeeNumber,
            @PathVariable("ticketNumber") Long ticketNumber) {
        employeeService.removeTicket(employeeNumber, ticketNumber);
        return new Response("employee " + employeeNumber + " removed ticket " + ticketNumber, Boolean.TRUE);
    }
}