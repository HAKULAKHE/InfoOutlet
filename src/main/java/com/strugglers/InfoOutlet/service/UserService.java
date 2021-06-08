package com.strugglers.InfoOutlet.service;

import com.strugglers.InfoOutlet.Model.User;
import com.strugglers.InfoOutlet.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);       //Create
    UserDTO findByID(int id);
    List<UserDTO> findAll();        //Retrieve
    UserDTO updateUser(UserDTO userDTO);        //Update
    void deleteUser(int id);        //Delete
}
