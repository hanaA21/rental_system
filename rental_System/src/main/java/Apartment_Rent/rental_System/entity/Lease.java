package Apartment_Rent.rental_System.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int leaseTerm; // Lease term in months
    private LocalDate startDate;
    private LocalDate endDate;

    public Lease( int leaseTerm, LocalDate startDate, LocalDate endDate, String status,Customer customer,Property property) {
        this.leaseTerm = leaseTerm;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.customer=customer;
        this.property=property;
    }

    private String status; // Active or Expired

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false) // Foreign key to Customer table
    private Customer customer; // The customer who owns this lease

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false) // Foreign key to Property table
    private Property property; // The property leased

    // No-arg constructor
    public Lease() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(int leaseTerm) {
        this.leaseTerm = leaseTerm;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}