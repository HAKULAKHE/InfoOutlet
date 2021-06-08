package com.strugglers.InfoOutlet.controller;

import com.strugglers.InfoOutlet.Model.User;
import com.strugglers.InfoOutlet.dto.UserDTO;
import com.strugglers.InfoOutlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//We need to add @Service, @RestController, @Repository or @Configuration to use @Autowired
@RestController
@RequestMapping("/users")     //Any request to server(Url) from any port it denote that this will be managed by user controller
public class UserController {
    @Autowired
    private UserService userService;
    //This is accessed using POSTMAN; METHOD=POST | localhost:8080/users if @PostMapping("/addUser")used we need to specify localhost:8080/users/addUser
    @PostMapping      //Save and add data from controller; @PostMapping("/addUser") would be easier but according to convention we should use the @RequestMapping("/users") for all task rather than adding new things.
    public UserDTO addUser(@RequestBody UserDTO userDTO)
    {
        return userService.addUser(userDTO);
    }
}
