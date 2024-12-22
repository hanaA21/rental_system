package Apartment_Rent.rental_System.Controller;

import Apartment_Rent.rental_System.Service.AppointmentService;
import Apartment_Rent.rental_System.Service.CustomerService;
import Apartment_Rent.rental_System.Service.PropertyService;
import Apartment_Rent.rental_System.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
        private final AppointmentService appointmentService;
    @Autowired
        private final CustomerService customerService;
    @Autowired
    private final PropertyService propertyService;

    public AppointmentController(AppointmentService appointmentService,
                                 CustomerService customerService,
                                 PropertyService propertyService) {
        this.appointmentService = appointmentService;
        this.customerService = customerService;
        this.propertyService = propertyService;
    }


    // Display all appointments
        @GetMapping
        public String listAppointments(Model model) {
            model.addAttribute("appointments", appointmentService.getAllAppointments());
            return "appointment_list";
        }

        // Show form to create a new appointment
        @GetMapping("/new")
        public String showAppointmentForm(Model model) {
            model.addAttribute("appointment", new Appointment());
            return "appointment_form"; // Refers to appointment-form.html
        }

        // Save appointment
        @PostMapping("/save")
        public String saveAppointment(@ModelAttribute Appointment appointment) {
            appointmentService.saveAppointment(appointment);
            return "redirect:/appointments/new?success=true"; // Redirect with success message
        }

        // Delete appointment
        @GetMapping("/delete/{id}")
        public String deleteAppointment(@PathVariable Long id) {
            appointmentService.deleteAppointment(id);
            return "redirect:/appointments";
        }
}
