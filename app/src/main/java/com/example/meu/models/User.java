package com.example.meu.models;

public class User {

    private String name;
    private String mail;
    private String id;
    private String Payment;
    private String imageURL;

    public User()
    {

    }
    public User(String name, String mail, String id,String Payment,String imageURL) {
        this.name = name;
        this.mail = mail;
        this.id = id;
        this.imageURL = imageURL;
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

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String payment) {
        Payment = payment;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
