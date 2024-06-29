package sn.microservices.customerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.microservices.customerservice.entities.Customer;


@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
