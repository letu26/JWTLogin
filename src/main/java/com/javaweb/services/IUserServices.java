package com.javaweb.services;

import org.springframework.stereotype.Service;

public interface IUserServices {
    String login(String username, String password) throws Exception;
}
