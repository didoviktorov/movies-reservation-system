package org.movies.system.controllers;

import org.movies.system.models.binding.UserLoginDto;
import org.movies.system.models.binding.UserRegisterDto;
import org.movies.system.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController extends BaseController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerUser(@ModelAttribute UserRegisterDto userRegisterDto, Model model) {
        model.addAttribute("userRegisterDto", userRegisterDto);
        return this.view("register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerUser(@Valid @ModelAttribute UserRegisterDto userRegisterDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return this.view("register");
        }
        if (!userRegisterDto.passwordsMatch()) {
            model.addAttribute(userRegisterDto);
            bindingResult.rejectValue("password", "error.userRegisterDto", "Passwords do not match.");
            bindingResult.rejectValue("confirmPassword", "error.userRegisterDto", "Passwords do not match.");

            return this.view("register");
        }

        this.userService.register(userRegisterDto);

        return this.redirect("/login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView loginUser(@RequestParam(required = false) String error, @ModelAttribute UserLoginDto userLoginDto) {
        if (error != null) {
            return this.view("login", "error", "Invalid credentials!");
        }

        return this.view("login");
    }
}
