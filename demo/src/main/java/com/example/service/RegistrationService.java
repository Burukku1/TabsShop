package com.example.service;

import com.example.core.dto.UserDto;
import com.example.db.api.IUserDao;

import com.example.db.entity.User;
import com.example.db.factory.UserFactory;
import com.example.service.api.IRegistrationService;
import kotlin.OptIn;

import java.util.Optional;

public class RegistrationService implements IRegistrationService {

    private IUserDao iUserDao = UserFactory.getInstance();

    @Override
    public void createUser(UserDto userDto) {
        iUserDao.createUser(new User(userDto.getLogin(), userDto.getPassword(), userDto.getEmail()));
    }

    @Override
    public Optional<User> checkUserLog(UserDto userDto) {
        return iUserDao.checkUserLog(new User(userDto.getLogin(), userDto.getPassword(), userDto.getEmail()));
    }

    @Override
    public boolean checkUserPass(UserDto userDto) {
        return iUserDao.checkUserPass(new User(userDto.getLogin(), userDto.getPassword(), userDto.getEmail()));
    }
}
