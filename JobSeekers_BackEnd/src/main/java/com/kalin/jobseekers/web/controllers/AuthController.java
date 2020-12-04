package com.kalin.jobseekers.web.controllers;

import com.kalin.jobseekers.service.models.UserServiceModel;
import com.kalin.jobseekers.service.services.AuthenticationService;
import com.kalin.jobseekers.web.payload.request.LoginRequest;
import com.kalin.jobseekers.web.payload.request.RegisterRequest;
import com.kalin.jobseekers.web.payload.response.JwtResponse;
import com.kalin.jobseekers.web.payload.response.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final ModelMapper modelMapper;

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(ModelMapper modelMapper, AuthenticationService authenticationService) {
        this.modelMapper = modelMapper;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest model) {

        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        JwtResponse jwtResponse = authenticationService.login(userServiceModel);
        return ResponseEntity.ok(jwtResponse);

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest model) {

        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        MessageResponse messageResponse = authenticationService.register(userServiceModel);
        HttpStatus status = messageResponse.getMessage().contains("Error") ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<>(messageResponse, status);
    }


}
