package info.joelwilson.controller;

import info.joelwilson.model.User;
import info.joelwilson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestApiController {

    private UserService userService;

    @Autowired
    public RestApiController(UserService userService) {

        this.userService = userService;
    }

    @RequestMapping(value = "/api/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(@PathVariable("id") String id) {

        return this.userService.getUserById(id);
    }

    @RequestMapping(value = "/api/all", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {

        return this.userService.getAllUsers();
    }
}
