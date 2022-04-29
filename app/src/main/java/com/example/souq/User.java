package com.example.souq;

public abstract class User {
    String name;
    String mail;
    String Phone;
    String password;



    public User(String name, String mail, String phone, String password) {
        this.name = name;
        this.mail = mail;
        Phone = phone;
        this.password = password;

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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
