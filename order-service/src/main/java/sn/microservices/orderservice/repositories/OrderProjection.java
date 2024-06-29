package sn.microservices.orderservice.repositories;

import org.springframework.data.rest.core.config.Projection;
import sn.microservices.orderservice.entities.Order;
import sn.microservices.orderservice.enums.OrderStatus;

@Projection(name = "fullOrder", types = {Order.class})

public interface OrderProjection {
    Long getId();
    String getCreatedAt();
    String getCustomerId();
    OrderStatus getOrderStatus();
}
