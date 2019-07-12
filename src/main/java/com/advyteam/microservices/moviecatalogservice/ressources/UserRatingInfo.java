package com.advyteam.microservices.moviecatalogservice.ressources;

import com.advyteam.microservices.moviecatalogservice.models.Rating;
import com.advyteam.microservices.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {



    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(  fallbackMethod = "getFallbackUserRating",
    commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000") },
    threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "30"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000")
    }

    )
    public UserRating getUserRating(@PathVariable("userID") String userId) {
        return restTemplate.getForObject("http://movie-rating-service/ratings/users/" + userId, UserRating.class);
    }

    public UserRating getFallbackUserRating(@PathVariable("userID") String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserRAting(Arrays.asList(
                new Rating("0",0)
        ));
        return userRating;
    }
}
