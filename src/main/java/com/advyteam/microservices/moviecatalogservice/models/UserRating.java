package com.advyteam.microservices.moviecatalogservice.models;

import java.util.List;

public class UserRating {
    public UserRating() {
    }

    private List<Rating> userRAting;

    public List<Rating> getUserRAting() {
        return userRAting;
    }

    public void setUserRAting(List<Rating> userRAting) {
        this.userRAting = userRAting;
    }

}
