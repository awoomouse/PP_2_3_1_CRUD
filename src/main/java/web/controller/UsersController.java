package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.dao.UserDao;
import web.model.User;

@Controller
public class UsersController {
    private final UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public String users(ModelMap model) {
        model.addAttribute("usersList", this.userDao.getAllUsers());
        return "users";
    }

    @GetMapping("users/add")
    public String addUser(@ModelAttribute("user") User user) {
        return "addUser";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        this.userDao.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("users/{id}")
    public String showUser(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", this.userDao.getUser(id));
        return "show";
    }

    @GetMapping("users/{id}/update")
    public String updateUser(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", this.userDao.getUser(id));
        return "updateUser";
    }

    @PatchMapping("users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        this.userDao.editUser(user, id);
        return "show";
    }

    @DeleteMapping("users/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        this.userDao.deleteUser(id);
        return "redirect:/users";
    }
}