package com.Expressian.carRentalApp.vehicles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin
@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    VehicleRepository repository;


    private final AtomicLong idCount = new AtomicLong();

//      Map<Long, Vehicle> repository = new HashMap<>();
//    public VehicleController(){
//        Long id = idCount.incrementAndGet();
//        repository.save(new Vehicle(id, "Test", "Car", 42));
//    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle newVehicle){
        Long id = idCount.incrementAndGet();
        newVehicle.setId(id);
        return repository.save(newVehicle);
    }

    @GetMapping
    public List<Vehicle> getVehicles() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Vehicle getOneVehicle(@PathVariable Long id){
        return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedVehicle){
        if(updatedVehicle.getMake() != null)
            repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)).setMake(updatedVehicle.getMake());
        if(updatedVehicle.getModel() != null)
            repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)).setModel(updatedVehicle.getModel());
        if(updatedVehicle.getPrice() != null)
            repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)).setPrice(updatedVehicle.getPrice());

        return repository.save(repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable Long id){
        Vehicle deletedVehicle = repository.getById(id);
        repository.deleteById(id);
        return deletedVehicle.getMake() + " " + deletedVehicle.getModel() + " was deleted!";
    }

}
