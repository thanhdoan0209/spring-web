package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class NewConverter {

    public NewDTO toDto(NewEntity entity) {
        NewDTO result = new NewDTO();
        result.setId(entity.getId());
        result.setTitle(entity.getTitle());
        result.setContent(entity.getContent());
        result.setThumbnail(entity.getThumbnail());
        result.setShortDescription(entity.getShortDescription());
        result.setCategoryCode(entity.getCategory().getCode());
        result.setCategoryId(entity.getCategory().getId());
        result.setCategoryName(entity.getCategory().getName());
        result.setCreatedBy(entity.getCreateBy());
        result.setCreatedDate((Timestamp) entity.getCreatedDate());
        result.setModifiedBy(entity.getModifiedBy());
        result.setModifiedDate((Timestamp) entity.getModifiedDate());
        return  result;
    }

    public NewEntity toEntity(NewDTO newDTO) {
        NewEntity result = new NewEntity();
        if (newDTO.getId() != null) {
            result.setId(newDTO.getId());
        }
        result.setTitle(newDTO.getTitle());
        result.setContent(newDTO.getContent());
        result.setThumbnail(newDTO.getThumbnail());
        result.setShortDescription(newDTO.getShortDescription());
        return  result;
    }

    public NewEntity toEntity(NewEntity result, NewDTO dto) {
        result.setTitle(dto.getTitle());
        result.setShortDescription(dto.getShortDescription());
        result.setContent(dto.getContent());
        result.setThumbnail(dto.getThumbnail());
        return result;
    }
}
