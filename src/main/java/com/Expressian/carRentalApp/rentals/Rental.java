package com.Expressian.carRentalApp.rentals;


import com.Expressian.carRentalApp.customers.Customer;
import com.Expressian.carRentalApp.vehicles.Vehicle;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Rental {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    private Long numDaysRented;

    public Rental(){

    }

    public Rental(Customer customer, Vehicle vehicle, Long numDaysRented) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.numDaysRented = numDaysRented;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Long getNumDaysRented() {
        return numDaysRented;
    }

    public void setNumDaysRented(Long numDaysRented) {
        this.numDaysRented = numDaysRented;
    }
}
