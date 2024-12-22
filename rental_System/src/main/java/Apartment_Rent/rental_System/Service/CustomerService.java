package Apartment_Rent.rental_System.Service;

import Apartment_Rent.rental_System.Repository.AppointmentRepository;
import Apartment_Rent.rental_System.Repository.CustomerRepository;
import Apartment_Rent.rental_System.entity.Appointment;
import Apartment_Rent.rental_System.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
