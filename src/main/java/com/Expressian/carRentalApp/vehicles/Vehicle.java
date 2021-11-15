package com.Expressian.carRentalApp.vehicles;


import com.Expressian.carRentalApp.stores.Store;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;
    private String make;
    private String model;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "developer_id", referencedColumnName = "id")
    private Store store;


    public Vehicle(){}

    public Vehicle(String make, String model, Integer price){
        this.make = make;
        this.model = model;
        this.price = price;
    }

    public Vehicle(Long id, String make, String model, Integer price){
        this.id = id;
        this.make = make;
        this.model = model;
        this.price = price;
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


    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
