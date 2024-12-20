package Apartment_Rent.rental_System.Repository;

import Apartment_Rent.rental_System.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
