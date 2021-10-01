package com.example.meu.models;



public class Consultant {

    private String name;
    private String mail;
    private String id;
    private String gender;

    public Consultant() {

    }

    public Consultant(String name, String mail, String id, String gender) {
        this.name = name;
        this.mail = mail;
        this.id = id;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String pass) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}