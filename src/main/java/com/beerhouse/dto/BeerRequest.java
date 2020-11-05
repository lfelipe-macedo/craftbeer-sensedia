package com.beerhouse.dto;

import com.beerhouse.domain.entity.Beer;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class BeerRequest {

    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Ingredients cannot be empty")
    private String ingredients;
    @NotBlank(message = "Alcohol Content cannot be empty")
    private String alcoholContent;
    @DecimalMin(value = "0.0")
    private String price;
    @NotBlank(message = "Category cannot be empty")
    private String category;

    public BeerRequest() {
    }

    public BeerRequest(@NotBlank(message = "Name cannot be empty") String name, @NotBlank(message = "Ingredients cannot be empty") String ingredients, @NotBlank(message = "Alcohol Content cannot be empty") String alcoholContent, @NotBlank(message = "Price cannot be empty") String price, @NotBlank(message = "Category cannot be empty") String category) {
        this.name = name;
        this.ingredients = ingredients;
        this.alcoholContent = alcoholContent;
        this.price = price;
        this.category = category;
    }

    public BeerRequest(Beer beer) {
        this.name = beer.getName();
        this.ingredients = beer.getIngredients();
        this.alcoholContent = beer.getAlcoholContent();
        this.price = beer.getPrice().toString();
        this.category = beer.getCategory();
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Beer toBeer() {
        Beer beer = new Beer();
        beer.setName(this.name);
        beer.setPrice(new BigDecimal(this.price));
        beer.setIngredients(this.ingredients);
        beer.setAlcoholContent(this.alcoholContent);
        beer.setCategory(this.category);

        return beer;
    }
}
