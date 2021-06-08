package com.strugglers.InfoOutlet.service.implementation;

import com.strugglers.InfoOutlet.Model.User;
import com.strugglers.InfoOutlet.dto.UserDTO;
import com.strugglers.InfoOutlet.repository.UserRepository;
import com.strugglers.InfoOutlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(int id) {

    }
}
