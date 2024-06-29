package sn.microservices.customerservice.web;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sn.microservices.customerservice.entities.Customer;
import sn.microservices.customerservice.repositories.CustomerRepository;

import java.util.List;
import java.util.Map;


@RestController
@RefreshScope
public class CustomerConfigTestController {

    @Value("${global.params.p1}")
    private String p1;
    @Value("${global.params.p2}")
    private String p2;
    @Value("${customer.params.x}")
    private String x;
    @Value("${customer.params.y}")
    private String y;


    @GetMapping("/params")
    public Map<String, String> Params() {
        return Map.of("p1", p1, "p2", p2, "x", x, "y", y);
    }

    private  CustomerRepository customerRepository;

    @Autowired
    public void CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @ApiOperation(value = "View a list of available customers", response = List.class)
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    @ApiOperation(value = "Get a customer by ID")
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(
            @ApiParam(value = "ID of the customer to retrieve", required = true)
            @PathVariable Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
