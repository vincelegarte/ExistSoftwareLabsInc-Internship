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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepositoryTest;

    @Mock
    private EmployeeRepository employeeRepositoryTest;
    private TicketService ticketServiceTest;

    private Ticket ticket;
    private Employee employee;

    @BeforeEach
    void setUp() {
        ticketServiceTest = new TicketService(ticketRepositoryTest, employeeRepositoryTest);
        ticket = new Ticket(1L, "Ticket 1", "Test 1", "Major", "Assigned");
        employee = new Employee(1L, "Vince", "Soriano", "Legarte", "IT");
    }

    @Test
    void getTickets() {
        ticketServiceTest.getTickets();
        verify(ticketRepositoryTest).findAll();
    }

    @Test
    void getTicket() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        ticketServiceTest.getTicket(ticket.getId());
        verify(ticketRepositoryTest).findById(ticket.getId());
    }

    @Test
    void getTicket_TicketDoesNotExist() {
        assertThatThrownBy(() -> ticketServiceTest.getTicket(ticket.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void listTicket() {
        employee.setAssigned(ticket);
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        ticketServiceTest.listTickets(employee.getId());
        verify(employeeRepositoryTest).findById(employee.getId());
    }

    @Test
    void listTicket_EmployeeDoesNotExist() {
        assertThatThrownBy(() -> ticketServiceTest.listTickets(employee.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee with id number of " + employee.getId() + " does not exist");
    }

    @Test
    void listTicket_NoAssignee() {
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        assertThatThrownBy(() -> ticketServiceTest.listTickets(employee.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee is not assigned to a ticket");
    }

    @Test
    void addTicket() {
        ticketServiceTest.addTicket(ticket);
        ArgumentCaptor<Ticket> ticketArgumentCaptor = ArgumentCaptor.forClass(Ticket.class);
        verify(ticketRepositoryTest).save(ticketArgumentCaptor.capture());
        Ticket capturedTicket = ticketArgumentCaptor.getValue();
        assertThat(capturedTicket).isEqualTo(ticket);
    }

    @Test
    void deleteTicket() {
        employee.setAssigned(ticket);
        ticket.setAssignee(employee);
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        ticketServiceTest.deleteTicket(ticket.getId());
        verify(ticketRepositoryTest, times(1)).deleteById(ticket.getId());
    }

    @Test
    void deleteTicket_TicketDoesNotExist() {
        assertThatThrownBy(() -> ticketServiceTest.deleteTicket(ticket.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void updateTicket() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        ticketServiceTest.updateTicket(ticket.getId(), "Ticket 1.1", "Test 1.1", "Critical", "Closed");
        assertEquals(ticket.getTitle(), "Ticket 1.1");
        assertEquals(ticket.getDescription(), "Test 1.1");
        assertEquals(ticket.getSeverity(), "Critical");
        assertEquals(ticket.getStatus(), "Closed");
    }

    @Test
    void updateTicket_Null() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        ticketServiceTest.updateTicket(ticket.getId(), null, null, null, null);
        assertEquals(ticket.getTitle(), "Ticket 1");
        assertEquals(ticket.getDescription(), "Test 1");
        assertEquals(ticket.getSeverity(), "Major");
        assertEquals(ticket.getStatus(), "Assigned");
    }

    @Test
    void updateTicket_ZeroLength() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        ticketServiceTest.updateTicket(ticket.getId(), "", "", "", "");
        assertEquals(ticket.getTitle(), "Ticket 1");
        assertEquals(ticket.getDescription(), "Test 1");
        assertEquals(ticket.getSeverity(), "Major");
        assertEquals(ticket.getStatus(), "Assigned");
    }

    @Test
    void updateTicket_Duplicate() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        ticketServiceTest.updateTicket(ticket.getId(), "Ticket 1", "Test 1", "Major", "Assigned");
        assertEquals(ticket.getTitle(), "Ticket 1");
        assertEquals(ticket.getDescription(), "Test 1");
        assertEquals(ticket.getSeverity(), "Major");
        assertEquals(ticket.getStatus(), "Assigned");
    }

    @Test
    void updateTicket_TicketDoesNotExist() {
        assertThatThrownBy(() -> ticketServiceTest.updateTicket(ticket.getId(), "Ticket 1.1", "Test 1.1", "Critical", "Closed"))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void assignTicket() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        ticketServiceTest.assignTicket(ticket.getId(), employee.getId());
        assertThat(ticket.getAssignee()).isEqualTo(employee);
    }

    @Test
    void assignTicket_TicketDoesNotExist() {
        assertThatThrownBy(() -> ticketServiceTest.assignTicket(ticket.getId(), employee.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("ticket with id number of " + ticket.getId() + " does not exist");
    }

    @Test
    void assignTicket_EmployeeDoesNotExist() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        assertThatThrownBy(() -> ticketServiceTest.assignTicket(ticket.getId(), employee.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee with id number of " + employee.getId() + " does not exist");
    }

    @Test
    void assignTicket_TicketIsPresent() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findTicketByEmployee(employee)).thenReturn(optionalTicket);
        Optional<Ticket> ticketOptional = ticketRepositoryTest.findTicketByEmployee(employee);
        assertTrue(ticketOptional.isPresent());
    }

    @Test
    void addWatcher() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        ticketServiceTest.addWatcher(ticket.getId(), employee.getId());
        assertThat(ticket.getWatchers().contains(employee)).isTrue();
    }

    @Test
    void addWatcher_TicketDoesNotExist() {
        assertThatThrownBy(() -> ticketServiceTest.addWatcher(ticket.getId(), employee.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void addWatcher_EmployeeDoesNotExist() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        assertThatThrownBy(() -> ticketServiceTest.addWatcher(ticket.getId(), employee.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee with id number of " + employee.getId() + " does not exist");
    }

    @Test
    void addWatcher_EmployeeIsAlreadyAWatcher() {
        ticket.getWatchers().add(employee);
        Optional<Ticket> optionalTicket = Optional.of(ticket);
        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);
        assertThatThrownBy(() -> ticketServiceTest.addWatcher(ticket.getId(), employee.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee is already on watchers");
    }

}