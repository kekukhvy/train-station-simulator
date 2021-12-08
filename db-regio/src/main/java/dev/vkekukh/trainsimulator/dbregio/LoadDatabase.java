package dev.vkekukh.trainsimulator.dbregio;

import dev.vkekukh.trainsimulator.dbregio.customer.model.Address;
import dev.vkekukh.trainsimulator.dbregio.customer.model.Customer;
import dev.vkekukh.trainsimulator.dbregio.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Configuration
public class LoadDatabase {

    private final CustomerService customerService;

    public LoadDatabase(CustomerService service) {
        this.customerService = service;
    }

    //@Bean
    CommandLineRunner initCustomers() {
        return args -> {
            generateCustomers()
                    .forEach(this::saveCustomer);
        };
    }


    private void saveCustomer(Customer customer) {
        customerService.saveCustomer(customer);
    }

    private List<Customer> generateCustomers() {
        return IntStream.rangeClosed(1, 20)
                .mapToObj(this::generateCustomer)
                .collect(Collectors.toList());
    }

    private Customer generateCustomer(int i) {
        return new Customer().setLogin("login" + i)
                .setFullName("Full name of customer  " + i)
                .setDiscount(i)
                .setAddress(new Address()
                        .setCountry("Austria")
                        .setCity("Vienna")
                        .setStreet("Ungargasse")
                        .setZipCode("1030")
                        .setHouseNo(i % 3 + 1 + "")
                        .setFlat(i % 10 + 1 + ""));
    }
}
