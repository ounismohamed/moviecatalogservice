package com.advyteam.microservices.moviecatalogservice.ressources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.apache.el.lang.ELArithmetic.add;


@RestController
@RequestMapping("/catalog")
public class CatalogRessources {

@GetMapping ("/{userID}")
public ArrayList<CatalogItem> getCatalogItems(@PathVariable ("userID") String userId) {

    ArrayList<CatalogItem> catalogItems= new ArrayList<CatalogItem>();
    catalogItems.add(new CatalogItem("Marvel","Test Description",4));

    return catalogItems;
}
}
