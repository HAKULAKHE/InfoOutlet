package com.strugglers.InfoOutlet.controller;

import com.strugglers.InfoOutlet.dto.UserDTO;
import com.strugglers.InfoOutlet.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Controller is used to connect with the repository which contains data in database and to create APIs.

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

    @GetMapping     //Retrieving data in database using GET method in POSTMAN
    public List<UserDTO> getAll()
    {
        return userService.findAll();
    }

    @GetMapping("/{id}")        //if id was not given it would show error of ambiguous mapping.
    public UserDTO getById(@PathVariable int id)
    {
        return userService.findByID(id);
    }

    @DeleteMapping("/{id}")     //Mappes input from POSTMAN i.e. instead of GET use DELETE
    public String deleteUser(@PathVariable int id)      //takes id of user
    {
        userService.deleteUser((id));       //deletes user
        return "User of id : "+ id + " deleted";
    }

    @PutMapping     //In POSTMAN same as POST where we go to body -> raw and select JSON.
    public UserDTO updateUser(@RequestBody UserDTO userDTO)
    {
        return userService.updateUser(userDTO);
    }
    //@RequestBody requests the whole users where as @PathVariable requests data of the specified id
}
