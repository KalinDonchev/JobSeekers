package com.kalin.jobseekers.service.services;

import com.kalin.jobseekers.data.models.Offer;
import com.kalin.jobseekers.service.models.OfferServiceModel;
import com.kalin.jobseekers.web.models.DeleteOfferRequest;
import com.kalin.jobseekers.web.models.OfferCreateModel;
import com.kalin.jobseekers.web.models.OfferDetailsViewModel;
import com.kalin.jobseekers.web.models.OfferInfoViewModel;

import java.io.IOException;
import java.util.List;

public interface OfferService {

    OfferServiceModel addOffer(OfferCreateModel offerCreateModel) throws IOException;

    OfferDetailsViewModel getById(String id);

    List<OfferInfoViewModel> getAll();

    List<OfferInfoViewModel> getAllByCreator(String username);

    void delete(DeleteOfferRequest deleteRequest);


}
