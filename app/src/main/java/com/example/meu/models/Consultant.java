package com.example.meu.models;



public class Consultant {

    private String name;
    private String mail;
    private String pass;
    private String gender;

    public Consultant() {

    }

    public Consultant(String name, String mail, String pass, String gender) {
        this.name = name;
        this.mail = mail;
        this.pass = pass;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}