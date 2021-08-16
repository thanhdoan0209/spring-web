package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.NewDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewService {
	List<NewDTO> findAll(Pageable pageable);
	int getTotalItem();
    NewDTO findById(Long id);
    NewDTO save(NewDTO newDTO);
    void delete(Long[] ids);
}
