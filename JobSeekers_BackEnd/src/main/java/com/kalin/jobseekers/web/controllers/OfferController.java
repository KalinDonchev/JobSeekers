package com.kalin.jobseekers.web.controllers;

import com.kalin.jobseekers.data.models.Offer;
import com.kalin.jobseekers.service.models.OfferServiceModel;
import com.kalin.jobseekers.service.services.OfferService;
import com.kalin.jobseekers.web.models.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;

    private final ModelMapper modelMapper;

    @Autowired
    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody OfferCreateModel offerCreateModel) throws IOException {
        offerService.addOffer(offerCreateModel);
        return ok().build();
    }

    @GetMapping("/all")
    @Transactional
    public ResponseEntity<?> getAllCategories() {

        // REFACTOR WITH MODELS !!!!!!! AND OPTIMIZE !!!!!
        List<OfferInfoViewModel> all = this.offerService.getAll();
        return ok(all);
    }


    @GetMapping("/details/{id}")
    @Transactional
    public ResponseEntity<?> details(@PathVariable String id) {

        // REFACTOR WITH MODELS !!!!!!! AND OPTIMIZE !!!!!

        return ok(this.offerService.getById(id));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(DeleteOfferRequest deleteRequest) {
        offerService.delete(deleteRequest);
        return ok().build();
    }

    @GetMapping("/created-by/{username}")
    public ResponseEntity<?> getByCreator(@PathVariable String username) {
        return ok(this.offerService.getAllByCreator(username));
    }





}
