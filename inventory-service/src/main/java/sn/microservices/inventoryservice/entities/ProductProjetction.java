package sn.microservices.inventoryservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullProduct", types = {Product.class})
public interface ProductProjetction {
    public Long getId();
    public String getName();
    public String getDescription();
    public double getPrice();
    public int getQuantity();

}
