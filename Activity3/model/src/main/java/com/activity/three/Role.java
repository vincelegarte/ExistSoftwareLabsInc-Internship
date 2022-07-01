package com.activity.three;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="role")
public class Role implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="role_id")
    private long role_id;

    @Column(name="roles", nullable=false)
    private String roles;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="person_role",
    joinColumns=@JoinColumn(name="role_id"),
    inverseJoinColumns=@JoinColumn(name="person_id"))
    private Set<Person> persons = new HashSet<>();

    public Role(){
    }

    public Role(String roles){
        this.roles = roles;
    }

    public long getId(){
        return role_id;
    }

    public void setRole(String roles){
        this.roles = roles;
    }

    public void addPerson(Person person){
        this.persons.add(person);
    }

    public Set<Person> getPersons(){
        return persons;
    }
}
