package com.strugglers.InfoOutlet.repository;

import com.strugglers.InfoOutlet.Model.Admin;
import com.strugglers.InfoOutlet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {        //if we use User instead of Admin here we get " No property user found for type User!" error.

    List<Admin>findByWorkingShift(String workingShift);
    Optional<Admin>findByUser_usernameAndUser_password(String username, String password);      //firstName which is inside User which is inside Admin

}
