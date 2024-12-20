package Apartment_Rent.rental_System.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agentName;  // Name of the agent
    private String phone; // Phone number of the agent
    private String agentEmail; // Email address of the agent

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments; // List of appointments handled by the agent

    // No-arg constructor
    public Agent() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id= id;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String AgentName) {
        this.agentName = agentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}