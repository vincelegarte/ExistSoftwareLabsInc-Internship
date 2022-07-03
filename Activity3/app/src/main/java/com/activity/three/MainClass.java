package com.activity.three;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {

        PersonCRUD pc = new PersonCRUD();
        ContactCRUD cc = new ContactCRUD();
        RoleCRUD rc = new RoleCRUD();

        //PERSON CREATE
        Person person1 = new Person("Vince","Legarte");
        Person person2 = new Person("Andre","Soriano");
        Person person3 = new Person("Troy", "Reyes");
        pc.addPerson(person1);
        pc.addPerson(person2);
        pc.addPerson(person3);

        //PERSON READ
        List<Person> persons = pc.getAllPersons();

        //PERSON GET ID
        Person person4 = pc.getPersonById(person1.getId());

        //PERSON UPDATE
        person2.setFirst_name("Andrew");
        pc.updatePerson(person2); 

        //TEST
        Contact contact6 = new Contact("09123654755",person3);

        //PERSON DELETE
        pc.removePerson(person3.getId());

        //CONTACT CREATE
        Contact contact1 = new Contact("09554100369",person1);
        Contact contact2 = new Contact("09547112369",person1);
        Contact contact3 = new Contact("09999999999",person2);
        Contact contact4 = new Contact("09123654755",person1);
        cc.addContact(contact1);
        cc.addContact(contact2);
        cc.addContact(contact3);
        cc.addContact(contact4);

        //CONTACT READ
        List<Contact> contacts = cc.getAllContacts();

        //CONTACT GET ID
        Contact contact5 = cc.getContactById(contact2.getId());

        //CONTACT UPDATE
        contact3.setTel_no("09994123695");
        cc.updateContact(contact3); 

        //CONTACT DELETE
        cc.removeContact(contact4.getId());

        //ROLE CREATE
        Role role1 = new Role("admin");
        Role role2 = new Role("user");
        rc.addRole(role1);
        rc.addRole(role2);

        //CONTACT READ
        List<Role> roles = rc.getAllRoles();

        //PERSON + CONTACT
        person1.getContact().add(contact1);
        person1.getContact().add(contact2);
        person2.getContact().add(contact3);
        pc.updatePerson(person1);
        pc.updatePerson(person2);

        //UPDATE CONTACT OF PERSON
        contact1.setTel_no("09291667236");
        person1.getContact().add(contact1);
        cc.updateContact(contact1);
        pc.updatePerson(person1);

        //PERSON + ROLE
        person1.getRoles().add(role1);
        person1.getRoles().add(role2);
        person2.getRoles().add(role2);
        role1.getPersons().add(person1);
        role2.getPersons().add(person1);
        role2.getPersons().add(person2);
        rc.updateRole(role1);
        rc.updateRole(role2);

        //UPDATE ROLE OF PERSON
        person2.getRoles().remove(role2);
        role2.getPersons().remove(person2);
        person2.getRoles().add(role1);
        role1.getPersons().add(person2);
        rc.updateRole(role1);
        rc.updateRole(role2);

        //REMOVE ROLE TO PERSON
        person1.getRoles().remove(role2);
        role2.getPersons().remove(person1);
        rc.updateRole(role2);

        //REMOVING PERSON + CONTACT AND ROLE
        person1.getRoles().remove(role1);
        role1.getPersons().remove(person1);
        rc.updateRole(role1);
        pc.removePerson(person1.getId());
    }
}
