package com.activity.three;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity(name ="contact")
@Table(name="contact")
public class Contact implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long contact_id;

    @Column(name="tel_no", nullable=false)
    private String tel_no;

    @ManyToOne
    @JoinTable(name="person_contact")
    private Person person;

    public Contact(){
    }

    public Contact(String tel_no, Person person){
        this.tel_no = tel_no;
        this.person = person;
    }

    public long getId(){
        return contact_id;
    }

    public void setTel_no(String tel_no){
        this.tel_no = tel_no;
    }

    public Person getPerson(){
        return person;
    }

    public void setPerson(){
        this.person = person;
    }
}
