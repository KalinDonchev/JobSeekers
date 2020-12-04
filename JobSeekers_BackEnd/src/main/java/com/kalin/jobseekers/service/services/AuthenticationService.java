package com.kalin.jobseekers.service.services;

import com.kalin.jobseekers.service.models.UserServiceModel;
import com.kalin.jobseekers.web.payload.response.JwtResponse;
import com.kalin.jobseekers.web.payload.response.MessageResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {

    MessageResponse register(UserServiceModel user);

    JwtResponse login(UserServiceModel user);


}
