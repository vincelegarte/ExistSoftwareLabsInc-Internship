package com.activity.four.ticket;


import javax.persistence.*;

@Entity
@Table
public class Ticket {

    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )
    private Long ticketNumber;
    private String title;
    private String description;
    private String severity;
    private String status;
    private String asignee;
    private String watchers;

    public Ticket() {
    }

    public Ticket(Long ticketNumber, String title, String description, String severity, String status, String asignee, String watchers) {
        this.ticketNumber = ticketNumber;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
        this.asignee = asignee;
        this.watchers = watchers;
    }

    public Ticket(String title, String description, String severity, String status, String asignee, String watchers) {
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
        this.asignee = asignee;
        this.watchers = watchers;
    }

    public Long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
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

    public String getAsignee() {
        return asignee;
    }

    public void setAsignee(String asignee) {
        this.asignee = asignee;
    }

    public String getWatchers() {
        return watchers;
    }

    public void setWatchers(String watchers) {
        this.watchers = watchers;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNumber=" + ticketNumber +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", severity='" + severity + '\'' +
                ", status='" + status + '\'' +
                ", asignee='" + asignee + '\'' +
                ", watchers='" + watchers + '\'' +
                '}';
    }

}
