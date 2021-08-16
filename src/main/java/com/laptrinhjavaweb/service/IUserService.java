package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.UserDTO;
import org.springframework.security.core.userdetails.User;

public interface IUserService {
    UserDTO registerNewUserAccount(UserDTO userDto);
}
