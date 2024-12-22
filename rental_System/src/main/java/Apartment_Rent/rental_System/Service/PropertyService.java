package Apartment_Rent.rental_System.Service;

import Apartment_Rent.rental_System.Repository.CustomerRepository;
import Apartment_Rent.rental_System.Repository.PropertyRepository;
import Apartment_Rent.rental_System.entity.Customer;
import Apartment_Rent.rental_System.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    /**
     * Add a new property to the system.
     *
     * @param property The property to add.
     * @return The saved Property object.
     */
    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    /**
     * Update an existing property's details.
     *
     * @param propertyId      The ID of the property to update.
     * @param updatedProperty The updated property details.
     * @return The updated Property object.
     */
    public Property updateProperty(Long propertyId, Property updatedProperty) {
        // Find the existing property
        Property existingProperty = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        // Update fields
        existingProperty.setType(updatedProperty.getType());
        existingProperty.setAddress(updatedProperty.getAddress());
        existingProperty.setRent(updatedProperty.getRent());

        return propertyRepository.save(existingProperty);
    }

    public Property getPropertyById(Long propertyId) {
        return propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    /**
     * Delete a property by its ID. Ensures no leases are associated with the property before deleting.
     *
     * @param propertyId The ID of the property to delete.
     */
    public void deleteProperty(Long propertyId) {
        // Validate the property exists
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        // Check if the property has active leases
        if (property.getLeases() != null && !property.getLeases().isEmpty()) {
            throw new IllegalStateException("Cannot delete property with associated leases");
        }

        // Delete the property
        propertyRepository.delete(property);
    }
}
