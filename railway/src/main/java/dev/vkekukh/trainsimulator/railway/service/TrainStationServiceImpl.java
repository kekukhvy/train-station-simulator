package dev.vkekukh.trainsimulator.railway.service;

import dev.vkekukh.trainsimulator.railway.exception.NotFoundException;
import dev.vkekukh.trainsimulator.railway.exception.ValidationException;
import dev.vkekukh.trainsimulator.railway.model.TrainStation;
import dev.vkekukh.trainsimulator.railway.repository.TrainStationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrainStationServiceImpl implements TrainStationService {

    private final TrainStationRepository trainStationRepository;


    public TrainStationServiceImpl(@Autowired TrainStationRepository trainStationRepository) {
        this.trainStationRepository = trainStationRepository;
    }

    @Override
    public Optional<TrainStation> findByName(String name) {
        return trainStationRepository.findByName(name);
    }

    @Override
    public List<TrainStation> findByCity(String city) {
        return trainStationRepository.findByCity(city);
    }

    @Override
    public Optional<TrainStation> findById(Long id) {
        return trainStationRepository.findById(id);
    }

    @Override
    public List<TrainStation> findAll() {
        return trainStationRepository.findAll();
    }

    @Override
    public TrainStation saveTrainStation(TrainStation trainStation) throws ValidationException {
        if (trainStation.getCity() == null || trainStation.getName() == null)
            throw new ValidationException("City and name for train station can't be null");

        log.info("Try to save trainStation " + trainStation.getName());
        return trainStationRepository.save(trainStation);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if (trainStationRepository.findById(id).isEmpty())
            throw new NotFoundException("Station with id " + id + " was not found");

        trainStationRepository.deleteById(id);
        log.info("TrainStation with id: " + id + " was deleted");
    }

    @Override
    public void delete(TrainStation trainStation) {
        trainStationRepository.delete(trainStation);
        log.info("TrainStation " + trainStation.getName() + " was deleted");
    }
}
