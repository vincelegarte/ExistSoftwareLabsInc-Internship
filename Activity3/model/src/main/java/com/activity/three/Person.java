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
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity(name="person")
@Table(name="person")
public class Person{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="person_id")
    private long person_id;

    @Column(name="first_name", nullable=false)
    private String first_name;

    @Column(name="last_name", nullable=false)
    private String last_name;

    @ManyToMany(mappedBy="persons")
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy="person", orphanRemoval=true, cascade=CascadeType.ALL)
    private Set<Contact> contacts = new HashSet<>();

    public Person(){
    }

    public Person(String first_name, String last_name){
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public long getId(){
        return person_id;
    }

    public String getFirst_name(){
        return first_name;
    }

    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    public String getLast_name(){
        return last_name;
    }

    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    public Set<Role> getRoles(){
        return roles;
    }

    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
    }

    public Set<Contact> getContact(){
        return contacts;
    }

    public void setContact(Set<Contact> contacts){
        this.contacts = contacts;
    }

}
