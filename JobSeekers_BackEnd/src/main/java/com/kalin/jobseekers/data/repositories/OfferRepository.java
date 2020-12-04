package com.kalin.jobseekers.data.repositories;

import com.kalin.jobseekers.data.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
}
