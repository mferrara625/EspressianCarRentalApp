package com.Expressian.carRentalApp.locations;

import com.Expressian.carRentalApp.vehicles.Vehicle;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Location {

    @Id
    @GeneratedValue
    private Long id;
    private String namedID;
    @OneToOne(mappedBy = "location")
    private Vehicle vehicle;

    public Location(){

    }

    public Location(String namedID, Vehicle vehicle) {
        this.namedID = namedID;
        this.vehicle = vehicle;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
