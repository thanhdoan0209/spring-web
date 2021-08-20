package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "categoryApiOfAdmin")
public class CategoryAPI {

    @Autowired
    private ICategoryService categoryService;

   @PostMapping("/api-admin/category")
   public CategoryDTO createNew(@RequestBody CategoryDTO categoryDTO){
       return categoryService.save(categoryDTO);
   }

    @PutMapping("/api-admin/category/{id}")
    public CategoryDTO updateNew(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        categoryDTO.setId(id);
        return categoryService.save(categoryDTO);
    }

    @DeleteMapping("/api-admin/category/")
    public void deleteNew(@RequestBody Long[] ids){
        categoryService.delete(ids);
    }

}
