package com.example.demo.models;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id")
    private Integer Id;

    @Column (name = "full_Name", nullable = false)
    private String FullName;

    @Column (name = "email", nullable = false, unique = true)
    private String Email;

    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // @Column (name = "birth_Date", nullable = false)
    private Date BirthDate;


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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }
    
}
