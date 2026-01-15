package com.javaweb.services;

import com.javaweb.dto.UserDTO;
import com.javaweb.entity.User;

public interface IUserServices {
    User createUser(UserDTO userDTO) throws Exception;
    String login(String username, String password) throws Exception;
}
