package com.kalin.jobseekers.service.services;

import com.kalin.jobseekers.service.models.OfferServiceModel;
import com.kalin.jobseekers.service.models.UserServiceModel;

public interface UserService {

    UserServiceModel getUserByUsername(String username);

    OfferServiceModel addOfferToFavourites(String offerId, String username);

}
