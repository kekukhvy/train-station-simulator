package dev.vkekukh.trainsimulator.railway.service;


import dev.vkekukh.trainsimulator.railway.exception.NotFoundException;
import dev.vkekukh.trainsimulator.railway.exception.ValidationException;
import dev.vkekukh.trainsimulator.railway.model.TrainStation;

import java.util.List;
import java.util.Optional;

public interface TrainStationService {

    Optional <TrainStation> findByCityAndName(String city, String name);

    List<TrainStation> findByName(String name);

    List<TrainStation> findByCity(String city);

    Optional<TrainStation> findById(Long id);

    TrainStation save(TrainStation trainStation) throws ValidationException;

    void deleteById(Long id) throws NotFoundException;

    void delete(TrainStation trainStation);

    List<TrainStation> findAll();
}
