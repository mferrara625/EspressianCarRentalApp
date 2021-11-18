package com.Expressian.carRentalApp.vehicles;

import com.Expressian.carRentalApp.locations.Location;
import com.Expressian.carRentalApp.locations.LocationRepository;
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
    VehicleRepository vehicleRepository;

    @Autowired
    LocationRepository locationRepository;


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
        return vehicleRepository.save(newVehicle);
    }

    @PostMapping("/location")
    public Vehicle addLocation(@RequestBody Vehicle vehicle){

        Vehicle vehicle1 = vehicleRepository.findById(vehicle.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(vehicle1.getLocation() != null)
            locationRepository.delete(vehicle1.getLocation());

        Location location = locationRepository.save(vehicle.getLocation());

        vehicle1.setLocation(location);

        return vehicleRepository.save(vehicle1);
    }

    @GetMapping
    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vehicle getOneVehicle(@PathVariable Long id){
        return vehicleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedVehicle){
        if(updatedVehicle.getMake() != null)
            vehicleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)).setMake(updatedVehicle.getMake());
        if(updatedVehicle.getModel() != null)
            vehicleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)).setModel(updatedVehicle.getModel());
        if(updatedVehicle.getPrice() != null)
            vehicleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)).setPrice(updatedVehicle.getPrice());
        if(updatedVehicle.getLocation() != null)
            vehicleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return vehicleRepository.save(vehicleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable Long id){
        Vehicle deletedVehicle = vehicleRepository.getById(id);
        vehicleRepository.deleteById(id);
        return deletedVehicle.getMake() + " " + deletedVehicle.getModel() + " was deleted!";
    }

}
