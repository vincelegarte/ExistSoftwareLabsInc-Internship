package com.activity.four.service;

import com.activity.four.entity.Employee;
import com.activity.four.entity.Ticket;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepositoryTest;

    @Mock
    private EmployeeRepository employeeRepositoryTest;
    private TicketService ticketServiceTest;
    private EmployeeService employeeServiceTest;

    private Ticket ticket;
    private Employee employee;

    @BeforeEach
    void setUp(){
        ticketServiceTest = new TicketService(ticketRepositoryTest,employeeRepositoryTest);
        ticket = new Ticket("Ticket 1","Test 1","Major","Assigned");
        employee = new Employee("Vince","Soriano","Legarte","IT");
    }

    @Test
    void getTicketsTest() {
        ticketServiceTest.getTickets();
        verify(ticketRepositoryTest).findAll();
    }

    @Test
    void addTicketTest() {
        ticketServiceTest.addTicket(ticket);

        ArgumentCaptor<Ticket> ticketArgumentCaptor = ArgumentCaptor.forClass(Ticket.class);

        verify(ticketRepositoryTest).save(ticketArgumentCaptor.capture());

        Ticket capturedTicket = ticketArgumentCaptor.getValue();

        assertThat(capturedTicket).isEqualTo(ticket);
    }

    @Test
    void deleteTicketTest() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);

        when(ticketRepositoryTest.existsById(optionalTicket.get().getId())).thenReturn(true);

        ticketServiceTest.deleteTicket(ticket.getId());

        verify(ticketRepositoryTest, times(1)).deleteById(ticket.getId());
    }

    @Test
    void deleteTicketExceptionTest(){
        assertThatThrownBy(()-> ticketServiceTest.deleteTicket(ticket.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void changeTicketTest() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);

        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);

        ticketServiceTest.changeTicket(ticket.getId(),"Ticket 1.1","Test 1.1", "Critical", "Closed");

        assertThat(ticket.getTitle()).isEqualTo("Ticket 1.1");
        assertThat(ticket.getDescription()).isEqualTo("Test 1.1");
        assertThat(ticket.getSeverity()).isEqualTo("Critical");
        assertThat(ticket.getStatus()).isEqualTo("Closed");
    }

    @Test
    void changeTicketExceptionTest(){
        assertThatThrownBy(()-> ticketServiceTest.changeTicket(ticket.getId(),"Ticket 1.1","Test 1.1", "Critical", "Closed"))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void assignTicketTest() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);

        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);

        Optional<Employee> optionalEmployee = Optional.of(employee);

        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);

        ticketServiceTest.assignTicket(ticket.getId(),employee.getId());

        assertThat(ticket.getAssignee()).isEqualTo(employee);

        assertThat(employee.getAssigned().contains(ticket)).isTrue();
    }

    @Test
    void assignTicketExceptionTest(){
        assertThatThrownBy(()-> ticketServiceTest.assignTicket(ticket.getId(),employee.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void addWatcherTest() {
        Optional<Ticket> optionalTicket = Optional.of(ticket);

        when(ticketRepositoryTest.findById(ticket.getId())).thenReturn(optionalTicket);

        Optional<Employee> optionalEmployee = Optional.of(employee);

        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);

        ticketServiceTest.addWatcher(ticket.getId(),employee.getId());

        assertThat(ticket.getWatchers().contains(employee)).isTrue();
    }

    @Test
    void addWatcherExceptionTest() {
        assertThatThrownBy(()-> ticketServiceTest.addWatcher(ticket.getId(),employee.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

}