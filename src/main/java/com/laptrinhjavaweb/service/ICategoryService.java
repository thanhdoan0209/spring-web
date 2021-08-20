package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
	Map<String, String> findAll();
	List<CategoryDTO> findAllCategory(Pageable pageable);
	List<CategoryDTO> findAllCategory();
	CategoryDTO findOne(Long id);

    int getTotalItem();

	CategoryDTO save(CategoryDTO categoryDTO);

	void delete(Long[] ids);
}
