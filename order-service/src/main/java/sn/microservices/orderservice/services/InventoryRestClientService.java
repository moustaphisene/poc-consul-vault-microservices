package sn.microservices.orderservice.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sn.microservices.orderservice.model.Product;

import java.util.Optional;



@FeignClient(name = "inventory-service")
public interface InventoryRestClientService {

 /*   @Autowired
    CustomerRestClientService customerRestClientService = null;

    @Autowired
    InventoryRestClientService inventoryRestClientService = null;
*/
    @CircuitBreaker(name = "InventoryService", fallbackMethod = "fallbackGetProductById")
    @Retry(name = "InventoryService")
    @RateLimiter(name = "orderService")
    @TimeLimiter(name = "orderService")
    @GetMapping("/products/{id}?projection=fullProduct")
    public Product productById(@PathVariable Long id);

    @GetMapping("/products?projection=fullProduct")
    public PagedModel<Product> allProducts();

  /*  public default Product fallbackGetProductById(Long id, Throwable throwable) {
        // Fallback method logic
        return new Product();*/
//    }

}
