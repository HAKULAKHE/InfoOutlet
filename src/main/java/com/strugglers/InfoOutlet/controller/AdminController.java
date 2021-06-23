package com.strugglers.InfoOutlet.controller;

import com.strugglers.InfoOutlet.Model.Admin;
import com.strugglers.InfoOutlet.dto.AdminDTO;
import com.strugglers.InfoOutlet.service.AdminService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Controller is used to connect with the repository which contains data in database and to create APIs.

//We need to add @Service, @RestController, @Repository or @Configuration to use @Autowired
@RestController
@RequestMapping("/admins")     //Any request to server(Url) from any port it denote that this will be managed by user controller
public class AdminController {
    @Autowired
    private AdminService adminService;


    //This is accessed using POSTMAN; METHOD=POST | localhost:8080/users if @PostMapping("/addUser")used we need to specify localhost:8080/users/addUser
    @PostMapping      //Save and add data from controller; @PostMapping("/addUser") would be easier but according to convention we should use the @RequestMapping("/users") for all task rather than adding new things.
    public Admin addAdmin(@RequestBody AdminDTO adminDTO)
    {
        return adminService.addAdmin(new Admin(adminDTO));
    }

    @GetMapping     //Retrieving data in database using GET method in POSTMAN
    public List<AdminDTO> getAll()
    {
        return adminService.findAll();
    }

    @GetMapping("/{id}")        //if id was not given it would show error of ambiguous mapping.
    public AdminDTO getById(@PathVariable int id)
    {
        return adminService.findByID(id);
    }

    @DeleteMapping("/{id}")     //Maps input from POSTMAN i.e. instead of GET use DELETE
    public String deleteUser(@PathVariable int id)      //takes id of user
    {
        adminService.deleteAdmin((id));       //deletes user
        return "Admin of id : "+ id + " deleted";
    }

    @PutMapping     //In POSTMAN same as POST where we go to body -> raw and select JSON.
    public AdminDTO updateUser(@RequestBody AdminDTO adminDTO)
    {
        return adminService.updateAdmin(adminDTO);
    }
    //@RequestBody requests the whole users where as @PathVariable requests data of the specified id

    @GetMapping("/login")
    public  AdminDTO login(@RequestParam String username, @RequestParam String password)
    {
        return adminService.login(username, password);
    }
}