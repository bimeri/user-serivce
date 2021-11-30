package com.softtech.app.service.interfaces;

import com.softtech.app.dto.JwtDto;
import com.softtech.app.dto.LoginRequestDto;
import com.softtech.app.dto.UserDto;
import com.softtech.app.model.User;

import java.util.List;

/**
 * Created by N.Bimeri on 23/08/2021.
 */
public interface UserService {
    User addUser(UserDto userDto);

    List<User> getAllUers();

    UserDto findById(int id);

    User updateUserInfo(UserDto userDto, Long id);

    boolean deleteById(Long id);

    JwtDto loginUser(LoginRequestDto loginRequestDto) throws Exception;
}
