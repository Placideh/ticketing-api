package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.entity.Role;
import com.placideh.ticketingapi.service.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/role")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/{roleName}")
    public ResponseEntity<Map<String,Role>> createRole(@PathVariable String roleName){
        Map<String,Role> message=new HashMap<>();
        Role role= roleService.createRole(roleName);
        message.put("success",role);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/name/{role-name}")
    public ResponseEntity<Map<String,Role>> getRoleByName(@PathVariable("role-name") String roleName){
        Map<String,Role> message=new HashMap<>();
        Role existingRole=roleService.getRoleByName(roleName);
        message.put("success",existingRole);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id){
        Role existingRole=roleService.getRoleById(id);
        return new ResponseEntity<>(existingRole,HttpStatus.OK);
    }
}
