package com.example.library.factory;

import com.example.library.dto.UserDTO;

public class UserFactory {
    public static UserDTO createUser(String name) {
        return new UserDTO(name);
    }
}
