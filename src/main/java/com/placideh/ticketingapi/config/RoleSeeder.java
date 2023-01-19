package com.placideh.ticketingapi.config;

import com.placideh.ticketingapi.entity.Role;
import com.placideh.ticketingapi.repository.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements CommandLineRunner {

    private RoleRepo roleRepo;
    private static final String USER_ROLE="USER";

    public RoleSeeder(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Role userRole = Role.builder()
                .roleName(USER_ROLE)
                .build();

        boolean isUserExist=roleRepo.findByRoleNameIgnoreCase(USER_ROLE).isPresent();
        if(!isUserExist){
            roleRepo.save(userRole);
        }

    }
}
