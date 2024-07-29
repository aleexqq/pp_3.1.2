package ru.shalygin.pp312.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.shalygin.pp312.model.User;
import ru.shalygin.pp312.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ModelAndView getUsers(@RequestParam(value = "id", required = false) Integer id) {
        List<User> users = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        if (id == null) {
            users = userService.allUsers();
            modelAndView.addObject("users", users);
        } else {
            users.add(userService.getUserById(id));
            modelAndView.addObject("users", users);
        }
        modelAndView.setViewName("user/users");
        return modelAndView;
    }

    @GetMapping("users/edit")
    public ModelAndView editUser(@RequestParam(value = "id", required = false) Integer id) {
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user/edit");
        return modelAndView;
    }

    @PostMapping("users/edit")
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/users");
        userService.updateUser(user);
        return modelAndView;
    }

    @PostMapping("users/delete")
    public ModelAndView deleteUser(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }

    @GetMapping("users/add")
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/edit");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("users/add")
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/users");
        userService.addUser(user);
        return modelAndView;
    }
}
