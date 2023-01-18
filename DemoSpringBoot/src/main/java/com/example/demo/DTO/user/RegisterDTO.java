package com.example.demo.DTO.user;

import java.util.Date;

public class RegisterDTO {
    private Integer Id;
    private String FullName;
    private String email;
    private Date BirthDate;
    private String Password;


    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getFullName() {
        return FullName;
    }
    public void setFullName(String fullName) {
        FullName = fullName;
    }
    public String getemail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getBirthDate() {
        return BirthDate;
    }
    public void setBirthDate(Date BirthDate) {
        this.BirthDate = BirthDate;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    
}
