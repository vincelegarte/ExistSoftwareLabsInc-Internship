package com.activity.two;
import java.util.Map;
import java.util.HashMap;

public class Smartphone extends Telephone {

    public String phoneNo;
    public String operatingSystem;
    Map<String, String> contacts = new HashMap<>();

    Telephone tp = new Telephone();

    public Smartphone(String phoneNo, String operatingSystem) {
        this.phoneNo = phoneNo;
        this.operatingSystem = operatingSystem;
    }
	
	public String getPhoneNo() {
        return phoneNo;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void addContact(String name, String phoneNo) {
        contacts.put(name, phoneNo);
    }

    public void removeContact(String name) {
        contacts.remove(name);
    }

    public void call(String phoneNo) {
		
        if (phoneNo == this.phoneNo) {
            System.out.println("You cannot call yourself");
        } else {
            tp.call(phoneNo);
        }

    }

    public void call(String name, String phoneNo) {
        
        boolean exists = contacts.containsKey(name);

        if (exists == true) {
            System.out.println("Calling from contacts");
            tp.call(phoneNo);
        }
    }

    public void displayContacts() {

        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println("Name: " + entry.getKey() + " - Phone Number: " + entry.getValue());
        }

    }

}