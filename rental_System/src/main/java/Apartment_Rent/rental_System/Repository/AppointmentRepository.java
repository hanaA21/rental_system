package Apartment_Rent.rental_System.Repository;

import Apartment_Rent.rental_System.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByAgent_Id(Long id); // Find appointments by agent
    List<Appointment> findByCustomer_Id(Long id);

}
