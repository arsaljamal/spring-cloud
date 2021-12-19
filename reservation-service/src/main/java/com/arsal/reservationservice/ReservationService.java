package com.arsal.reservationservice;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReservationService {

    private final RestTemplate restTemplate;
    private final LoadBalancerClient loadBalancer;

    public ReservationService(RestTemplate restTemplate, LoadBalancerClient loadBalancer) {
        this.restTemplate = restTemplate;
        this.loadBalancer = loadBalancer;
    }

    @HystrixCommand(fallbackMethod = "getDefaultCustomerList")
    public List<Customer> getAllCustomers() {
        String baseUrl="http://customerservices/customers";
        ResponseEntity<List<Customer>> customerResponse = this.restTemplate.exchange
                (baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return customerResponse.getBody();
    }

    public List<Customer> getDefaultCustomerList() {
        return List.of(new Customer(-99,"Default","Default"));
    }

    @HystrixCommand(fallbackMethod = "getDefaultVehicleList")
    public List<Vehicle> getAllVehicles() {
        String baseUrl="http://vehicleservices/vehicles";
        ResponseEntity<List<Vehicle>> vehicleResponse = this.restTemplate.exchange
                (baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return vehicleResponse.getBody();
    }

    public List<Vehicle> getDefaultVehicleList() {
        return List.of(new Vehicle(-99,"Default","Default"));
    }
}
