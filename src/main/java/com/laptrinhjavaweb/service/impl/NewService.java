package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewService implements INewService {

	@Autowired
	private NewRepository newRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private NewConverter newConverter;

	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> newDTOS = new ArrayList<>();
		List<NewEntity> newEntities = newRepository.findAll(pageable).getContent();
		for (NewEntity item: newEntities) {
			NewDTO newDTO = newConverter.toDto(item);
			newDTOS.add(newDTO);
		}
		return newDTOS;
	}

	@Override
	public int getTotalItem() {
		return (int) newRepository.count();
	}

	@Override
	public NewDTO findById(Long id) {
		NewEntity newEntity = newRepository.findOne(id);
		return newConverter.toDto(newEntity);
	}

	@Override
	public NewDTO save(NewDTO newDTO) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		NewEntity newEntity = new NewEntity();
		if (newDTO.getId() != null) {
			NewEntity oldNew = newRepository.findOne(newDTO.getId());
			oldNew.setCategory(categoryEntity);
			newEntity = newConverter.toEntity(oldNew, newDTO);
		} else {
			newEntity = newConverter.toEntity(newDTO);
			newEntity.setCategory(categoryEntity);
		}
		return newConverter.toDto(newRepository.save(newEntity));
	}

	@Override
	public void delete(Long[] ids) {
		for (Long item: ids) {
			newRepository.delete(item);
		}
	}

	@Override
	public List<NewDTO> findAllByCategoryCode(String category) {
		List<NewDTO> newDTOS = new ArrayList<>();
		List<NewEntity> newEntities = newRepository.findAllByCategoryCode(category);
		for (NewEntity item: newEntities) {
			newDTOS.add(newConverter.toDto(item));
		}
		return newDTOS;
	}
}
