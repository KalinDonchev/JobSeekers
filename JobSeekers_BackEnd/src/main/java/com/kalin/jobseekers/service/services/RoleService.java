package com.kalin.jobseekers.service.services;

import com.kalin.jobseekers.service.models.RoleServiceModel;

import java.util.Set;

public interface RoleService {

    void seed();

    boolean rolesAreSeeded();

    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String authority);

}
