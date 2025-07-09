package com.example.library.service;

import com.example.library.dto.UserDTO;
import com.example.library.factory.UserFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<UserDTO> getAllUsers() {
        return List.of(
                UserFactory.createUser("Alice"),
                UserFactory.createUser("Bob")
        );
    }
}
