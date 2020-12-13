package com.kalin.jobseekers.web.controllers;


import com.kalin.jobseekers.service.services.UserService;
import com.kalin.jobseekers.web.models.FavouriteOfferRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final ModelMapper modelMapper;

    private final UserService userService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @GetMapping("/details/{username}")
    public ResponseEntity<?> userDetails(@PathVariable String username) {
        return ok(this.userService.getUserByUsername(username));
    }

    @PostMapping("/add-to-favourites/{id}")
    public ResponseEntity<?> addOfferToFavourites(@PathVariable String id, Principal principal) throws IOException {
        String principalName = principal.getName();
        userService.addOfferToFavourites(id, principalName);
        return ok().build();
    }


}
