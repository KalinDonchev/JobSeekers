package com.kalin.jobseekers.service.services.impl;

import com.kalin.jobseekers.data.models.Category;
import com.kalin.jobseekers.data.models.Image;
import com.kalin.jobseekers.data.models.Offer;
import com.kalin.jobseekers.data.models.User;
import com.kalin.jobseekers.data.repositories.CategoryRepository;
import com.kalin.jobseekers.data.repositories.OfferRepository;
import com.kalin.jobseekers.data.repositories.UserRepository;
import com.kalin.jobseekers.service.models.OfferServiceModel;
import com.kalin.jobseekers.service.services.ImageService;
import com.kalin.jobseekers.service.services.OfferService;
import com.kalin.jobseekers.service.services.UserService;
import com.kalin.jobseekers.web.models.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ImageService imageService;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository,
                            UserRepository userRepository,
                            CategoryRepository categoryRepository,
                            ModelMapper modelMapper,
                            UserService userService,
                            ImageService imageService) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.imageService = imageService;
    }


    @Override
    public OfferServiceModel addOffer(OfferCreateModel offerCreateModel) throws IOException {
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
        offer.setImages(Arrays.stream(offerCreateModel.getImages()).map(imageService::upload)
                .map(i -> modelMapper.map(i, Image.class))
                .collect(Collectors.toList()));

        offer = offerRepository.save(offer);

        return this.modelMapper.map(offer, OfferServiceModel.class);
    }

    @Override
    @Transactional
    public OfferDetailsViewModel getById(String id) {
        Offer offer = this.offerRepository.findById(id).orElseThrow(() -> new Error("Not Found"));;
        OfferDetailsViewModel viewModel = new OfferDetailsViewModel();
        viewModel.setTitle(offer.getTitle());
        viewModel.setDescription(offer.getDescription());
        viewModel.setPrice(offer.getPrice());
        viewModel.setCategory(offer.getCategory().getName());
        viewModel.setImageUrl(offer.getImages().get(0).getUrl());
        UserDetailsViewModel userDetailsViewModel = new UserDetailsViewModel();
        userDetailsViewModel.setUsername(offer.getUser().getUsername());
        userDetailsViewModel.setEmail(offer.getUser().getEmail());
        userDetailsViewModel.setPhoneNumber(offer.getUser().getPhoneNumber());
        viewModel.setUserDetailsViewModel(userDetailsViewModel);
        return viewModel;
    }


    @Override
    @Transactional
    public List<OfferInfoViewModel> getAll() {
        //REFACTOR WITH MODEL
        List<Offer> collect = this.offerRepository.findAll();
        return mapModels(collect);
    }

    @Override
    @Transactional
    public List<OfferInfoViewModel> getAllByCreator(String username) {
        //REFACTOR WITH MODEL
        List<Offer> collect = this.offerRepository.findAllByUserUsername(username);
        return mapModels(collect);
    }

    private List<OfferInfoViewModel> mapModels(List<Offer> collect) {
        List<OfferInfoViewModel> offerInfoViewModels = new ArrayList<>();
        for (int i = 0; i < collect.size(); i++) {
            OfferInfoViewModel offerinfoViewModel = new OfferInfoViewModel();
            offerinfoViewModel.setId(collect.get(i).getId());
            offerinfoViewModel.setTitle(collect.get(i).getTitle());
            offerinfoViewModel.setImageUrl(collect.get(i).getImages().get(0).getUrl());
            offerinfoViewModel.setUser(collect.get(i).getUser().getUsername());
            offerinfoViewModel.setPrice(collect.get(i).getPrice());
            offerInfoViewModels.add(offerinfoViewModel);
        }
        return offerInfoViewModels;
    }

    @Override
    public void delete(DeleteOfferRequest deleteRequest) {
        Offer offer = this.offerRepository.findById(deleteRequest.getOfferId())
                .orElseThrow(() -> new Error("No such offer"));

        this.offerRepository.delete(offer);
    }


}
