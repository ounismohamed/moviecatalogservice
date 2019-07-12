package com.advyteam.microservices.moviecatalogservice.ressources;

import com.advyteam.microservices.moviecatalogservice.models.CatalogItem;
import com.advyteam.microservices.moviecatalogservice.models.Movie;
import com.advyteam.microservices.moviecatalogservice.models.Rating;
import com.advyteam.microservices.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/catalog")
public class CatalogRessources {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webclientbuilder;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo ;



    @GetMapping("/{userID}")
    //   @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalogItems(@PathVariable("userID") String userId) {
// Get the ratings
       UserRating ratings = userRatingInfo.getUserRating(userId);
// For every rating we get the movie info and we construct the catalog item

        /* // if we somehow want to use webclient instead of restTemplate
        Movie movie = webclientbuilder.build()
                .get()
                .uri("http://localhost:8083/ratings/users/" + userId)
                .retrieve()
                .bodyToMono(Movie.class)
                .block(); // eliminate the block option if we need the request to be asychronious
                */

        return ratings.getUserRAting().stream().map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList()); }



/*
    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
        return Arrays.asList(new CatalogItem("no movie", "", 0));
    }
    */



}
