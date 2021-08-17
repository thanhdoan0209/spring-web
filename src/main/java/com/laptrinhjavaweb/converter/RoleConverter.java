package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;

@Component
public class RoleConverter {

    public RoleDTO toDTO(RoleEntity roleEntity) {
        RoleDTO result = new RoleDTO();
        result.setCode(roleEntity.getCode());
        result.setName(roleEntity.getName());
        return result;
    }
}
