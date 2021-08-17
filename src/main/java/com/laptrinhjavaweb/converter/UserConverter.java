package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDTO toDto(UserEntity userEntity) {
        UserDTO result = new UserDTO();
        result.setFullName(userEntity.getFullName());
        result.setUserName(userEntity.getUserName());
        result.setStatus(userEntity.getStatus());
        result.setPassword(userEntity.getPassword());
        result.setId(userEntity.getId());
        return result;
    }

    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity result = new UserEntity();
        result.setFullName(userDTO.getFullName());
        result.setUserName(userDTO.getUserName());
        result.setStatus(userDTO.getStatus());
        result.setPassword(userDTO.getPassword());
        return result;
    }

}
