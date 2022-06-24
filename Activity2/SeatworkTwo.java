package com.activity.two.app;
import com.activity.two.Smartphone;
import com.activity.two.Telephone;

public class SeatworkTwo {

    public static void main(String[] args) {
        
        Telephone tp = new Telephone();
        tp.call("201811925");
        
        Smartphone sp = new Smartphone("201812935","Android");
        System.out.println(sp.getOperatingSystem() + " " + sp.getPhoneNo());
        
        sp.addContact("Vince", "201813945");
        sp.addContact("Andrew", "201814955");
        sp.addContact("Jolene", "201815965");
        sp.addContact("Pia", "201816975");
		sp.addContact("Kenzo", "201817985");
        
        sp.displayContacts();
        sp.removeContact("Pia");
        sp.call("201812995");
        sp.call("Jolene", "201815965");
    }

}