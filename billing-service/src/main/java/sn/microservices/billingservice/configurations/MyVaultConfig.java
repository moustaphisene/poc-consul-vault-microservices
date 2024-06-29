package sn.microservices.billingservice.configurations;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;

@Component
@ConfigurationProperties(prefix = "user")
@Data
public class MyVaultConfig {
    private String username;
    private String password;
    private String otp;

//New
   /* @Bean
    public VaultTemplate vaultTemplate() {
        VaultEndpoint vaultEndpoint = VaultEndpoint.create("127.0.0.1", 8200);
        TokenAuthentication tokenAuthentication = new TokenAuthentication("hvs.i9SRKuZgz0wteUaeU8Wrd0Xj");
        return new VaultTemplate(vaultEndpoint, tokenAuthentication);

}

    */
}