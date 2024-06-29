package sn.microservices.orderservice.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.microservices.orderservice.enums.OrderStatus;
import sn.microservices.orderservice.model.Customer;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "orders")
@Data @AllArgsConstructor @NoArgsConstructor  @Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private OrderStatus status;
    private Long customerId;
    @Transient
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;

    public double getTotal(){
        double somme =0;
        for (ProductItem productItem : productItems) {
            somme += productItem.getAmount();
        }
        return somme;
    }
}
