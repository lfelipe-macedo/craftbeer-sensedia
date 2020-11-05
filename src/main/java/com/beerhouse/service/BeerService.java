package com.beerhouse.service;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.dto.BeerRequest;

import java.util.List;

public interface BeerService {

    List<Beer> find();

    Beer get(Long id);

    Beer create(Beer beer);

    Beer update(Long id, Beer updatedBeer);

    Beer patch(Long id, Beer updatedBeer);

    void remove(Long id);
}
