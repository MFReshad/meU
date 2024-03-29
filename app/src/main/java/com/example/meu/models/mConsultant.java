package com.example.meu.models;

public class mConsultant {
    private String id;
    private String imageUrl;
    private String name;
    private String mail;
    private String status;
    private String img;
    private String gender;  // Add the gender field

    public mConsultant() {
        // Required empty public constructor
    }

    public mConsultant(String id, String imageUrl, String name, String mail, String status, String img, String gender) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.mail = mail;
        this.status = status;
        this.img = img;
        this.gender = gender;
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

    // Getter and setter methods for the img field
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
