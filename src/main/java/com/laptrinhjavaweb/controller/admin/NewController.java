package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller(value = "newControllerOfAdmin")
public class NewController {

    @Autowired
    private INewService newService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private MessageUtil messageUtil;

    @RequestMapping(value = "/admin/new/list", method = RequestMethod.GET)
    public ModelAndView showList(@RequestParam("page") int page,
                                 @RequestParam("limit") int limit, HttpServletRequest request) {
        NewDTO newDTO = new NewDTO();
        newDTO.setPage(page);
        newDTO.setLimit(limit);
        ModelAndView mav = new ModelAndView("admin/new/list");
        Pageable pageable = new PageRequest(page-1, limit);
        newDTO.setTotalItem(newService.getTotalItem());
        newDTO.setTotalPage((int) Math.ceil((double) newDTO.getTotalItem() / newDTO.getLimit()));
        newDTO.setListResult(newService.findAll(pageable));
        mav.addObject("model", newDTO);
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        return  mav;
    }

    @RequestMapping(value = "/admin/new/edit", method = RequestMethod.GET)
    public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/new/edit");
        NewDTO newDTO = new NewDTO();
        if (id != null) {
            newDTO = newService.findById(id);
        }
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("model", newDTO);
        return  mav;
    }

}
