package dev.vkekukh.trainsimulator.dbregio.customer.repository;

import dev.vkekukh.trainsimulator.dbregio.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByLogin(String login);

}
