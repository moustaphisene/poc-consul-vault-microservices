package sn.microservices.orderservice.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sn.microservices.orderservice.model.Customer;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerRestClientService {

    @CircuitBreaker(name = "customerService", fallbackMethod = "fallbackCustomerById")
    @Retry(name = "customerService")
    @GetMapping("/customers/{id}?projection=fullCustomer")
    public  Customer customerById(@PathVariable Long id);

    @CircuitBreaker(name = "customerService", fallbackMethod = "fallbackAllCustomers")
    @Retry(name = "customerService")
    @GetMapping("/customers?projection=fullCustomer")
    public List<Customer> allCustomers();


}




