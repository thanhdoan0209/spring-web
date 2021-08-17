package com.laptrinhjavaweb.api.web;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController(value = "newApiOfWeb")
public class NewAPI {

    @Autowired
    private IUserService userService;

    @PostMapping("/user/sign-up")
    public UserDTO registerUserAccount(@RequestBody UserDTO userDto) {
        return userService.registerNewUserAccount(userDto);
    }
}
