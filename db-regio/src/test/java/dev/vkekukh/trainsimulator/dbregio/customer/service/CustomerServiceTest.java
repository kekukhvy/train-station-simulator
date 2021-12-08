package dev.vkekukh.trainsimulator.dbregio.customer.service;

import dev.vkekukh.trainsimulator.dbregio.customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    void updateCustomerAddress(){
        Customer customer = customerService.findCustomerById(Integer.toUnsignedLong(44)).get();
        customer.getAddress().setStreet("Landstrasse");
        customerService.saveCustomer(customer);

        System.out.println(customer);
    }

    @Test
    void updateCustomerName(){
        Customer customer = customerService.findCustomerById(Integer.toUnsignedLong(44)).get();
        customer.setFullName("Vladyslav Kekukh");
        customerService.saveCustomer(customer);
    }

    @Test
    void checkTransaction(){
        Customer customer = customerService.findCustomerById(Integer.toUnsignedLong(44)).get();
        customer.getAddress().setStreet("Rollback!");
        customer.setFullName("Rollback!");
        customerService.saveCustomer(customer);
    }
}