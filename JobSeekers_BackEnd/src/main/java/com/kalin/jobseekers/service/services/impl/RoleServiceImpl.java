package com.kalin.jobseekers.service.services.impl;

import com.kalin.jobseekers.data.models.Role;
import com.kalin.jobseekers.data.repositories.RoleRepository;
import com.kalin.jobseekers.service.models.RoleServiceModel;
import com.kalin.jobseekers.service.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private static final List<Role> roles = List.of(
            new Role("ROLE_USER"),
            new Role("ROLE_ADMIN"),
            new Role("ROLE_ROOT"));

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seed() {
        this.roleRepository.saveAll(roles);
    }

    @Override
    public boolean rolesAreSeeded() {
        return this.roleRepository.count() != 0;
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
        return this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        return this.modelMapper.map(this.roleRepository.findByAuthority(authority), RoleServiceModel.class);
    }
}
