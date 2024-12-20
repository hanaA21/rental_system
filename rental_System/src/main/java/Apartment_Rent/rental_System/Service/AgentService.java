package Apartment_Rent.rental_System.Service;

import Apartment_Rent.rental_System.Repository.AgentRepository;
import Apartment_Rent.rental_System.entity.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AgentService {
    @Autowired
    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }
    public Agent getAgentByNamePhoneAndEmail(String name, String phone, String email) {
        return agentRepository.findAgentsByAgentNameAndPhoneAndAgentEmail(name, phone, email).orElse(null);
    }
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Agent saveAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }

    public Agent getAgentById(Long id) {
        return agentRepository.findById(id).orElseThrow(() -> new RuntimeException("Agent not found"));
    }
}
