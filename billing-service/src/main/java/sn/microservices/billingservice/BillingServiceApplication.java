package sn.microservices.billingservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.vault.core.VaultTemplate;

import java.util.Map;

@SpringBootApplication
public class BillingServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(BillingServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(VaultTemplate vaultTemplate) {
        return args -> {
            try {
                vaultTemplate.opsForVersionedKeyValue("secret")
                        .put("secure", Map.of("privateKey", "54321", "publicKey", "54321"));
                logger.info("Keys stored successfully");
            } catch (Exception e) {
                logger.error("Failed to store keys: {}", e.getMessage());
            }
        };
    }
}
