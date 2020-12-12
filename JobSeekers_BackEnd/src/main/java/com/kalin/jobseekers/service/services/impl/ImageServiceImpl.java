package com.kalin.jobseekers.service.services.impl;

import com.cloudinary.Uploader;
import com.kalin.jobseekers.data.models.Image;
import com.kalin.jobseekers.data.models.Offer;
import com.kalin.jobseekers.data.repositories.ImageRepository;
import com.kalin.jobseekers.data.repositories.OfferRepository;
import com.kalin.jobseekers.service.models.ImageServiceModel;
import com.kalin.jobseekers.service.services.ImageService;
import com.kalin.jobseekers.web.models.ImagesToUploadModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final OfferRepository offerRepository;

    private final Uploader uploader;

    private final ModelMapper modelMapper;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, OfferRepository offerRepository, Uploader uploader, ModelMapper modelMapper) {
        this.imageRepository = imageRepository;
        this.offerRepository = offerRepository;
        this.uploader = uploader;
        this.modelMapper = modelMapper;
    }


    @Override
    public ImageServiceModel upload(String imageContent) {

        Pair<String, String> cloudUploadData = uploadImage(imageContent);

        ImageServiceModel imageServiceModel = new ImageServiceModel();
        imageServiceModel.setUrl(cloudUploadData.getLeft());
        imageServiceModel.setIdInCloud(cloudUploadData.getRight());

        return imageServiceModel;
    }

    @Override
    public ImageServiceModel upload(MultipartFile file) {
        Pair<String, String> cloudUploadData = uploadImage(file);
        ImageServiceModel imageServiceModel = new ImageServiceModel();
        imageServiceModel.setUrl(cloudUploadData.getLeft());
        imageServiceModel.setIdInCloud(cloudUploadData.getRight());
        return imageServiceModel;
    }

    public List<ImageServiceModel> uploadAdvertisementPhotos(ImagesToUploadModel model) {

        String id = model.getOfferId();

        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new Error(String.format("Image not found", id)));

        if (!offer.getUser().getUsername().equals(model.getUsername())) {
            throw new Error("Not allowed");
        }

        List<Image> images = new ArrayList<>();

        for (String content : model.getImages()) {
            Pair<String, String> cloudUploadData = uploadImage(content);
            Image image = new Image();
            image.setUrl(cloudUploadData.getLeft());
            image.setIdInCloud(cloudUploadData.getRight());
            images.add(image);
        }

        offer.getImages().addAll(images);

        offer = offerRepository.save(offer);

        return offer.getImages().stream()
                .map(i -> this.modelMapper.map(i, ImageServiceModel.class))
                .collect(Collectors.toList());
    }




    private Pair<String, String> uploadImage(String content) {

        Map<String, String> uploadedData;

        try {
            uploadedData = uploader.upload(content, new HashMap<>());
        } catch (Exception ignored) {
            uploadedData = null;
        }

        //cloud works this way
        return Pair.of(uploadedData.get("url"), uploadedData.get("public_id"));
    }

    private Pair<String, String> uploadImage(MultipartFile multipartFile) {

        Map<String, String> uploadedData;

        try {
            File file = File.createTempFile("temp-file", multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            uploadedData = uploader.upload(file, new HashMap());
        } catch (Exception ex) {
            uploadedData = null;
        }

        //cloud works this way
        return Pair.of(uploadedData.get("url"), uploadedData.get("public_id"));
    }



}
