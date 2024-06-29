package sn.microservices.orderservice.web;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sn.microservices.orderservice.entities.Order;
import sn.microservices.orderservice.model.Customer;
import sn.microservices.orderservice.model.Product;
import sn.microservices.orderservice.repositories.OrderRepository;
import sn.microservices.orderservice.repositories.ProductItemRepository;
import sn.microservices.orderservice.services.CustomerRestClientService;
import sn.microservices.orderservice.services.InventoryRestClientService;

import java.util.concurrent.CompletableFuture;

@RestController
public class OrderRestController {

    private static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    private final OrderRepository orderRepository;
    private final InventoryRestClientService inventoryRestClientService;
    private final CustomerRestClientService customerRestClientService;

    @Autowired
    public OrderRestController(OrderRepository orderRepository,
                               ProductItemRepository productItemRepository,
                               InventoryRestClientService inventoryRestClientService,
                               CustomerRestClientService customerRestClientService) {
        this.orderRepository = orderRepository;
        this.inventoryRestClientService = inventoryRestClientService;
        this.customerRestClientService = customerRestClientService;
    }

    @GetMapping("/fullOrder/{id}")
    @CircuitBreaker(name = "orderService", fallbackMethod = "fallbackGetOrder")
    @Retry(name = "orderService")
    @RateLimiter(name = "orderService")
    @TimeLimiter(name = "orderService")
    public CompletableFuture<Order> getOrder(@PathVariable Long id) {
        try {
            Order order = orderRepository.findById(id).orElseThrow(() -> new Exception("Order not found"));
            Customer customer = customerRestClientService.customerById(order.getCustomerId());
            order.setCustomer(customer);

            order.getProductItems().forEach(pi -> {
                Product product = inventoryRestClientService.productById(pi.getProductId());
                pi.setProduct(product);
            });
            return CompletableFuture.completedFuture(order);

        } catch (Exception e) {
            logger.error("Error fetching order details for order id: " + id, e);
            throw new RuntimeException("Error fetching order details for order id: " + id, e);

        }

    }
}
