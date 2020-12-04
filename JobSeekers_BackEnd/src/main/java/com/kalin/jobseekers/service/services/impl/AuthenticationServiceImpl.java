package com.kalin.jobseekers.service.services.impl;

import com.kalin.jobseekers.config.security.jwt.JwtUtils;
import com.kalin.jobseekers.data.models.User;
import com.kalin.jobseekers.data.repositories.UserRepository;
import com.kalin.jobseekers.service.models.UserServiceModel;
import com.kalin.jobseekers.service.services.AuthenticationService;
import com.kalin.jobseekers.service.services.RoleService;
import com.kalin.jobseekers.web.payload.response.JwtResponse;
import com.kalin.jobseekers.web.payload.response.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RoleService roleService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(RoleService roleService, AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    @Override
    public MessageResponse register(UserServiceModel userModel) {
        if (userRepository.existsByUsername(userModel.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(userModel.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

//        // Create new user's account
//        User user = new User(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()));

        if (!this.roleService.rolesAreSeeded()){
            this.roleService.seed();
        }

        if (this.userRepository.count() == 0) {
            userModel.setAuthorities(this.roleService.findAllRoles());
        } else {
            userModel.setAuthorities(new LinkedHashSet<>());

            userModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }

        // Create new User
        User user = this.modelMapper.map(userModel,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }

    @Override
    public JwtResponse login(UserServiceModel user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getUsername(), roles);
    }
}
