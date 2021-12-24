package dev.vkekukh.trainsimulator.railway.repository;

import dev.vkekukh.trainsimulator.railway.model.TrainStation;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainStationRepository extends JpaRepository<TrainStation, Long> {

    Optional<TrainStation> findByName(String name);

    List<TrainStation> findByCity(String city);
}
