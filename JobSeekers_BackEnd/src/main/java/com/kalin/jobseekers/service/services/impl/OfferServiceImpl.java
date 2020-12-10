package com.kalin.jobseekers.service.services.impl;

import com.kalin.jobseekers.data.models.Category;
import com.kalin.jobseekers.data.models.Offer;
import com.kalin.jobseekers.data.models.User;
import com.kalin.jobseekers.data.repositories.CategoryRepository;
import com.kalin.jobseekers.data.repositories.OfferRepository;
import com.kalin.jobseekers.data.repositories.UserRepository;
import com.kalin.jobseekers.service.models.OfferServiceModel;
import com.kalin.jobseekers.service.services.OfferService;
import com.kalin.jobseekers.service.services.UserService;
import com.kalin.jobseekers.web.models.OfferCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, UserService userService) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public OfferServiceModel addOffer(OfferCreateModel offerCreateModel) {
        Offer offer = this.offerRepository
                .findByTitle(offerCreateModel.getTitle())
                .orElse(null);

        if (offer != null) {
            throw new RuntimeException("Offer with this name already exists");
        }

        Category category = this.categoryRepository.findByName(offerCreateModel.getCategory())
                .orElseThrow(() -> new Error("User Not Found with username: " + offerCreateModel.getCategory()));

        User user = this.userRepository.findByUsername(offerCreateModel.getUser())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + offerCreateModel.getUser()));


        offer = this.modelMapper.map(offerCreateModel, Offer.class);
        offer.setCategory(category);
        offer.setUser(user);
        this.offerRepository.saveAndFlush(offer);

        return this.modelMapper.map(offer, OfferServiceModel.class);
    }
}
