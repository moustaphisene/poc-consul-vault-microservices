package sn.microservices.orderservice.model;


import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
