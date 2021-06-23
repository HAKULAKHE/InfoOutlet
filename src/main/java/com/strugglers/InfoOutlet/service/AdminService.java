package com.strugglers.InfoOutlet.service;

import com.strugglers.InfoOutlet.Model.Admin;
import com.strugglers.InfoOutlet.Model.User;
import com.strugglers.InfoOutlet.dto.AdminDTO;
import com.strugglers.InfoOutlet.dto.AdminDTO;
import java.util.List;

//This is mainly for adding features of the database into the actual user interface that the user uses so it uses AdminDTO as it is responsible for the interface side data.
public interface AdminService {
    Admin addAdmin(Admin admin);       //Create
    AdminDTO login(String username, String password);
    AdminDTO findByID(int id);
    List<AdminDTO> findAll();        //Retrieve
    AdminDTO updateAdmin(AdminDTO adminDTO);        //Update
    void deleteAdmin(int id);        //Delete
}