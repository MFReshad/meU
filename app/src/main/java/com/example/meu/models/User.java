package com.example.meu.models;

public class User {

    private String name;
    private String mail;
    private String id;

    public User()
    {

    }
    public User(String name, String mail, String id) {
        this.name = name;
        this.mail = mail;
        this.id = id;
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

    public void setId(String id) {
        this.id = id;
    }
}
