package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CategoryConverter;
import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryConverter categoryConverter;
	
	@Override
	public Map<String, String> findAll() {
		Map<String, String> categoryDTOS = new HashMap<>();
		List<CategoryEntity> categoryEntities = categoryRepository.findAll();
		for (CategoryEntity item: categoryEntities) {
			categoryDTOS.put(item.getCode(), item.getName());
		}
		return categoryDTOS;
	}

	@Override
	public CategoryDTO findOne(Long id) {
		return categoryConverter.toDto(categoryRepository.findOne(id));
	}

}
