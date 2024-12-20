package Apartment_Rent.rental_System.Service;

import Apartment_Rent.rental_System.Repository.CustomerRepository;
import Apartment_Rent.rental_System.Repository.LeaseRepository;
import Apartment_Rent.rental_System.Repository.PropertyRepository;
import Apartment_Rent.rental_System.entity.Customer;
import Apartment_Rent.rental_System.entity.Lease;
import Apartment_Rent.rental_System.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
@Service
public class LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    /**
     * Place a lease for a customer and property.
     *
     * @param customerId Customer ID.
     * @param propertyId Property ID.
     * @param startDate  Lease start date.
     * @param leaseTerm  Lease term in months.
     * @return The created Lease object.
     */
    public Lease placeLease(Long customerId, Long propertyId, LocalDate startDate, int leaseTerm) {
        // Validate the customer
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Validate the property
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        // Check if the property is available (no overlapping leases)
        if (leaseRepository.existsByPropertyAndDateRange(property, startDate, startDate.plusMonths(leaseTerm))) {
            throw new IllegalStateException("Property is already leased for the selected period");
        }

        // Create the lease
        Lease lease = new Lease();
        lease.setCustomer(customer);
        lease.setProperty(property);
        lease.setStartDate(startDate);
        lease.setLeaseTerm(leaseTerm);
        lease.setEndDate(startDate.plusMonths(leaseTerm));
        lease.setStatus(lease.getEndDate().isBefore(LocalDate.now()) ? "Expired" : "Active");

        return leaseRepository.save(lease);
    }

    /**
     * Cancel a lease and update its status.
     *
     * @param leaseId ID of the lease to cancel.
     */
    public void cancelLease(Long leaseId) {
        // Validate the lease
        Lease lease = leaseRepository.findById(leaseId)
                .orElseThrow(() -> new IllegalArgumentException("Lease not found"));

        // Update lease status to canceled
        lease.setStatus("Canceled");
        leaseRepository.save(lease);
    }

    /**
     * Get a lease by ID.
     *
     * @param leaseId Lease ID.
     * @return Lease object.
     */
    public Lease getLeaseById(Long leaseId) {
        return leaseRepository.findById(leaseId)
                .orElseThrow(() -> new IllegalArgumentException("Lease not found"));
    }

    /**
     * Get all leases.
     *
     * @return List of leases.
     */
    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }
}