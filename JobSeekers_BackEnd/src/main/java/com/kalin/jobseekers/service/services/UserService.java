package com.kalin.jobseekers.service.services;

import com.kalin.jobseekers.service.models.OfferServiceModel;
import com.kalin.jobseekers.service.models.UserServiceModel;
import com.kalin.jobseekers.web.models.UserDetailsViewModel;

public interface UserService {

    UserDetailsViewModel getUserByUsername(String username);

    OfferServiceModel addOfferToFavourites(String offerId, String username);

}
