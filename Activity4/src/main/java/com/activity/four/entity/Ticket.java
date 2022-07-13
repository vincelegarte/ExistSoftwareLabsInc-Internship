package com.activity.four.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Ticket {

    @Id
    @SequenceGenerator(
            name="ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )
    @Column(name = "ticket_number")
    private Long id;
    private String title;
    private String description;
    private String severity;
    private String status;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "assignee", referencedColumnName = "id")
    private Employee assignee;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "employee_watchers",
            joinColumns = @JoinColumn(name = "employee_number"),
            inverseJoinColumns = @JoinColumn(name = "ticket_number")
    )
    private Set<Employee> watchers = new HashSet<>();

    public Ticket() {
    }

    public Ticket(Long id, String title, String description, String severity, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
    }

    public Ticket(String title, String description, String severity, String status) {
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Employee> getWatchers() {
        return watchers;
    }

    public void setWatchers(Employee employee) {
        watchers.add(employee);
    }

    public Employee getAssignee() {
        return assignee;
    }

    public void setAssignee(Employee assignee) {
        this.assignee = assignee;
    }
}