package com.Expressian.carRentalApp.locations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;
    private String namedID;

    public Location(){

    }

    public Location(String namedID){
        this.namedID = namedID;
    }

    public Location(Long id, String namedID){
        this.id = id;
        this.namedID = namedID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamedID() {
        return namedID;
    }

    public void setNamedID(String namedID) {
        this.namedID = namedID;
    }
}
