package com.example.theproject.repository;

import com.example.theproject.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Integer> {
    List<Offer> findAll();
    @Query(value = "SELECT * FROM offers WHERE city = ?1 AND id NOT IN (SELECT offer_id FROM bookings WHERE check_in <= ?2 AND check_out >= ?3)", nativeQuery = true)
    List<Offer> findOffersNotBookedInPeriod(String city, String startDate, String endDate);
    List<Offer> findOffersByUserId(int id);
}
