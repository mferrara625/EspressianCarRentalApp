package com.Expressian.carRentalApp.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.lang.model.element.Name;
import java.lang.reflect.Field;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    @Query
    List<Customer> findAllByName(String name);
}
