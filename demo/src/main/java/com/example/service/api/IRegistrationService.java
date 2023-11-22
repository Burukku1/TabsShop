package com.example.service.api;

import com.example.core.dto.UserDto;
import com.example.db.entity.User;

import java.util.Optional;

public interface IRegistrationService {

    void createUser(UserDto userDto); //maybe boolean?

    Optional<User> checkUserLog(UserDto userDto);

    boolean checkUserPass(UserDto userDto);
}
