package Apartment_Rent.rental_System.Repository;

import Apartment_Rent.rental_System.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent,Long> {
    Optional<Agent> findAgentsByAgentNameAndPhoneAndAgentEmail(String agentName, String phone,String agentEmail);
    List<Agent> findAllBy();
}
