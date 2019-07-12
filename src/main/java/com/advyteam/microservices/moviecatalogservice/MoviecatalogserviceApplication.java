package com.advyteam.microservices.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class MoviecatalogserviceApplication {

    @Bean
    @LoadBalanced // it uses the url given as a hint to find the real service adress that it needs to call
    public RestTemplate getRestTemplate(){ return new RestTemplate(); }

//    @Bean
//    public WebClient.Builder getWebClientBuilder (){
//
//        return   WebClient.builder();
//    }

    public static void main(String[] args) {
        SpringApplication.run(MoviecatalogserviceApplication.class, args);
    }

}
