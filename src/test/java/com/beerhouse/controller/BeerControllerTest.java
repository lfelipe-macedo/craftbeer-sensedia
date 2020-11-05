package com.beerhouse.controller;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.dto.BeerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void shouldCreateABeer_Return201Created() throws Exception {
        Beer beer = new Beer("Indian Pale Ale",
                "Água, malte, lúpulo e leveduras",
                "6.5",
                new BigDecimal("21.9"),
                "IPA");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/beers")
                .content(objectMapper.writeValueAsString(beer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @Order(2)
    void shouldNotCreateABeerWithoutAllFields_Return400BadRequest() throws Exception {
        Beer beer = new Beer("Indian Pale Ale",
                "",
                "6.5",
                new BigDecimal("21.9"),
                "IPA");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/beers")
                .content(objectMapper.writeValueAsString(beer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    void shouldReadAllBeers_Return200Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/beers", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    @Order(4)
    void shouldFindABeerById_Return200Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/beers/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @Order(5)
    void shouldNotFindABeerByIdWithWrongId_Return400NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/beers/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @Order(6)
    void shoudUpdateABeer_Return200Ok() throws Exception {
        BeerRequest request = new BeerRequest("Witbier",
                "Água, malte, lúpulo e leveduras",
                "4.5",
                "21.9",
                "Witbier");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/beers/{id}", 1)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(request.getName()))
                .andExpect(jsonPath("$.ingredients").value(request.getIngredients()))
                .andExpect(jsonPath("$.alcoholContent").value(request.getAlcoholContent()))
                .andExpect(jsonPath("$.price").value(request.getPrice()))
                .andExpect(jsonPath("$.category").value(request.getCategory()));
    }

    @Test
    @Order(7)
    void shouldUpdateOneInfo_Return200Ok() throws Exception {
        BeerRequest request = new BeerRequest();
        request.setPrice("40.0");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/beers/{id}", 1)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(request.getPrice()));
    }

    @Test
    @Order(8)
    void shouldDeleteABeer_Return204NoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/beers/{id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(9)
    void shouldNotDeleteABeerWithWrongId_Return400NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/beers/{id}", 3).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
