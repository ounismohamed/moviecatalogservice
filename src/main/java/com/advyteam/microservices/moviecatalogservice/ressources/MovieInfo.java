package com.advyteam.microservices.moviecatalogservice.ressources;

import com.advyteam.microservices.moviecatalogservice.models.CatalogItem;
import com.advyteam.microservices.moviecatalogservice.models.Movie;
import com.advyteam.microservices.moviecatalogservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {
    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service:8082/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getMovieName(), "Description 1 ", rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie Name not found","",rating.getRating());
    }
}

