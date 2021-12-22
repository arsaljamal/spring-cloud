package com.arsal.vehicleservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleWebServices {

    Logger logger = LoggerFactory.getLogger(VehicleWebServices.class);

    private final VehicleRepository vehicleRepository;

    public VehicleWebServices(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        logger.info("All Vehicle Call Invoked!");
        return vehicleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicle(@PathVariable("id") long id) {
        return vehicleRepository.getById(id);
    }
}
