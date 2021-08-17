package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CategoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
	Map<String, String> findAll();
	List<CategoryDTO> findAllCategory();
	CategoryDTO findOne(Long id);
}
