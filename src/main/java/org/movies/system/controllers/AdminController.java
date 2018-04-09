package org.movies.system.controllers;

import org.movies.system.models.view.UserEditDto;
import org.movies.system.services.role.RoleService;
import org.movies.system.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AdminController extends BaseController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView getUsers() {
        return this.view("users/all-users", "users", this.userService.findAllUsers());
    }

    @GetMapping("/edit/user/{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView editUser(@PathVariable String id, @ModelAttribute UserEditDto userEditDto) {
        userEditDto = this.userService.findEditUser(id);
        String[] names = {"userEditDto", "roles", "userId"};
        Object[] models = {userEditDto, this.roleService.findAllRoles(), id};

        return this.view("users/edit-user", names, models);
    }

    @PostMapping("/edit/user/{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView editUser(@PathVariable String id, @Valid @ModelAttribute UserEditDto userEditDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String[] names = {"userEditDto", "roles", "userId"};
            Object[] models = {userEditDto, this.roleService.findAllRoles(), id};
            return this.view("users/edit-user", names, models);
        }

        this.userService.editUser(id, userEditDto);

        return this.redirect("/users");
    }
}
