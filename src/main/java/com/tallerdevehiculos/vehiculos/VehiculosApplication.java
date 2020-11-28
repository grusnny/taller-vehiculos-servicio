package com.tallerdevehiculos.vehiculos;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.tallerdevehiculos.vehiculos.entity.Vehicle;
import com.tallerdevehiculos.vehiculos.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://tallerdevehiculos.s3-website-us-east-1.amazonaws.com/"})
@SpringBootApplication
@RestController
@RequestMapping(value = "/vehicles")
public class VehiculosApplication {

    @Autowired

    private VehicleRepository repository;

    @GetMapping("/{licensePlate}")
    public Vehicle GetVehicle(@PathVariable String licensePlate){
        return repository.findVehicleById(licensePlate);
    }
    @PostMapping
    public Vehicle PostVehicle(@RequestBody Vehicle vehicle){
        return repository.addVehicle(vehicle);
    }
    @PutMapping
    public String UpdateVehicle(@RequestBody Vehicle vehicle){
        return repository.editVehicle(vehicle);
    }
    @DeleteMapping
    public String DeleteVehicle(@RequestBody Vehicle vehicle){
        return repository.deleteVehicle(vehicle);
    }
    @GetMapping
    public PaginatedScanList<Vehicle> GetAllVehicles(){
        return repository.findAllVehicles();
    }

    public static void main(String[] args) {
        SpringApplication.run(VehiculosApplication.class, args);
    }

}
