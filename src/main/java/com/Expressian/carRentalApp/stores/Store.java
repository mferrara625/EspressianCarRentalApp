package com.Expressian.carRentalApp.stores;

import com.Expressian.carRentalApp.customers.Customer;
import com.Expressian.carRentalApp.vehicles.Vehicle;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Store {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String storeLocation;
    @OneToMany
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    List<Vehicle> vehicleList;

    @ManyToMany
    @JoinTable(
        name = "store_customer",
        joinColumns = @JoinColumn(name = "store_id"),
        inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<Customer> customers;

    public Store(){

    }

    public Store(String name, String storeLocation) {
        this.name = name;
        this.storeLocation = storeLocation;
    }

    public Store(Long id, String name, String storeLocation) {
        this.id = id;
        this.name = name;
        this.storeLocation = storeLocation;
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

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }
}
