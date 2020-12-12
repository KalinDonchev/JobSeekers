package com.kalin.jobseekers.service.services.impl;

import com.kalin.jobseekers.data.models.Offer;
import com.kalin.jobseekers.data.models.User;
import com.kalin.jobseekers.data.repositories.OfferRepository;
import com.kalin.jobseekers.data.repositories.UserRepository;
import com.kalin.jobseekers.service.models.OfferServiceModel;
import com.kalin.jobseekers.service.models.UserServiceModel;
import com.kalin.jobseekers.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final OfferRepository offerRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OfferRepository offerRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public UserServiceModel getUserByUsername(String username) {

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public OfferServiceModel addOfferToFavourites(String offerId, String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        Offer offer = this.offerRepository.findById(offerId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));;

        List<Offer> favouriteOffers = user.getFavouriteOffers();
        favouriteOffers.add(offer);

        this.userRepository.save(user);

        return this.modelMapper.map(offer, OfferServiceModel.class);
    }
}
