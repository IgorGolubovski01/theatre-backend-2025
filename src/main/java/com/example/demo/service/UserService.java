package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authManager;


    public ResponseEntity<String> register(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            return new ResponseEntity<>("Username already in use.",HttpStatus.CONFLICT);

        if (userRepository.existsByEmail(user.getEmail()))
            return new ResponseEntity<>("Email already in use.", HttpStatus.CONFLICT);

        User u = new User();
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setRole(roleRepository.getReferenceById(2));

        userRepository.save(u);
        return new ResponseEntity<>("Successfully registered.", HttpStatus.CREATED);
    }

    public ResponseEntity<String> login(LoginRequest request) {

        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            return ResponseEntity.ok("Login successful.");
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }

    }
}
