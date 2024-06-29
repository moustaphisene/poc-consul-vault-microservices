package sn.microservices.orderservice.model;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Product {
    private Long id;
    private String name;
    private double price;
    private int quantity;

}
