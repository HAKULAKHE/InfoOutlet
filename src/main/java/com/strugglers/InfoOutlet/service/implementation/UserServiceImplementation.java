package com.strugglers.InfoOutlet.service.implementation;

import com.strugglers.InfoOutlet.Model.Admin;
import com.strugglers.InfoOutlet.Model.User;
import com.strugglers.InfoOutlet.dto.AdminDTO;
import com.strugglers.InfoOutlet.dto.UserDTO;
import com.strugglers.InfoOutlet.repository.UserRepository;
import com.strugglers.InfoOutlet.service.AdminService;
import com.strugglers.InfoOutlet.service.UserService;
import com.strugglers.InfoOutlet.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//Database is connected through repository and UserDto

//We need to add @Service, @RestController, @Repository or @Configuration to use @Autowired
@Service        //Database
public class UserServiceImplementation implements UserService {
    @Autowired      //Dependency injection : To use User repository
    private UserRepository userRepository;

    @Autowired
    private AdminService adminService;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User(userDTO);      //We cannot use userDtO directly as we use User in UserRepository so we convert userDTO to user.
        User savedUser = userRepository.save(user);     //Save the value in database.
        UserDTO savedUserDTO = new UserDTO(savedUser);      //Covert savedUser to UserDTO form.
        if(userDTO.getRoles().equals(Roles.ADMIN))
        {
            Admin admin = new Admin();
            admin.setUser(savedUser);
            admin.setBranch(userDTO.getBranch());
            admin.setPosition(userDTO.getPosition());
            admin.setJoiningDate(userDTO.getJoiningDate());     //Should be inserted in specific formate
            admin.setWorkingShift(userDTO.getWorkingShift());
            adminService.addAdmin(admin);       //Isn't admin null right now?
        }
        return savedUserDTO;        //Returning value in User Repository back to UserDTO (Copied user to userDTO).
        //This is returned to the controller
    }

    @Override
    public UserDTO findByID(int id) {
        Optional<User> userOptional = userRepository.findById(id);      //If there is no matching value of User in Repository i.e. database, the Optional<> sets User to null.
        //If you don't know what statement is corrent use Alt + ctrl + v
//        UserDTO userDTO = null;
//        if(userOptional.isPresent())
//        {
//            userDTO = new UserDTO(userOptional.get());
//        }
//        return userDTO;

        //The above and the below code do the same job
        User user = userOptional.orElseThrow(() -> new RuntimeException("User with given ID not found."));      //orElseThrow() comes from Optional class. This is in case there is no matching value in the database so value of User is null.
        UserDTO userDTO = new UserDTO(user);        //After we retrieve data from User, we convert it to UserDTO
        return userDTO;     //return UserDTO value.
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();        //Retrieving values from database and putting it in users in list form.
        //users.stream().map(UserDTO::new).collect(Collectors.toList());        //This is equivalent to for each given below.
        List<UserDTO> userDTOs = new ArrayList<>();     //Defining a new list to store data that was retrieved from database.
        for (User user : users) {
            UserDTO userDTO = new UserDTO(user);        //copy user to userDTO
            userDTOs.add(userDTO);      //adding each userDTO to list of userDTOs
        }
        return userDTOs;        //We did all of the above to convert the list of data in database which was in User form into UserDTO form.
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        findByID(userDTO.getId());      //Here, we added user defined method findByID() because if ID was not checked and we update when specified id doesn't exist or was deleted it will create a new entry in the next auto_increment id number and not the specified id as id is set according to the auto_increment.
        //here, userDTO is returned if ID not found and as userDTO is not of User type it is not updated.
        User user = new User(userDTO);      //save userDTO into user form
        User updatedUser = userRepository.save(user);       //userRepository.save(user) saves user in database and then returns this saved data to updatedUser. Here, just using user also gives the same value but it is not updated in the database.
        UserDTO updatedUserDTO = new UserDTO(updatedUser);
        return updatedUserDTO;
    }


    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    //Here, deleteById() is given by CrudRepository class which is recursively inherited by the UserRepository class.


    @Override
    public UserDTO login(String username, String password) {
        Optional<User> optionalUser =  userRepository.findByUsernameAndPassword(username,password);

        UserDTO userDTO = null;
        if(optionalUser.isPresent())
        {
            userDTO = new UserDTO(optionalUser.get());
        }
        return userDTO;

                        //OR
//        User user = optionalUser.orElseThrow(() -> new RuntimeException("User with given id not found"));
//        UserDTO userDTO = new UserDTO(user);
//        return userDTO;
    }
}
