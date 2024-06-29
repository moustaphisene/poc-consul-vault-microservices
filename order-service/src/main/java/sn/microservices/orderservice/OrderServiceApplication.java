package sn.microservices.orderservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import sn.microservices.orderservice.entities.Order;
import sn.microservices.orderservice.entities.ProductItem;
import sn.microservices.orderservice.enums.OrderStatus;
import sn.microservices.orderservice.model.Customer;
import sn.microservices.orderservice.model.Product;
import sn.microservices.orderservice.repositories.OrderRepository;
import sn.microservices.orderservice.repositories.ProductItemRepository;
import sn.microservices.orderservice.services.CustomerRestClientService;
import sn.microservices.orderservice.services.InventoryRestClientService;


import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(OrderRepository orderRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClientService customerRestClientService,
							InventoryRestClientService	inventoryRestClientService) {
		return args -> {
			List<Customer> customers=customerRestClientService.allCustomers();
			List<Product> products = inventoryRestClientService.allProducts().getContent().stream().toList();
			Long customerId=1L;
			Random random = new Random();
			Customer customer=customerRestClientService.customerById(customerId);
			for (int i=0;i<20;i++) {
				Order order= Order.builder()
						.customerId(customers.get(random.nextInt(customers.size())).getId())
						.status(Math.random()>0.5? OrderStatus.PENDING:OrderStatus.CREATED)
						.createdAt(new Date())
						.build();
				Order savedOrder=orderRepository.save(order);

				for (int j=0;j< products.size();j++) {
					if (Math.random()>0.70) {
						ProductItem productItem = ProductItem.builder()
								.order(savedOrder)
								.productId(products.get(j).getId())
								.price(products.get(j).getPrice())
								.quantity(1+random.nextInt(100))
								.discount(Math.random())
								.build();
						productItemRepository.save(productItem);

					}

				}
			}

		};
	}
}
