package com.beerhouse.controller;

import com.beerhouse.assembler.BeerModelAssembler;
import com.beerhouse.domain.entity.Beer;
import com.beerhouse.dto.BeerRequest;
import com.beerhouse.dto.BeerResponse;
import com.beerhouse.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
public class BeerController {

    private final BeerService service;
    private final BeerModelAssembler assembler;

    @Autowired
    public BeerController(BeerService service, BeerModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @PostMapping("/beers")
    public ResponseEntity<EntityModel<BeerResponse>> create(@Valid @RequestBody BeerRequest request, UriComponentsBuilder uriBuilder) {
        Beer beer = service.create(request.toBeer());

        URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("{id}")
                .buildAndExpand(beer.getId())
                .toUri();

        return ResponseEntity.created(uri).body(assembler.toModel(new BeerResponse(beer)));
    }

    @GetMapping("/beers")
    public ResponseEntity<CollectionModel<EntityModel<BeerResponse>>> find() {

        List<Beer> beers = service.find();

        return ResponseEntity.ok(assembler.toCollectionModel(BeerResponse.toList(beers)));
    }

    @GetMapping("/beers/{id}")
    public ResponseEntity<EntityModel<BeerResponse>> get(@PathVariable Long id) {
        Beer beer = service.get(id);

        return ResponseEntity.ok(assembler.toModel(new BeerResponse(beer)));
    }

    @PutMapping("/beers/{id}")
    public ResponseEntity<EntityModel<BeerResponse>> update(@PathVariable Long id, @RequestBody BeerRequest request) {
        Beer beer = service.update(id, request.toBeer());

        return ResponseEntity.ok(assembler.toModel(new BeerResponse(beer)));
    }

    @PatchMapping("/beers/{id}")
    public ResponseEntity<EntityModel<BeerResponse>> patch(@PathVariable Long id, @RequestBody BeerRequest request) {
        Beer beer = service.patch(id, request.toBeer());

        return ResponseEntity.ok(assembler.toModel(new BeerResponse(beer)));
    }

    @DeleteMapping("/beers/{id}")
    @Transactional
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}
