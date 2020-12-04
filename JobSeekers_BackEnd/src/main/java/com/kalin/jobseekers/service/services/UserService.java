package com.kalin.jobseekers.service.services;

import com.kalin.jobseekers.service.models.UserServiceModel;

public interface UserService {

    UserServiceModel getUserByUsername(String username);

}
