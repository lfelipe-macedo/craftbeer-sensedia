package com.beerhouse.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "beers")
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String ingredients;
    @Column(nullable = false)
    private String alcoholContent;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String category;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDate.now();
    }

    public Beer() {
    }

    public Beer(String name, String ingredients, String alcoholContent, BigDecimal price, String category) {
        this.name = name;
        this.ingredients = ingredients;
        this.alcoholContent = alcoholContent;
        this.price = price;
        this.category = category;
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

    public void populate(Beer newBeer) {
        name = newBeer.getName() != null ? newBeer.getName() : name;
        ingredients = newBeer.getIngredients() != null ? newBeer.getIngredients() : ingredients;
        alcoholContent = newBeer.getAlcoholContent() != null ? newBeer.getAlcoholContent() : alcoholContent;
        price = newBeer.getPrice() != null ? newBeer.getPrice() : price;
        category = newBeer.getCategory() != null ? newBeer.getCategory() : category;
    }
}
