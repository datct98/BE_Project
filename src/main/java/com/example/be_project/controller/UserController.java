package com.example.be_project.controller;

import com.example.be_project.model.entities.LoginRequest;
import com.example.be_project.model.entities.User;
import com.example.be_project.model.entities.UserDetail;
import com.example.be_project.repository.UserRepository;
import com.example.be_project.service.UserService;
import com.example.be_project.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j

public class UserController {

    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping("/")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.POST)
    @PostMapping("/register")
    public String addUser(@RequestBody User user){
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        Date date= new Date(System.currentTimeMillis());

        User userNew = new User();
        try{
            //check user
            userNew.setEmail(user.getEmail());
            userNew.setCreatedAt(format.format(date));
            userNew.setUsername(user.getUsername());
            userNew.setAddress(user.getAddress());
            userNew.setPassword(passwordEncoder.encode(user.getPassword()));
            userNew.setValueId(1);
            userRepository.save(userNew);
            return "Register successfully";
        } catch (Exception e){
           return "Somethings wrong!";
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.PUT)
    @PutMapping("/user")
    public User updateUser(@RequestBody User p) {
        return userRepository.save(p);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.DELETE)
    @DeleteMapping("{id}")
    public User deleteUser(@PathVariable("id") Integer id) {
        User p = userRepository.findUserById(id);
        if (p != null) {
            userRepository.deleteById(id);
            System.out.println("Da xoa thanh cong");
        }
        return null;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.POST)
    @PostMapping("/signing")
    public ResponseEntity<?> authenticateUser2(@RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetail detail = (UserDetail) authentication.getPrincipal();

        // Trả về jwt cho người dùng.
        String jwt = jwtUtil.generateToken(detail);
        return ResponseEntity.ok(jwt);
    }

}
