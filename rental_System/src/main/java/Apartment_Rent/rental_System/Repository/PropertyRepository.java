package Apartment_Rent.rental_System.Repository;

import Apartment_Rent.rental_System.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property,Long> {
}
