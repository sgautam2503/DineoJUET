package com.example.dineojuet;
public class UserModel {
    private String firstname,lastname,email,enrol,password;

    public UserModel(String firstname, String lastname, String email, String enrol, String password) {
    }

    public UserModel(String firstname, String lastname, String number, String email, String enrol, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.enrol = enrol;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String fistname) {
        this.firstname = fistname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnrol() {
        return enrol;
    }

    public void setEnrol(String enrol) {
        this.enrol = enrol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
