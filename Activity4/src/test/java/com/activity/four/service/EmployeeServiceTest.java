package com.activity.four.service;

import com.activity.four.entity.Employee;
import com.activity.four.entity.Ticket;
import com.activity.four.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepositoryTest;
    private EmployeeService employeeServiceTest;

    private Employee employee;
    private Ticket ticket;

    @BeforeEach
    void setUp(){
        employeeServiceTest = new EmployeeService(employeeRepositoryTest);
        employee = new Employee("Vince","Soriano","Legarte","IT");
        ticket = new Ticket("Ticket 1","Test 1","Major","Assigned");
    }

    @Test
    void getAllEmployeesTest(){
        employeeServiceTest.getEmployees();
        verify(employeeRepositoryTest).findAll();
    }

    @Test
    void addEmployeeTest(){
        employeeServiceTest.addEmployee(employee);

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        verify(employeeRepositoryTest).save(employeeArgumentCaptor.capture());

        Employee capturedEmployee = employeeArgumentCaptor.getValue();

        assertThat(capturedEmployee).isEqualTo(employee);
    }

    @Test
    void deleteEmployeeTest(){
        Optional<Employee> optionalEmployee = Optional.of(employee);

        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);

        employeeServiceTest.deleteEmployee(employee.getId());

        verify(employeeRepositoryTest, times(1)).deleteById(employee.getId());
    }

    @Test
    void deleteEmployeeExceptionTest(){
        assertThatThrownBy(()-> employeeServiceTest.deleteEmployee(employee.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void changeEmployeeTest(){
        Optional<Employee> optionalEmployee = Optional.of(employee);

        when(employeeRepositoryTest.findById(employee.getId())).thenReturn(optionalEmployee);

        employeeServiceTest.changeEmployee(employee.getId(),"Andrew","De Leon","Tolentino","ADMIN");

        assertThat(employee.getFirstName()).isEqualTo("Andrew");
        assertThat(employee.getMiddleName()).isEqualTo("De Leon");
        assertThat(employee.getLastName()).isEqualTo("Tolentino");
        assertThat(employee.getDepartment()).isEqualTo("ADMIN");
    }

    @Test
    void changeEmployeeExceptionTest(){
        assertThatThrownBy(()->employeeServiceTest.changeEmployee(employee.getId(),"Andrew","De Leon","Tolentino","ADMIN"))
                .isInstanceOf(IllegalStateException.class);
    }

}
