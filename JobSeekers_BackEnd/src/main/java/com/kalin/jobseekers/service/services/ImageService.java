package com.kalin.jobseekers.service.services;

import com.kalin.jobseekers.service.models.ImageServiceModel;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageServiceModel upload(String imageContent);

    ImageServiceModel upload(MultipartFile file);

}
