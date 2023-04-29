package com.example.be_project.controller;

import com.example.be_project.model.entities.Role;
import com.example.be_project.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Slf4j

public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping()
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }


}
