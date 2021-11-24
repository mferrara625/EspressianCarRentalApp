package com.Expressian.carRentalApp.locations;

import com.Expressian.carRentalApp.customers.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;
    private AtomicLong locationIdCounter = new AtomicLong();

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location newLocation){
        Long id = locationIdCounter.incrementAndGet();
        newLocation.setId(id);
        return new ResponseEntity<>(locationRepository.save(newLocation), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Location getOneById(@PathVariable Long id){
        return locationRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Location updateLocationById(@PathVariable Long id, @RequestBody Location update){
        Location location = locationRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(update.getNamedID() != null)
            location.setNamedID(update.getNamedID());
        if(update.getVehicle() != null)
            location.setVehicle(update.getVehicle());

        return locationRepository.save(location);
    }

    @DeleteMapping("/{id}")
    public String deleteLocation(@PathVariable Long id){
        locationRepository.deleteById(id);
        return "Location Deleted";
    }
}
