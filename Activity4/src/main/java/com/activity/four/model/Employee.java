package com.activity.four.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Employee {

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String department;

    @JsonIgnore
    @OneToMany
    private Set<Ticket> assigned = new HashSet<>();

    @ManyToMany(mappedBy = "watchers")
    private Set<Ticket> watcher = new HashSet<>();

    public Employee() {
    }

    public Employee(Long id, String firstName, String middleName, String lastName, String department) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.department = department;
    }

    public Employee(String firstName, String middleName, String lastName, String department) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<Ticket> getAssigned() {
        return assigned;
    }

    public void setAssigned(Ticket ticket) {
        assigned.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        assigned.remove(ticket);
    }

}