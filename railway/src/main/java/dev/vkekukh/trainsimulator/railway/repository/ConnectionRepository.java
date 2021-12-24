package dev.vkekukh.trainsimulator.railway.repository;

import dev.vkekukh.trainsimulator.railway.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
}
