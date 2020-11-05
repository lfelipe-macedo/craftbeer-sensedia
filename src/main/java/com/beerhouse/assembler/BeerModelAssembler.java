package com.beerhouse.assembler;

import com.beerhouse.controller.BeerController;
import com.beerhouse.domain.entity.Beer;
import com.beerhouse.dto.BeerResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BeerModelAssembler implements RepresentationModelAssembler<BeerResponse, EntityModel<BeerResponse>> {
    @Override
    public EntityModel<BeerResponse> toModel(BeerResponse beer) {
        return EntityModel.of(beer,
                linkTo(methodOn(BeerController.class).get(beer.getId())).withSelfRel(),
                linkTo(methodOn(BeerController.class).find()).withRel("beers"));
    }

    @Override
    public CollectionModel<EntityModel<BeerResponse>> toCollectionModel(Iterable<? extends BeerResponse> beers) {
        return CollectionModel.of(StreamSupport.stream(beers.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.toList()));
    }
}
