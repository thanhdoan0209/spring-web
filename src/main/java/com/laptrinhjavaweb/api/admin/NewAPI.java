package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "newApiOfAdmin")
public class NewAPI {

    @Autowired
    private INewService newService;

   @PostMapping("/api-admin/new")
   public NewDTO createNew(@RequestBody NewDTO newDTO){
       return newService.save(newDTO);
   }

    @PutMapping("/api-admin/new/{id}")
    public NewDTO updateNew(@RequestBody NewDTO updateNew, @PathVariable Long id){
        updateNew.setId(id);
        return newService.save(updateNew);
    }

    @DeleteMapping("/api-admin/new/")
    public void deleteNew(@RequestBody Long[] ids){
        newService.delete(ids);
    }

}
