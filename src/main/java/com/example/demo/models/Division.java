package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "division")
public class Division {
    @Id
    @Column (name = "id") //nama database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column (name = "name", nullable = false)
    private String Name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", referencedColumnName = "id") //Relasi dari region ke divisi. Banyak divisi ke satu region
    private Region region;

    public Division() {

    }

    /**
     * @return int return the divisionId
     */
    public Integer getId() {
        return Id;
    }

    /**
     * @param divisionId the divisionId to set
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     * @return String return the divisionName
     */
    public String getName() {
        return Name;
    }

    /**
     * @param divisionName the divisionName to set
     */
    public void setName(String divisionName) {
        this.Name = divisionName;
    }

    /**
     * @return int return the regionId
     */
    public Region getRegion() {
        return region;
    }

    /**
     * @param regionId the regionId to set
     */
    public void setRegion(Region region) {
        this.region = region;
    }

}
