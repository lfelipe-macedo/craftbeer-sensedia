package com.beerhouse.dto;

import com.beerhouse.domain.entity.Beer;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Relation(collectionRelation = "beers")
public class BeerResponse {

    private Long id;
    private String name;
    private String ingredients;
    private String alcoholContent;
    private BigDecimal price;
    private String category;

    public BeerResponse() {
    }

    public BeerResponse(Beer beer) {
        this.id = beer.getId();
        this.name = beer.getName();
        this.ingredients = beer.getIngredients();
        this.alcoholContent = beer.getAlcoholContent();
        this.price = beer.getPrice();
        this.category = beer.getCategory();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(String alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static List<BeerResponse> toList(List<Beer> beers) {
        return beers.stream().map(BeerResponse::new).collect(Collectors.toList());
    }
}
