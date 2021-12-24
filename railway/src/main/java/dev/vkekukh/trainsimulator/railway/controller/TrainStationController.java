package dev.vkekukh.trainsimulator.railway.controller;

import dev.vkekukh.trainsimulator.railway.assembler.TrainStationAssembler;
import dev.vkekukh.trainsimulator.railway.exception.NotFoundException;
import dev.vkekukh.trainsimulator.railway.exception.ValidationException;
import dev.vkekukh.trainsimulator.railway.model.TrainStation;
import dev.vkekukh.trainsimulator.railway.service.TrainStationService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TrainStationController {

    private final TrainStationService trainStationService;
    private final TrainStationAssembler assembler;


    public TrainStationController(TrainStationService trainStationService, TrainStationAssembler assembler) {
        this.trainStationService = trainStationService;
        this.assembler = assembler;
    }


    @GetMapping("/trainstations/{id}")
    public EntityModel<TrainStation> one(@PathVariable Long id) {

        TrainStation trainStation = trainStationService.findById(id)
                .orElseThrow(() -> new NotFoundException("Train station with id:" + id + " was not found"));


        return assembler.toModel(trainStation);
    }

    @GetMapping(value = "/trainstations", params = "city")
    public CollectionModel<EntityModel<TrainStation>> searchByCity(@RequestParam String city) {

        List<EntityModel<TrainStation>> trainstations = trainStationService.findByCity(city).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(trainstations, linkTo(methodOn(TrainStationController.class).all()).withSelfRel());
    }

    @GetMapping(value = "/trainstations", params = "name")
    public CollectionModel<EntityModel<TrainStation>> searchByName(@RequestParam String name) {

        List<EntityModel<TrainStation>> trainstations = trainStationService.findByName(name).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(trainstations, linkTo(methodOn(TrainStationController.class).all()).withSelfRel());
    }

    @GetMapping(value = "/trainstations", params = {"name", "city"})
    public EntityModel<TrainStation> searchByNameAndCity(@RequestParam String city, @RequestParam String name) {

        TrainStation trainstation = trainStationService.findByCityAndName(city, name)
                .orElseThrow(() -> new NotFoundException("Train Station " + name + " was not found in " + city));

        return assembler.toModel(trainstation);
    }

    @GetMapping("/trainstations")
    public CollectionModel<EntityModel<TrainStation>> all() {

        List<EntityModel<TrainStation>> trainstations = trainStationService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(trainstations, linkTo(methodOn(TrainStationController.class).all()).withSelfRel());
    }

    @PostMapping("/trainstations")
    ResponseEntity<?> newTrainStation(@RequestBody TrainStation trainStation) throws ValidationException {

        EntityModel<TrainStation> entityModel = assembler.toModel(trainStationService.save(trainStation));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/trainstations/{id}")
    ResponseEntity<?> deleteTrainStation(@PathVariable Long id) {

        trainStationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
