package com.activity.four.service;

import com.activity.four.model.Employee;
import com.activity.four.model.Ticket;
import com.activity.four.repository.EmployeeRepository;
import com.activity.four.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepositoryTest;
    @Mock
    private TicketRepository ticketRepositoryTest;
    private EmployeeService employeeServiceTest;

    private Employee employee;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        employeeServiceTest = new EmployeeService(employeeRepositoryTest, ticketRepositoryTest);
        employee = new Employee(1L,"Vince", "Soriano", "Legarte", "IT");
        ticket = new Ticket(1L,"Ticket 1", "Test 1", "Major", "Assigned");
    }

    @Test
    void getAllEmployees() {
        employeeServiceTest.getEmployees();
        verify(employeeRepositoryTest).findAll();
    }

    @Test
    void getEmployee() {
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        employeeServiceTest.getEmployee(employee.getId());
        verify(employeeRepositoryTest).findById(employee.getId());
    }

    @Test
    void getEmployee_EmployeeDoesNotExist() {
        assertThatThrownBy(() -> employeeServiceTest.getEmployee(employee.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void addEmployee() {
        employeeServiceTest.addEmployee(employee);
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepositoryTest).save(employeeArgumentCaptor.capture());
        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee).isEqualTo(employee);
    }

    @Test
    void deleteEmployee() {
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        employeeServiceTest.deleteEmployee(employee.getId());
        verify(employeeRepositoryTest, times(1)).deleteById(employee.getId());
    }

    @Test
    void deleteEmployee_EmployeeDoesNotExist() {
        assertThatThrownBy(() -> employeeServiceTest.deleteEmployee(employee.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deleteEmployee_WithTicket() {
        employee.setAssigned(ticket);
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        assertThatThrownBy(() -> employeeServiceTest.deleteEmployee(employee.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee with assigned ticket cannot be deleted");
    }

    @Test
    void updateEmployee() {
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        employeeServiceTest.updateEmployee(employee.getId(), "Andrew", "De Leon", "Tolentino", "ADMIN");
        assertEquals(employee.getFirstName(), "Andrew");
        assertEquals(employee.getMiddleName(), "De Leon");
        assertEquals(employee.getLastName(), "Tolentino");
        assertEquals(employee.getDepartment(), "ADMIN");
        verify(employeeRepositoryTest).findById(employee.getId());
    }

    @Test
    void updateEmployee_Null() {
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        employeeServiceTest.updateEmployee(employee.getId(), null, null, null, null);
        assertEquals(employee.getFirstName(), "Vince");
        assertEquals(employee.getMiddleName(), "Soriano");
        assertEquals(employee.getLastName(), "Legarte");
        assertEquals(employee.getDepartment(), "IT");
        verify(employeeRepositoryTest).findById(employee.getId());
    }

    @Test
    void updateEmployee_ZeroLength() {
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        employeeServiceTest.updateEmployee(employee.getId(), "", "", "", "");
        assertEquals(employee.getFirstName(), "Vince");
        assertEquals(employee.getMiddleName(), "Soriano");
        assertEquals(employee.getLastName(), "Legarte");
        assertEquals(employee.getDepartment(), "IT");
        verify(employeeRepositoryTest).findById(employee.getId());
    }

    @Test
    void updateEmployee_Duplicate() {
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        employeeServiceTest.updateEmployee(employee.getId(), "Vince", "Soriano", "Legarte", "IT");
        assertEquals(employee.getFirstName(), "Vince");
        assertEquals(employee.getMiddleName(), "Soriano");
        assertEquals(employee.getLastName(), "Legarte");
        assertEquals(employee.getDepartment(), "IT");
        verify(employeeRepositoryTest).findById(employee.getId());
    }

    @Test
    void updateEmployee_EmployeeDoesNotExist() {
        assertThatThrownBy(() -> employeeServiceTest.updateEmployee(employee.getId(), "Andrew", "De Leon", "Tolentino", "ADMIN"))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void removeTicket() {
        employee.setAssigned(ticket);
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        employeeServiceTest.removeTicket(employee.getId(), ticket.getId());
    }

    @Test
    void removeTicket_EmployeeDoesNotExist() {
        assertThatThrownBy(() -> employeeServiceTest.removeTicket(employee.getId(), ticket.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee with id number of " + employee.getId() + " does not exist");
    }

    @Test
    void removeTicket_TicketDoesNotExist() {
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        assertThatThrownBy(() -> employeeServiceTest.removeTicket(employee.getId(), ticket.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("ticket with id number of " + ticket.getId() + " does not exist");
    }

    @Test
    void removeTicket_NotAssigned() {
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        assertThatThrownBy(() -> employeeServiceTest.removeTicket(employee.getId(), ticket.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee is not assigned to this ticket");
    }

}
