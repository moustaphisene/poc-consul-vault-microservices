package sn.microservices.orderservice.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.config.HypermediaMappingInformation;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class CustomFeignConfig {
    @Bean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        return new ResponseEntityDecoder(new SpringDecoder(() -> new HttpMessageConverters(converter)));
    }
}
