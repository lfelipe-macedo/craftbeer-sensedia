package com.beerhouse.service.impl;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository repository;

    @Autowired
    public BeerServiceImpl(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Beer> find() {
        return repository.findAll();
    }

    @Override
    public Beer get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Beer with id " + id + " not found"));
    }

    @Override
    public Beer create(Beer beer) {
        return repository.save(beer);
    }

    @Override
    public Beer update(Long id, Beer updatedBeer) {
        Beer beer = get(id);
        beer.populate(updatedBeer);

        return repository.save(beer);
    }

    @Override
    public Beer patch(Long id, Beer updatedBeer) {
        Beer beer = get(id);
        beer.populate(updatedBeer);

        return repository.save(beer);
    }

    @Override
    public void remove(Long id) {
        Beer beer = get(id);

        repository.delete(beer);
    }
}
