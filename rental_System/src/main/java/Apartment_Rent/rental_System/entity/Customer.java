package Apartment_Rent.rental_System.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column(name="name")
    private String name;
@Column(name="phone")
    private String phone;
@Column(name="email")
    private String email;
private int customer_id;
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Appointment> appointments;

    // Default constructor
    public Customer() {}

    // Parameterized constructor
    public Customer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

