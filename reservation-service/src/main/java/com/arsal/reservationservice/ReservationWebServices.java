package com.arsal.reservationservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/reservations")
public class ReservationWebServices {

    private final ReservationRepository reservationRepository;

    public ReservationWebServices(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public List<Reservation> getAllCustomer() {
        return reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getCustomer(@PathVariable("id") long id) {
        return reservationRepository.getById(id);
    }
}
