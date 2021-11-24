package com.Expressian.carRentalApp.rentals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    RentalRepository rentalRepository;
    private AtomicLong rentalIdCounter = new AtomicLong();


    @PostMapping
    public ResponseEntity<Rental> createOne(@RequestBody Rental rental){
        rental.setId(rentalIdCounter.incrementAndGet());

        return new ResponseEntity<>(rentalRepository.save(rental), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Rental> getAllRentals(){
        return rentalRepository.findAll();
    }

    @GetMapping("/{id}")
    public Rental getOneById(@PathVariable Long id){
        return rentalRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Rental updateById(@PathVariable Long id, @RequestBody Rental update){
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(update.getCustomer() != null)
            rental.setCustomer(update.getCustomer());
        if(update.getVehicle() != null)
            rental.setVehicle(update.getVehicle());
        if(update.getNumDaysRented() != null)
            rental.setNumDaysRented(update.getNumDaysRented());

        return rentalRepository.save(rental);
    }

    @DeleteMapping("/{id}")
    public String deleteRentalById(@PathVariable Long id){
        rentalRepository.deleteById(id);
        return "Rental " + id + " Deleted From System";
    }


}
