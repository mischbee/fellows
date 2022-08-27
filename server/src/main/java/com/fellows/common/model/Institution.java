package com.fellows.common.model;

import com.fellows.common.DBEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "T_INSTITUTION")
public class Institution extends DBEntity {
    @Column(name = "NAME", columnDefinition = "TEXT", nullable = false)
    private String name;
    @Column(name = "COUNTRY", columnDefinition = "TEXT")
    private String country;
    @Column(name = "LOCALITY", columnDefinition = "TEXT")
    private String locality;

    public Institution() {
    }

    public Institution(String name, String country, String locality) {
        this.name = name;
        this.country = country;
        this.locality = locality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
