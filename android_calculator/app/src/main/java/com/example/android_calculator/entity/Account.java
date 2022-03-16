package com.example.android_calculator.entity;

public class Account {
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String avatar;
    private int gender;
    private String email;
    private String birthday;

    public Account() {
    }

    public Account(String firstName, String lastName, String password, String address, String avatar, int gender, String email, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
