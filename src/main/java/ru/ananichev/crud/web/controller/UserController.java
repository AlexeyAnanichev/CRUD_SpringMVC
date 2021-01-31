package ru.ananichev.crud.web.controller;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import ru.ananichev.crud.service.UserService;
import ru.ananichev.crud.model.User;
import ru.ananichev.crud.model.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public UserController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "/admin/users";
    }

    @GetMapping("/user")
    public String getUser(Model model, Principal principal) {
        principal.getName();
        model.addAttribute("userInfo", userDetailsService.loadUserByUsername(principal.getName()));
        return "user";
    }

    @PostMapping(value = "/admin/add")
    public String saveUser(@ModelAttribute User user, @RequestParam("roles") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(userService.getRoleByName(role));
        }
        userService.saveUser(user, roleSet);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update/{id}")
    public String updatePage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "admin/update";
    }

    @PostMapping(value = "/admin/edit")
    public String updateUser(@ModelAttribute User user, @RequestParam("roles") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(userService.getRoleByName(role));
        }
        userService.updateUser(user, roleSet);
        return "redirect:/admin";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}

