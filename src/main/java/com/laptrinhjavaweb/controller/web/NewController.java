package com.laptrinhjavaweb.controller.web;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "newControllerOfWeb")
public class NewController {

    @Autowired
    private INewService newService;

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/new/{id}", method = RequestMethod.GET)
    private ModelAndView pageNewDetail(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("web/new/newDetail");
        mav.addObject("newModel", newService.findById(id));
        List<CategoryDTO> categories = categoryService.findAllCategory();
        mav.addObject("categories", categories);
        return mav;
    }
}
