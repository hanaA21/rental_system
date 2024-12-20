package Apartment_Rent.rental_System.Repository;

import Apartment_Rent.rental_System.entity.Lease;
import Apartment_Rent.rental_System.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;


public interface LeaseRepository extends JpaRepository<Lease,Long> {
    /**
     * Checks if a property is already leased for the specified date range.
     *
     * @param property   The property to check.
     * @param startDate  The start date of the desired lease period.
     * @param endDate    The end date of the desired lease period.
     * @return true if there is an overlapping lease, false otherwise.
     */
    @Query("SELECT COUNT(l) > 0 FROM Lease l WHERE l.property = :property AND " +
            "(l.startDate <= :endDate AND l.endDate >= :startDate)")
    boolean existsByPropertyAndDateRange(
            @Param("property") Property property,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
