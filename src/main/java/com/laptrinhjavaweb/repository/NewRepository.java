package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewRepository extends JpaRepository<NewEntity, Long> {
    List<NewEntity> findAllByCategoryCode(String category);
}
