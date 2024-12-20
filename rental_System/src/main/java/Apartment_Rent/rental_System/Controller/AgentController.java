package Apartment_Rent.rental_System.Controller;

import Apartment_Rent.rental_System.Service.AgentService;
import Apartment_Rent.rental_System.entity.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/agents")
public class AgentController {
    @Autowired
    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public String listAgents(Model model) {
        model.addAttribute("agents", agentService.getAllAgents());
        return "agent-list";
    }

    @PostMapping("/login")
    public String loginAgent(@RequestParam String name, @RequestParam String phone, @RequestParam String email, Model model) {
        Agent agent = agentService.getAgentByNamePhoneAndEmail(name, phone, email);
        if (agent != null) {
            // Redirect to the property form page if login is successful
            return "redirect:/properties/new";
        } else {
            model.addAttribute("error", "No account found. Please add yourself as a new agent.");
            return "agent-form"; // Return to the agent form with an error message
        }
    }

    @GetMapping("/new")
    public String showAddAgentForm(Model model) {
        model.addAttribute("agent", new Agent());
        return "agent-form";
    }
    @GetMapping("/edit/{id}")
    public String showEditAgentForm(@PathVariable Long id, Model model) {
        Agent agent = agentService.getAgentById(id);
        model.addAttribute("agent", agent);
        return "agent-form"; // Refers to agent-form.html
    }

    @PostMapping("/save")
    public String saveAgent(@ModelAttribute Agent agent, Model model) {
        agentService.saveAgent(agent);
        model.addAttribute("message", "Agent added successfully! Please login now.");
        return "redirect:/agents"; // Redirect to the agent login page
    }

    @GetMapping("/delete/{id}")
    public String deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
        return "redirect:/agents";
    }
}
