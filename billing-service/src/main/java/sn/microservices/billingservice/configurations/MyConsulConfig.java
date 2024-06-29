package sn.microservices.billingservice.configurations;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "token")
@Data
public class MyConsulConfig {
    private Long accessTokenTimeout;
    private Long refreshTokenTimeout;
}
