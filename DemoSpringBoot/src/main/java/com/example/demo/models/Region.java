package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "region") //nama table di db
public class Region {
    @Id //penunjuk bahwa ini primary key    
    @Column(name = "id") //mengikuti field name di db
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Integer Id;
    
    @Column(name = "name", nullable = false)
    private String Name;
    // @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true) //relasi table


    /**
     * @return int return the regionId
     */
    public Integer getId() {
        return Id;
    }

    /**
     * @param regionId the regionId to set
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     * @return String return the regionName
     */
    public String getName() {
        return Name;
    }

    /**
     * @param regionName the regionName to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

}
