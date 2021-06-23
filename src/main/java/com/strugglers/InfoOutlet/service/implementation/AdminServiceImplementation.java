package com.strugglers.InfoOutlet.service.implementation;
import com.strugglers.InfoOutlet.Model.Admin;
import com.strugglers.InfoOutlet.Model.User;
import com.strugglers.InfoOutlet.dto.AdminDTO;
import com.strugglers.InfoOutlet.dto.UserDTO;
import com.strugglers.InfoOutlet.repository.AdminRepository;
import com.strugglers.InfoOutlet.repository.UserRepository;
import com.strugglers.InfoOutlet.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//Database is connected through repository and AdminDTO

//We need to add @Service, @RestController, @Repository or @Configuration to use @Autowired
@Service        //Database, Creates Bean, Gives methods which are defined within and applied through Annotation.
public class AdminServiceImplementation implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Admin addAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    @Override
    public AdminDTO findByID(int id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);

        Admin admin = adminOptional.orElseThrow(() -> new RuntimeException("Admin with given ID not found."));      //orElseThrow() comes from Optional class. This is in case there is no matching value in the database so value of Admin is null.
        AdminDTO adminDTO = new AdminDTO(admin);        //After we retrieve data from Admin, we convert it to AdminDTO
        UserDTO userDTO = new UserDTO(admin.getUser());     //As userDTO is not set in constructor
        adminDTO.setUserDTO(userDTO);
        return adminDTO;     //return AdminDTO value.
    }

    @Override
    public List<AdminDTO> findAll() {
        List<Admin> admins = adminRepository.findAll();        //Retrieving values from database and putting it in users in list form.
        //users.stream().map(AdminDTO::new).collect(Collectors.toList());        //This is equivalent to for each given below.
        List<AdminDTO> adminDTOs = new ArrayList<>();     //Defining a new list to store data that was retrieved from database.
        for (Admin admin : admins) {
            AdminDTO adminDTO = new AdminDTO(admin);        //copy user to AdminDTO
            UserDTO userDTO = new UserDTO(admin.getUser());     //Cause we didn't map the value of userDTO in the constructor
            adminDTO.setUserDTO(userDTO);
            adminDTOs.add(adminDTO);      //adding each AdminDTO to list of AdminDTOs
        }
        return adminDTOs;        //We did all of the above to convert the list of data in database which was in User form into AdminDTO form.
    }

    @Override
    public AdminDTO updateAdmin(AdminDTO adminDTO ) {
        Optional<Admin> optionalAdmin = adminRepository.findById(adminDTO.getId());
        Admin admin = optionalAdmin.orElseThrow(() -> new RuntimeException("Admin with given ID not found."));
        admin.setPosition(adminDTO.getPosition());
        admin.setBranch(adminDTO.getBranch());
        admin.setWorkingShift(adminDTO.getWorkingShift());
        admin.setJoiningDate(adminDTO.getJoiningDate());

        //UserDTO to updated user related data.
        UserDTO userDTO = adminDTO.getUserDTO();
        //User associated to the admin
        User user = admin.getUser();
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        //Save the updatedUser to user repository
        User updatedUser = userRepository.save(user);
        //set the updated user to admin
        admin.setUser(updatedUser);

        //save the updated admin
        Admin updatedAdmin = adminRepository.save(admin);
        //convert the updated admin and user to DTOs so that they can be sent back to the interface side
        AdminDTO updatedAdminDTO = new AdminDTO(updatedAdmin);
        updatedAdminDTO.setUserDTO(new UserDTO(updatedAdmin.getUser()));
        return updatedAdminDTO;
    }


    @Override
    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }
    //Here, deleteById() is given by CrudRepository class which is recursively inherited by the AdminRepository class.


    @Override
    public AdminDTO login(String username, String password) {
        Optional<Admin> optionalAdmin =  adminRepository.findByUser_usernameAndUser_password(username,password);     //Here, i have change the previously set Optional as List due to error

        AdminDTO adminDTO = null;
        if(optionalAdmin.isPresent())
        {
            adminDTO= new AdminDTO(optionalAdmin.get());
        }
        return adminDTO;

        //OR
//        User user = optionalUser.orElseThrow(() -> new RuntimeException("User with given id not found"));
//        AdminDTO AdminDTO = new AdminDTO(user);
//        return AdminDTO;
    }
}
