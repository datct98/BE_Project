package com.example.be_project.service;

import com.example.be_project.model.entities.User;
import com.example.be_project.model.entities.UserDetail;
import com.example.be_project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return new UserDetail(user);
    }

    public UserDetails loadUserById(int id) throws UsernameNotFoundException {
        User user = userRepository.getById(id);
        if (user == null)
            throw new UsernameNotFoundException(Integer.toString(id));
        return new UserDetail(user);
    }



}
