package Apartment_Rent.rental_System.Controller;

import Apartment_Rent.rental_System.Service.CustomerService;
import Apartment_Rent.rental_System.Service.PropertyService;
import Apartment_Rent.rental_System.entity.Customer;
import Apartment_Rent.rental_System.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/properties")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public String listProperties(Model model) {
        System.out.println("inside");
        model.addAttribute("properties", propertyService.getAllProperties());
        return "Property_list";
    }
    @GetMapping("/new")
    public String showPropertyForm(Model model) {
        model.addAttribute("property", new Property());
        return "Property_form"; // Refers to property-form.html
    }

    @PostMapping("/save")
    public String saveProperty(@ModelAttribute Property property) {
        propertyService.saveProperty(property);
        return "redirect:/properties";
    }
    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        Property savedProperty = propertyService.saveProperty(property);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProperty);
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<Property> updateProperty(
            @PathVariable Long propertyId,
            @RequestBody Property property) {
        Property updatedProperty = propertyService.updateProperty(propertyId, property);
        return ResponseEntity.ok(updatedProperty);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long propertyId) {
        Property property = propertyService.getPropertyById(propertyId);
        return ResponseEntity.ok(property);
    }

//    @GetMapping
//    public ResponseEntity<List<Property>> getAllProperties() {
//        System.out.println("inside");
//        List<Property> properties = propertyService.getAllProperties();
//        return ResponseEntity.ok(properties);
//    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long propertyId) {
        propertyService.deleteProperty(propertyId);
        return ResponseEntity.ok("Property deleted successfully");
    }
}
