package dev.vkekukh.trainsimulator.dbregio.customer.repository;

import dev.vkekukh.trainsimulator.dbregio.customer.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findAddressByFlat(String flat);
}
