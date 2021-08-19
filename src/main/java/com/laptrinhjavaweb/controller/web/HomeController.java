package com.laptrinhjavaweb.controller.web;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

    @Autowired
    private INewService newService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private MessageUtil messageUtil;

    @RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
    public ModelAndView homepage(@RequestParam(value = "category", required = false) String category){
        ModelAndView mav = new ModelAndView("web/home");
        List<CategoryDTO> categories = categoryService.findAllCategory();
        mav.addObject("categories", categories);
        if (category != null) {
            List<NewDTO> news = newService.findAllByCategoryCode(category);
            mav.addObject("news", news);
        } else {
            List<NewDTO> news = newService.findAllByCategoryCode(categories.get(0).getCode());
            mav.addObject("news", news);
        }
        return  mav;
    }

    @RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("login/login");
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        return mav;
    }

    @RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
    public ModelAndView signUpPage(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("login/signup");
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        return mav;
    }


    @RequestMapping(value = "/thoat", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/trang-chu");
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        return new ModelAndView("redirect:/dang-nhap?accessDenied");
    }
}
