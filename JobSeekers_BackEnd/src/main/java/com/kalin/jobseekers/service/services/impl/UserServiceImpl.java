package com.kalin.jobseekers.service.services.impl;

import com.kalin.jobseekers.data.models.User;
import com.kalin.jobseekers.data.repositories.UserRepository;
import com.kalin.jobseekers.service.models.UserServiceModel;
import com.kalin.jobseekers.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return this.modelMapper.map(user, UserServiceModel.class);
    }
}
