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

    private Ticket ticket_1, ticket_2;
    private Employee employee_1, employee_2;

    @BeforeEach
    void setUp() {
        ticketServiceTest = new TicketService(ticketRepositoryTest, employeeRepositoryTest);
        ticket_1 = new Ticket(1L, "Ticket 1", "Test 1", "Major", "Assigned");
        ticket_2 = new Ticket(2L, "Ticket 2", "Test 2", "Critical", "New");
        employee_1 = new Employee(1L, "Vince", "Soriano", "Legarte", "IT");
        employee_2 = new Employee(2L, "Alice", "Harrington", "Rocca", "HR");
    }

    @Test
    void getTickets() {
        ticketServiceTest.getTickets();
        verify(ticketRepositoryTest).findAll();
    }

    @Test
    void getTicket() {
        Optional<Ticket> optionalTicket = Optional.of(ticket_1);
        when(ticketRepositoryTest.findById(ticket_1.getId())).thenReturn(optionalTicket);
        ticketServiceTest.getTicket(ticket_1.getId());
        verify(ticketRepositoryTest).findById(ticket_1.getId());
    }

    @Test
    void getTicket_TicketDoesNotExist() {
        assertThatThrownBy(() -> ticketServiceTest.getTicket(ticket_1.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void listTicket() {
        employee_1.setAssigned(ticket_1);
        Optional<Employee> optionalEmployee = Optional.of(employee_1);
        when(employeeRepositoryTest.findById(employee_1.getId())).thenReturn(optionalEmployee);
        ticketServiceTest.listTickets(employee_1.getId());
        verify(employeeRepositoryTest).findById(employee_1.getId());
    }

    @Test
    void listTicket_EmployeeDoesNotExist() {
        assertThatThrownBy(() -> ticketServiceTest.listTickets(employee_1.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee with id number of " + employee_1.getId() + " does not exist");
    }

    @Test
    void listTicket_NoAssignee() {
        Optional<Employee> optionalEmployee = Optional.of(employee_1);
        when(employeeRepositoryTest.findById(employee_1.getId())).thenReturn(optionalEmployee);
        assertThatThrownBy(() -> ticketServiceTest.listTickets(employee_1.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee is not assigned to a ticket");
    }

    @Test
    void addTicket() {
        ticketServiceTest.addTicket(ticket_1);
        ArgumentCaptor<Ticket> ticketArgumentCaptor = ArgumentCaptor.forClass(Ticket.class);
        verify(ticketRepositoryTest).save(ticketArgumentCaptor.capture());
        Ticket capturedTicket = ticketArgumentCaptor.getValue();
        assertThat(capturedTicket).isEqualTo(ticket_1);
    }

    @Test
    void deleteTicket() {
        employee_1.setAssigned(ticket_1);
        ticket_1.setAssignee(employee_1);
        Optional<Ticket> optionalTicket = Optional.of(ticket_1);
        when(ticketRepositoryTest.findById(ticket_1.getId())).thenReturn(optionalTicket);
        ticketServiceTest.deleteTicket(ticket_1.getId());
        verify(ticketRepositoryTest, times(1)).deleteById(ticket_1.getId());
    }

    @Test
    void deleteTicket_TicketDoesNotExist() {
        assertThatThrownBy(() -> ticketServiceTest.deleteTicket(ticket_1.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void updateTicket() {
        Optional<Ticket> optionalTicket = Optional.of(ticket_1);
        when(ticketRepositoryTest.findById(ticket_1.getId())).thenReturn(optionalTicket);
        ticketServiceTest.updateTicket(ticket_1.getId(), "Ticket 1.1", "Test 1.1", "Critical", "Closed");
        assertEquals(ticket_1.getTitle(), "Ticket 1.1");
        assertEquals(ticket_1.getDescription(), "Test 1.1");
        assertEquals(ticket_1.getSeverity(), "Critical");
        assertEquals(ticket_1.getStatus(), "Closed");
    }

    @Test
    void updateTicket_Null() {
        Optional<Ticket> optionalTicket = Optional.of(ticket_1);
        when(ticketRepositoryTest.findById(ticket_1.getId())).thenReturn(optionalTicket);
        ticketServiceTest.updateTicket(ticket_1.getId(), null, null, null, null);
        assertEquals(ticket_1.getTitle(), "Ticket 1");
        assertEquals(ticket_1.getDescription(), "Test 1");
        assertEquals(ticket_1.getSeverity(), "Major");
        assertEquals(ticket_1.getStatus(), "Assigned");
    }

    @Test
    void updateTicket_ZeroLength() {
        Optional<Ticket> optionalTicket = Optional.of(ticket_1);
        when(ticketRepositoryTest.findById(ticket_1.getId())).thenReturn(optionalTicket);
        ticketServiceTest.updateTicket(ticket_1.getId(), "", "", "", "");
        assertEquals(ticket_1.getTitle(), "Ticket 1");
        assertEquals(ticket_1.getDescription(), "Test 1");
        assertEquals(ticket_1.getSeverity(), "Major");
        assertEquals(ticket_1.getStatus(), "Assigned");
    }

    @Test
    void updateTicket_Duplicate() {
        Optional<Ticket> optionalTicket = Optional.of(ticket_1);
        when(ticketRepositoryTest.findById(ticket_1.getId())).thenReturn(optionalTicket);
        ticketServiceTest.updateTicket(ticket_1.getId(), "Ticket 1", "Test 1", "Major", "Assigned");
        assertEquals(ticket_1.getTitle(), "Ticket 1");
        assertEquals(ticket_1.getDescription(), "Test 1");
        assertEquals(ticket_1.getSeverity(), "Major");
        assertEquals(ticket_1.getStatus(), "Assigned");
    }

    @Test
    void updateTicket_TicketDoesNotExist() {
        assertThatThrownBy(() -> ticketServiceTest.updateTicket(ticket_1.getId(), "Ticket 1.1", "Test 1.1", "Critical", "Closed"))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void assignTicket() {
        Optional<Ticket> optionalTicket = Optional.of(ticket_1);
        when(ticketRepositoryTest.findById(ticket_1.getId())).thenReturn(optionalTicket);
        Optional<Employee> optionalEmployee = Optional.of(employee_1);
        when(employeeRepositoryTest.findById(employee_1.getId())).thenReturn(optionalEmployee);
        ticketServiceTest.assignTicket(ticket_1.getId(), employee_1.getId());
        assertThat(ticket_1.getAssignee()).isEqualTo(employee_1);
    }

    @Test
    void assignTicket_TicketDoesNotExist() {
        assertThatThrownBy(() -> ticketServiceTest.assignTicket(ticket_1.getId(), employee_1.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("ticket with id number of " + ticket_1.getId() + " does not exist");
    }

    @Test
    void assignTicket_EmployeeDoesNotExist() {
        Optional<Ticket> optionalTicket = Optional.of(ticket_1);
        when(ticketRepositoryTest.findById(ticket_1.getId())).thenReturn(optionalTicket);
        assertThatThrownBy(() -> ticketServiceTest.assignTicket(ticket_1.getId(), employee_1.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee with id number of " + employee_1.getId() + " does not exist");
    }

    @Test
    void assignTicket_TicketIsAssigned() {
        ticket_1.setAssignee(employee_2);
        Optional<Ticket> optionalTicket = Optional.of(ticket_1);
        when(ticketRepositoryTest.findById(ticket_1.getId())).thenReturn(optionalTicket);
        Optional<Employee> optionalEmployee = Optional.of(employee_1);
        when(employeeRepositoryTest.findById(employee_1.getId())).thenReturn(optionalEmployee);
        assertThatThrownBy(() -> ticketServiceTest.assignTicket(ticket_1.getId(), employee_1.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("ticket " + ticket_1.getId() + " is already assigned to employee " + ticket_1.getAssignee().getId());
    }

    @Test
    void addWatcher() {
        Optional<Ticket> optionalTicket = Optional.of(ticket_1);
        when(ticketRepositoryTest.findById(ticket_1.getId())).thenReturn(optionalTicket);
        Optional<Employee> optionalEmployee = Optional.of(employee_1);
        when(employeeRepositoryTest.findById(employee_1.getId())).thenReturn(optionalEmployee);
        ticketServiceTest.addWatcher(ticket_1.getId(), employee_1.getId());
        assertThat(ticket_1.getWatchers().contains(employee_1)).isTrue();
    }

    @Test
    void addWatcher_TicketDoesNotExist() {
        assertThatThrownBy(() -> ticketServiceTest.addWatcher(ticket_1.getId(), employee_1.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void addWatcher_EmployeeDoesNotExist() {
        Optional<Ticket> optionalTicket = Optional.of(ticket_1);
        when(ticketRepositoryTest.findById(ticket_1.getId())).thenReturn(optionalTicket);
        assertThatThrownBy(() -> ticketServiceTest.addWatcher(ticket_1.getId(), employee_1.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee with id number of " + employee_1.getId() + " does not exist");
    }

    @Test
    void addWatcher_EmployeeIsAlreadyAWatcher() {
        ticket_1.getWatchers().add(employee_1);
        Optional<Ticket> optionalTicket = Optional.of(ticket_1);
        when(ticketRepositoryTest.findById(ticket_1.getId())).thenReturn(optionalTicket);
        Optional<Employee> optionalEmployee = Optional.of(employee_1);
        when(employeeRepositoryTest.findById(employee_1.getId())).thenReturn(optionalEmployee);
        assertThatThrownBy(() -> ticketServiceTest.addWatcher(ticket_1.getId(), employee_1.getId()))
                .isInstanceOf(IllegalStateException.class).hasMessage("employee is already on watchers");
    }

}