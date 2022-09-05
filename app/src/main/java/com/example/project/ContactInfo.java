package com.example.project;

import java.util.ArrayList;
import java.util.HashMap;


public class ContactInfo {
    public static String NAME = "name";
    public static String ADDRESS = "address";
    public static String TELEPHONE = "telephone";
    public static String CATEGORY = "category";
    public static String GENDER = "gender";
    public static String FAX = "fax";
    public static String WEBSITE = "website";
    public static String RELIGION = "religion";
    public static String WEBSITEVIEW =  "websiteview";

    public static ArrayList<HashMap<String, String>> contactList = new ArrayList<>();


    public static void addContact(String tname, String taddress, String ttelephone, String tcatrgory,
                                  String tgender, String tfax, String twebsite, String treligion, String name,
                                  String address, String telephone, String category, String gender,
                                  String fax, String website, String religion) {

        HashMap<String, String> contact = new HashMap<>();
        contact.put(NAME, tname + " : " + name);
        contact.put(ADDRESS, taddress + " : " + address);
        contact.put(TELEPHONE, ttelephone + " : " + telephone);
        contact.put(CATEGORY, tcatrgory + " : " + category);
        contact.put(GENDER, tgender + " : " + gender);
        contact.put(FAX, tfax + " : " + fax);
        contact.put(WEBSITE, twebsite + " : " + website);
        contact.put(RELIGION, treligion + " : " + religion);
        contact.put(WEBSITEVIEW, website);



        contactList.add(contact);
    }
}