package com.kalin.jobseekers.data.repositories;

import com.kalin.jobseekers.data.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface OfferRepository extends JpaRepository<Offer, String> {

    Optional<Offer> findByTitle(String name);

    List<Offer> findAllByUserUsername(String username);

    List<Offer> findTop3ByOrderByIdDesc();

}
