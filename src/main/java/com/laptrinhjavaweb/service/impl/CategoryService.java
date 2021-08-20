package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CategoryConverter;
import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryConverter categoryConverter;

	@Autowired
	private INewService newService;

	@Autowired
	private NewRepository newRepository;
	
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
	public List<CategoryDTO> findAllCategory() {
		List<CategoryEntity> categoryEntities = categoryRepository.findAll();
		List<CategoryDTO> result = new ArrayList<>();
		for (CategoryEntity item: categoryEntities) {
			result.add(categoryConverter.toDto(item));
		}
		return result;
	}

	@Override
	public List<CategoryDTO> findAllCategory(Pageable pageable) {
		List<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable).getContent();
		List<CategoryDTO> result = new ArrayList<>();
		for (CategoryEntity item: categoryEntities) {
			result.add(categoryConverter.toDto(item));
		}
		return result;
	}

	@Override
	public CategoryDTO findOne(Long id) {
		return categoryConverter.toDto(categoryRepository.findOne(id));
	}

	@Override
	public int getTotalItem() {
		return (int) categoryRepository.count();
	}

	@Override
	public CategoryDTO save(CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = new CategoryEntity();
		if (categoryDTO.getId() != null) {
			CategoryEntity oldCategory = categoryRepository.findOne(categoryDTO.getId());
			categoryEntity = categoryConverter.toEntity(oldCategory, categoryDTO);
		} else {
			categoryEntity = categoryConverter.toEntity(categoryDTO);
		}
		return categoryConverter.toDto(categoryRepository.save(categoryEntity));
	}

	@Override
	public void delete(Long[] ids) {
		for (Long category: ids) {
			List<NewEntity> news = newRepository.findAllByCategoryId(category);
			for (NewEntity newEntity: news) {
				newRepository.delete(newEntity.getId());
			}
			categoryRepository.delete(category);
		}
	}

}
