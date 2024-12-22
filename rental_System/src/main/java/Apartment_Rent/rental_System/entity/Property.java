package Apartment_Rent.rental_System.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="type")
    private String type; // Property number or identifier
    @Column(name="address")
    private String address;   // Property address
    @Column(name="rent")
    private int rent;         // Monthly rent

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Lease> leases; // List of leases associated with the property

    // No-arg constructorlease
    public Property() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public List<Lease> getLeases() {
        return leases;
    }

    public void setLeases(List<Lease> leases) {
        this.leases = leases;
    }
}
