package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
}
