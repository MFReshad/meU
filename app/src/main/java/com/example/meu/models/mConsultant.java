package com.example.meu.models;

public class mConsultant {
    private String id;
    private String imageUrl;
    private String name;
    private String mail;
    private String status;



    public mConsultant() {

    }

    public mConsultant(String id,String imageUrl, String name, String mail, String status, String search) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.mail = mail;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
