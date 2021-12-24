package dev.vkekukh.trainsimulator.railway.repository;

import dev.vkekukh.trainsimulator.railway.model.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainStationRepository extends JpaRepository<TrainStation, Long> {

    List<TrainStation> findByName(String name);

    List<TrainStation> findByCity(String city);

    Optional<TrainStation> findByCityAndName(String city, String name);
}
