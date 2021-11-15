package com.Expressian.carRentalApp.stores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private AtomicLong storeIDCounter = new AtomicLong();

    @Autowired
    private StoreRepository repository;

    @GetMapping
    public Iterable<Store> getAllStores(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Store getOneStore(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Store createStore(@RequestBody Store newStore){
        Long id = storeIDCounter.incrementAndGet();
        newStore.setId(id);
        return repository.save(newStore);
    }

}
