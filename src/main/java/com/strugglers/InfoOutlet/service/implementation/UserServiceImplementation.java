package com.strugglers.InfoOutlet.service.implementation;

import com.strugglers.InfoOutlet.Model.User;
import com.strugglers.InfoOutlet.dto.UserDTO;
import com.strugglers.InfoOutlet.repository.UserRepository;
import com.strugglers.InfoOutlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//Database is connected through repository and UserDto

//We need to add @Service, @RestController, @Repository or @Configuration to use @Autowired
@Service
public class UserServiceImplementation implements UserService {
    @Autowired      //Dependency injection : To use User repository
    private UserRepository userRepository;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User(userDTO);      //We cannot use userDtO directly as we use User in UserRepository so we convert userDTO to user.
        User savedUser = userRepository.save(user);     //Save the value in database.
        UserDTO savedUserDTO = new UserDTO(savedUser);      //Covert savedUser to UserDTO form.
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
        return null;
    }

    @Override
    public void deleteUser(int id) {

    }
}
