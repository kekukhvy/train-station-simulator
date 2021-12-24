package dev.vkekukh.trainsimulator.railway.repository;

import dev.vkekukh.trainsimulator.railway.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
