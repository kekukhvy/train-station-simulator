package dev.vkekukh.trainsimulator.dbregio.customer.service;

import dev.vkekukh.trainsimulator.dbregio.customer.model.Address;
import dev.vkekukh.trainsimulator.dbregio.customer.model.Customer;
import dev.vkekukh.trainsimulator.dbregio.customer.repository.AddressRepository;
import dev.vkekukh.trainsimulator.dbregio.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerService(CustomerRepository repository, AddressRepository addressRepository) {
        this.customerRepository = repository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Long saveCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findByLogin(customer.getLogin());

        if (optionalCustomer.isEmpty()) {
            log.info("Customer " + customer.getLogin() + " was not found. ");
            addressRepository.save(customer.getAddress());
            customer = customerRepository.save(customer);

            log.info("Customer " + customer.getLogin() + " was created with id " + customer.getId());
        } else {
            Customer dbCustomer = optionalCustomer.get();
            updateAddress(dbCustomer.getId(), dbCustomer.getAddress(), customer.getAddress());
            updateCustomer(dbCustomer, customer);
        }

        return customer.getId();
    }

    public Optional<Customer> findCustomerById(Long id) {
        return  customerRepository.findById(id);
    }

    private void updateCustomer(Customer origCustomer, Customer newCustomer){
        origCustomer.setFullName(newCustomer.getFullName())
                .setDiscount(newCustomer.getDiscount());

        customerRepository.save(origCustomer);
    }

    private void updateAddress(Long customerId, Address origAddress, Address newAddress) {
        if (!origAddress.equals(newAddress)) {
            log.info("Address of customer " + customerId + " was changed");
            addressRepository.save(newAddress);
        } else {
            log.info("NO changes in address of customer " + customerId);
        }
    }
}
