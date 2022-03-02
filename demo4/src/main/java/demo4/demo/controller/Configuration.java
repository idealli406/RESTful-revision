package demo4.demo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration

public class Configuration {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }




}
