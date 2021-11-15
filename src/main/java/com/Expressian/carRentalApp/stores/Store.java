package com.Expressian.carRentalApp.stores;

import com.Expressian.carRentalApp.vehicles.Vehicle;

import javax.persistence.*;
import java.util.List;

@Entity
public class Store {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String location;
    @OneToMany
    @JoinColumn(name = "developer_id", referencedColumnName = "id")
    List<Vehicle> vehicleList;

    public Store(){

    }

    public Store(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Store(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
