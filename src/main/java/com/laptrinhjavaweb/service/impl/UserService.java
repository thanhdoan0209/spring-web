package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.RoleConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO registerNewUserAccount(UserDTO userDto)  {
        UserEntity user = new UserEntity();

        userDto.setStatus(1);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user = userConverter.toEntity(userDto);

        RoleEntity role = roleRepository.findOne(2L);
        List<RoleEntity>  roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        return userConverter.toDto(userRepository.save(user));
    }
}
