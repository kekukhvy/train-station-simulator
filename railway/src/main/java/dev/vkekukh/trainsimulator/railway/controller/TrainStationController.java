package dev.vkekukh.trainsimulator.railway.controller;

import dev.vkekukh.trainsimulator.railway.exception.NotFoundException;
import dev.vkekukh.trainsimulator.railway.model.TrainStation;
import dev.vkekukh.trainsimulator.railway.service.TrainStationService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TrainStationController {

    private final TrainStationService trainStationService;

    public TrainStationController(TrainStationService trainStationService) {
        this.trainStationService = trainStationService;
    }

    @GetMapping("/trainstations/{id}")
    EntityModel<TrainStation> one(@PathVariable Long id) {

        TrainStation trainStation = trainStationService.findById(id)
                .orElseThrow(() -> new NotFoundException("Train with id:" + id + " was not found"));


        return EntityModel.of(trainStation,
                linkTo(methodOn(TrainStationController.class).one(id)).withSelfRel(),
                linkTo(methodOn(TrainStationController.class).all()).withRel("trainstations"));
    }

    @GetMapping("/trainstations")
    CollectionModel<EntityModel<TrainStation>> all() {

        List<EntityModel<TrainStation>> trainstations = trainStationService.findAll().stream()
                .map(trainStation -> EntityModel.of(trainStation,
                        linkTo(methodOn(TrainStationController.class).one(trainStation.getId())).withSelfRel(),
                        linkTo(methodOn(TrainStationController.class).all()).withRel("trainstations")))
                .collect(Collectors.toList());

        return CollectionModel.of(trainstations, linkTo(methodOn(TrainStationController.class).all()).withSelfRel());
    }
}
