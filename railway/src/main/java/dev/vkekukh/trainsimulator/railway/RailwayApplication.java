package dev.vkekukh.trainsimulator.railway;

import dev.vkekukh.trainsimulator.railway.model.Connection;
import dev.vkekukh.trainsimulator.railway.model.Route;
import dev.vkekukh.trainsimulator.railway.model.TrainStation;
import dev.vkekukh.trainsimulator.railway.repository.ConnectionRepository;
import dev.vkekukh.trainsimulator.railway.repository.RouteRepository;
import dev.vkekukh.trainsimulator.railway.repository.TrainStationRepository;
import dev.vkekukh.trainsimulator.railway.service.TrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
public class RailwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RailwayApplication.class, args);
    }

    @Autowired
    TrainStationService trainStationService;

    
    
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            List<TrainStation> trainStation = trainStationService.findByCity("Киев");
            System.out.println(trainStation);
        };
    }


}
