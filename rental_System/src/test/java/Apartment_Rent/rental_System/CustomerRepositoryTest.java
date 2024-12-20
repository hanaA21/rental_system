package Apartment_Rent.rental_System;

import Apartment_Rent.rental_System.Repository.CustomerRepository;
import Apartment_Rent.rental_System.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(System.out::println);
    }
}
