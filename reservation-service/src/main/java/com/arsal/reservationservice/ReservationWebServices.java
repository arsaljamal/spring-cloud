package com.arsal.reservationservice;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/reservations")
public class ReservationWebServices {

    private final ReservationRepository reservationRepository;
    private final RestTemplate restTemplate;

    public ReservationWebServices(ReservationRepository reservationRepository, RestTemplate restTemplate) {
        this.reservationRepository = reservationRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping(path = "/customers")
    public List<Customer> getAllCustomers() {
        ResponseEntity<List<Customer>> customerResponse = this.restTemplate.exchange
                ("http://CUSTOMERSERVICES/customers", HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
        });
        return customerResponse.getBody();
    }

    @GetMapping(path = "/vehicles")
    public List<Vehicle> getAllVehicles() {
        ResponseEntity<List<Vehicle>> vehicleResponse = this.restTemplate.exchange
                ("http://VEHICLESERVICES/vehicles", HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return vehicleResponse.getBody();
    }

    @GetMapping
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }
}
