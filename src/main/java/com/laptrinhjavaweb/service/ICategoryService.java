package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CategoryDTO;

import java.util.Map;

public interface ICategoryService {
	Map<String, String> findAll();
	CategoryDTO findOne(Long id);
}
