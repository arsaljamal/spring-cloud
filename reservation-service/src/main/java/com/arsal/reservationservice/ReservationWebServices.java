package com.arsal.reservationservice;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/reservations")
public class ReservationWebServices {

    private final ReservationRepository reservationRepository;
    private final RestTemplate restTemplate;
    private LoadBalancerClient loadBalancer;

    public ReservationWebServices(ReservationRepository reservationRepository, RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
        this.reservationRepository = reservationRepository;
        this.restTemplate = restTemplate;
        this.loadBalancer = loadBalancerClient;
    }

    @GetMapping(path = "/customers")
    public List<Customer> getAllCustomers() {

        ServiceInstance serviceInstance=loadBalancer.choose("customerservices");
        String baseUrl=serviceInstance.getUri().toString();
        baseUrl=baseUrl+"/customers";

        ResponseEntity<List<Customer>> customerResponse = this.restTemplate.exchange
                (baseUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
        });
        return customerResponse.getBody();
    }

    @GetMapping(path = "/vehicles")
    public List<Vehicle> getAllVehicles() {
        ServiceInstance serviceInstance=loadBalancer.choose("vehicleservices");
        String baseUrl=serviceInstance.getUri().toString();
        baseUrl=baseUrl+"/vehicles";

        ResponseEntity<List<Vehicle>> vehicleResponse = this.restTemplate.exchange
                (baseUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return vehicleResponse.getBody();
    }

    @GetMapping
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }
}
