package info.joelwilson.controller;

import info.joelwilson.model.User;
import info.joelwilson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    // All users
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String allUsers(Model model) {

        List<User> users = this.userService.getAllUsers();

        model.addAttribute("users", users);

        return "index";
    }

    // View user by id
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("id") String id) {

        User user = this.userService.getUserById(id);

        ModelAndView mav = new ModelAndView("user");

        mav.addObject("user", user);

        return mav;
    }

    // Get create-user form
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String createUser(Model model) {

        User user = new User();

        model.addAttribute("user", user);

        return "create-user";
    }


    // Submit create-user form
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String createUserSubmit(@Valid @ModelAttribute("user") User user, BindingResult result) {



        if(result.hasErrors()) {

            return "create-user";
        }

        // Set user id (UUID)
        user.setId(UserService.generateUId());

        this.userService.addUser(user);

        return "redirect:/";
    }

    // Get edit-user form
    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable("id") String id, Model model) {

        User user = this.userService.getUserById(id);

        model.addAttribute("user", user);

        return "edit-user";
    }

    // Submit edit-user form
    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
    public String editUserSubmit(@Valid @ModelAttribute("user") User user, BindingResult result) {

        if(result.hasErrors()) {

            return "edit-user";
        }

        this.userService.updateUser(user);

        return "redirect:/";
    }

    // Delete user then return to index page
    @RequestMapping(value = "/user/delete/{id}")
    public String deleteUser(@PathVariable("id") String id) {

        this.userService.deleteUser(id);

        return "redirect:/";
    }

}
