package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller(value = "categoryControllerOfAdmin")
public class CategoryController {

    @Autowired
    private INewService newService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private MessageUtil messageUtil;

    @RequestMapping(value = "/admin/category/list", method = RequestMethod.GET)
    public ModelAndView showList(@RequestParam("page") int page,
                                 @RequestParam("limit") int limit, HttpServletRequest request) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setPage(page);
        categoryDTO.setLimit(limit);
        ModelAndView mav = new ModelAndView("admin/category/list");
        Pageable pageable = new PageRequest(page-1, limit);
        categoryDTO.setTotalItem(categoryService.getTotalItem());
        categoryDTO.setTotalPage((int) Math.ceil((double) categoryDTO.getTotalItem() / categoryDTO.getLimit()));
        categoryDTO.setListResult(categoryService.findAllCategory(pageable));
        mav.addObject("model", categoryDTO);
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        return  mav;
    }

    @RequestMapping(value = "/admin/category/edit", method = RequestMethod.GET)
    public ModelAndView editCategory(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/category/edit");
        CategoryDTO categoryDTO = new CategoryDTO();
        if (id != null) {
            categoryDTO = categoryService.findOne(id);
        }
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject("model", categoryDTO);
        return  mav;
    }

}
