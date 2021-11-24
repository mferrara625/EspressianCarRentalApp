package com.Expressian.carRentalApp.vehicles;


import com.Expressian.carRentalApp.locations.Location;
import com.Expressian.carRentalApp.rentals.Rental;
import com.Expressian.carRentalApp.stores.Store;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;
    private String make;
    private String model;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Store store;

    @OneToMany
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Set<Rental> rentals;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;


    public Vehicle(){}

    public Vehicle(String make, String model, Integer price, Store store, Location location) {
        this.make = make;
        this.model = model;
        this.price = price;
        this.store = store;
        this.location = location;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(Set<Rental> rentals) {
        this.rentals = rentals;
    }

}
