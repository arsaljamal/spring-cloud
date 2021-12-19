package com.arsal.reservationservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    public ReservationController(ReservationRepository reservationRepository, ReservationService reservationService) {
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    @GetMapping(path = "/customers")
    public List<Customer> getAllCustomers() {
        return reservationService.getAllCustomers();
    }

    @GetMapping(path = "/vehicles")
    public List<Vehicle> getAllVehicles() {
        return reservationService.getAllVehicles();
    }

    @GetMapping
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }
}
