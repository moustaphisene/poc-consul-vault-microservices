package sn.microservices.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import sn.microservices.customerservice.entities.Customer;
import sn.microservices.customerservice.repositories.CustomerRepository;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.saveAll(List.of(
                    Customer.builder()
                            .firstName("Mohamed")
                            .lastName("SENE")
                            .email("mohamed@gmail.com")
                            .phoneNumber("774455558").build(),
                    Customer.builder()
                            .firstName("Moustapha")
                            .lastName("SENE")
                            .email("moustapha@gmail.com")
                            .phoneNumber("774455559").build(),
                    Customer.builder()
                            .firstName("Soxna Diarra")
                            .lastName("SENE")
                            .email("diarra@gmail.com")
                            .phoneNumber("774455557").build()
            ));
           customerRepository.findAll().forEach(System.out::println);
        };
    }
}
