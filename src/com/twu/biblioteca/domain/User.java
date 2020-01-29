package com.twu.biblioteca.domain;

public class User {
    private String userName;
    private String password;
    private boolean isAdmin;
    private int id;
    private String mail;
    private String phone;

    public User(String userName, String password, boolean isAdmin, String mail, String phone) {
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.mail = mail;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getMail() {
        return this.mail;
    }

    public String getPhone() {
        return this.phone;
    }
}
