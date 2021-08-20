package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryDTO toDto(CategoryEntity entity) {
        CategoryDTO result = new CategoryDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setCode(entity.getCode());
        return  result;
    }

    public CategoryEntity toEntity(CategoryDTO categoryDTO) {
        CategoryEntity result = new CategoryEntity();
        result.setCode(categoryDTO.getCode());
        result.setName(categoryDTO.getName());
        return  result;
    }

    public CategoryEntity toEntity(CategoryEntity oldCategory, CategoryDTO categoryDTO) {
        oldCategory.setName(categoryDTO.getName());
        oldCategory.setCode(categoryDTO.getCode());
        return  oldCategory;
    }
}
