package Apartment_Rent.rental_System.Service;

import Apartment_Rent.rental_System.Repository.AgentRepository;
import Apartment_Rent.rental_System.Repository.AppointmentRepository;
import Apartment_Rent.rental_System.Repository.CustomerRepository;
import Apartment_Rent.rental_System.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final AgentRepository agentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              CustomerRepository customerRepository,
                              AgentRepository agentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.agentRepository = agentRepository;
    }

    // Fetch all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Fetch appointments by customer ID
    public List<Appointment> getAppointmentsByCustomer(Long customerId) {
        return appointmentRepository.findByCustomer_Id(customerId);
    }

    // Fetch appointments by agent ID
    public List<Appointment> getAppointmentsByAgent(Long agentId) {
        return appointmentRepository.findByAgent_Id(agentId);
    }

    // Save or update an appointment
    public Appointment saveAppointment(Appointment appointment) {
        // Ensure customer and agent exist before saving
        customerRepository.findById(appointment.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        agentRepository.findById(appointment.getAgent().getId())
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        return appointmentRepository.save(appointment);
    }

    // Delete an appointment
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // Fetch an appointment by ID
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }
}
