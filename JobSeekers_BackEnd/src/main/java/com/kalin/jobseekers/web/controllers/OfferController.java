package com.kalin.jobseekers.web.controllers;

import com.kalin.jobseekers.service.models.OfferServiceModel;
import com.kalin.jobseekers.service.services.OfferService;
import com.kalin.jobseekers.web.models.OfferCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody OfferCreateModel offerCreateModel) {
        offerService.addOffer(offerCreateModel);
        return ok().build();
    }

}
