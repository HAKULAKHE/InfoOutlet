package com.strugglers.InfoOutlet.repository;

import com.strugglers.InfoOutlet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//In spring boot no need to use DBMS queries manually all are done automatically.
@Repository     //We need UserRepository object in the beginning of the program so that database can be connected to it.
public interface UserRepository extends JpaRepository<User, Integer> {      //Integer is the wrapper class of data type of the primary key
    //If we use UserDTO it shows error.
}
