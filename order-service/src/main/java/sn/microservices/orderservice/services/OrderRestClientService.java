package sn.microservices.orderservice.services;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sn.microservices.orderservice.entities.Order;
import sn.microservices.orderservice.model.Product;

@FeignClient(name = "order-service")
public interface OrderRestClientService {
    @GetMapping("/orders/{id}?projection=fullOrder")
    public Order orderById(@PathVariable Long id);

    @GetMapping("/orders?projection=fullOrder")
    public PagedModel<Order> allOrders();
}
