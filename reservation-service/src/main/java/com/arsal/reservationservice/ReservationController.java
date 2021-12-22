package com.arsal.reservationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/reservations")
public class ReservationController {

    Logger logger = LoggerFactory.getLogger(ReservationController.class);

    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    public ReservationController(ReservationRepository reservationRepository, ReservationService reservationService) {
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    @GetMapping(path = "/customers")
    public List<Customer> getAllCustomers() {
        logger.info("Invoking /customers call from reservation");
        return reservationService.getAllCustomers();
    }

    @GetMapping(path = "/vehicles")
    public List<Vehicle> getAllVehicles() {
        logger.info("Invoking /vehicles call from reservation");
        return reservationService.getAllVehicles();
    }

    @GetMapping
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }
}
