package sn.microservices.inventoryservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sn.microservices.inventoryservice.entities.Product;
import sn.microservices.inventoryservice.repositories.ProductRepository;

@RestController
public class InventoryRestController {

    private final ProductRepository productRepository;

    @Autowired
    public InventoryRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
