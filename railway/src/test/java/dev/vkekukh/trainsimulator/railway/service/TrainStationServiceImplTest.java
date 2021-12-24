package dev.vkekukh.trainsimulator.railway.service;

import dev.vkekukh.trainsimulator.railway.exception.NotFoundException;
import dev.vkekukh.trainsimulator.railway.exception.ValidationException;
import dev.vkekukh.trainsimulator.railway.model.TrainStation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrainStationServiceImplTest {


    @Autowired
    private TrainStationService trainStationService;

    @Test
    @DisplayName("Test find station by name")
    void findByName() {
        Optional<TrainStation> trainStation = trainStationService.findByName("Киев Центральный");
        assertNotNull(trainStation);
        assertTrue(trainStation.get().getCity().equals("Киев"));
    }

    @Test
    void findByCity() {
        List<TrainStation> trainStations = trainStationService.findByCity("Киев");
        assertTrue(trainStations.size() > 0);
    }

    @Test
    void saveTrainStationError() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            trainStationService.saveTrainStation(new TrainStation());
        });

        String expectedMessage = "City and name for train station can't be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void saveTrainStation() throws ValidationException {

        TrainStation station = trainStationService.saveTrainStation(new TrainStation()
                .setCity("Киев").setName("Киев-Дарница"));

        assertNotNull(station.getId());
    }

    @Test
    void deleteById() throws NotFoundException {
        Long id = trainStationService.findByName("Киев-Дарница").get().getId();
        trainStationService.deleteById(id);
        assertTrue(trainStationService.findByName("Киев-Дарница").isEmpty());
    }

    @Test
    void delete() throws ValidationException {
        TrainStation station = trainStationService.saveTrainStation(new TrainStation()
                .setCity("Киев").setName("Киев-Дарница"));
        trainStationService.delete(station);

        assertTrue(trainStationService.findByName("Киев-Дарница").isEmpty());
    }

    @Test
    void deleteByIdException(){
        Exception exception = assertThrows(NotFoundException.class, () -> {
            trainStationService.deleteById(Long.valueOf(0));
        });

        String expectedMessage = "Station with id 0 was not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}