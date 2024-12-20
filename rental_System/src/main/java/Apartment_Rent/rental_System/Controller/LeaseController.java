package Apartment_Rent.rental_System.Controller;

import Apartment_Rent.rental_System.Service.CustomerService;
import Apartment_Rent.rental_System.Service.LeaseService;
import Apartment_Rent.rental_System.Service.PropertyService;
import Apartment_Rent.rental_System.entity.Customer;
import Apartment_Rent.rental_System.entity.Lease;
import Apartment_Rent.rental_System.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/leases")
public class LeaseController {
    @Autowired
    private LeaseService leaseService;

    /**
     * Endpoint to place a lease.
     *
     * @param customerId Customer ID.
     * @param propertyId Property ID.
     * @param startDate  Lease start date.
     * @param leaseTerm  Lease term in months.
     * @return The created Lease object.
     */
    @PostMapping
    public ResponseEntity<Lease> placeLease(
            @RequestParam Long customerId,
            @RequestParam Long propertyId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam int leaseTerm) {
        Lease lease = leaseService.placeLease(customerId, propertyId, startDate, leaseTerm);
        return ResponseEntity.status(HttpStatus.CREATED).body(lease);
    }

    /**
     * Endpoint to cancel a lease.
     *
     * @param leaseId Lease ID.
     * @return Response message.
     */
    @DeleteMapping("/{leaseId}")
    public ResponseEntity<String> cancelLease(@PathVariable Long leaseId) {
        leaseService.cancelLease(leaseId);
        return ResponseEntity.ok("Lease canceled successfully");
    }

    /**
     * Endpoint to get a lease by ID.
     *
     * @param leaseId Lease ID.
     * @return Lease object.
     */
    @GetMapping("/{leaseId}")
    public ResponseEntity<Lease> getLeaseById(@PathVariable Long leaseId) {
        Lease lease = leaseService.getLeaseById(leaseId);
        return ResponseEntity.ok(lease);
    }

    /**
     * Endpoint to get all leases.
     *
     * @return List of leases.
     */
    @GetMapping
    public ResponseEntity<List<Lease>> getAllLeases() {
        List<Lease> leases = leaseService.getAllLeases();
        return ResponseEntity.ok(leases);
    }
}
