package dev.vkekukh.trainsimulator.railway.assembler;

import dev.vkekukh.trainsimulator.railway.controller.TrainStationController;
import dev.vkekukh.trainsimulator.railway.model.TrainStation;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TrainStationAssembler implements RepresentationModelAssembler<TrainStation, EntityModel<TrainStation>> {

    @Override
    public EntityModel<TrainStation> toModel(TrainStation trainStation) {
        return EntityModel.of(trainStation,
                linkTo(methodOn(TrainStationController.class).one(trainStation.getId())).withSelfRel(),
                linkTo(methodOn(TrainStationController.class).all()).withRel("trainstations"));
    }
}
