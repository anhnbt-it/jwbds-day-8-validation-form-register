package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public ModelAndView showFormRegister() {
        ModelAndView modelAndView = new ModelAndView("register");
        User user = new User();
        user.setName("TuanAnh");
        user.setEmail("anhnbt@gmail.com");
        user.setPhone("0123456789");
        user.setAge(18);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView checkValidation(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        new User().validate(user, bindingResult);
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasFieldErrors()) {
            modelAndView.setViewName("register");
        } else {
            userService.save(user);
            modelAndView.setViewName("result");
        }
        return  modelAndView;
    }
}
