package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewRepository extends JpaRepository<NewEntity, Long> {
    @Query("select n from NewEntity n where n.category.code = ?1")
    List<NewEntity> findAllByCategoryCode(String category);

    List<NewEntity> findAllByCategoryId(Long category);
}
