package com.Expressian.carRentalApp.customers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerRepository repository;
    private AtomicLong customerIdCounter = new AtomicLong();

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer){
        Long id = customerIdCounter.incrementAndGet();
        newCustomer.setId(id);
        return new ResponseEntity<>(repository.save(newCustomer), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return repository.findAll();
    }

    @GetMapping("/name/{lastName}")
    public List<Customer> getAllByLastName(@PathVariable String lastName){
        return repository.findAllBylastName(lastName);
    }

    @GetMapping("/{id}")
    public Customer getOneCustomer(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer update){
        Customer customer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(update.getFirstName() != null)
            customer.setFirstName(update.getFirstName());
        if(update.getLastName() != null)
            customer.setLastName(update.getLastName());
        if(update.getEmail() != null)
            customer.setEmail(update.getEmail());


        return repository.save(customer);

    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id){
        String result = repository.getById(id).getFirstName() + " was deleted from the system.";
        repository.deleteById(id);
        return result;
    }

}
