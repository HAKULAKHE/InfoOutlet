package com.strugglers.InfoOutlet.dto;

import com.strugglers.InfoOutlet.Model.User;
//Is mapped with Interface((view source in browser)) so if we don't want users to see something like password then we keep it in User as it needs to be stored in the database but don't keep it in UserDTO so that it is not displayed in the interface.
import javax.persistence.Column;

public class UserDTO {      //Got values from Model.User
    private int id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String fullname;        //As an example of difference between User and UserDTO
    public UserDTO() {      //Used in Postman
    }

    public UserDTO(User user){      //Use Alt + enter if User comes in red as the IDE hasn't recognised it as Model.User
        this.id = user.getId();
        this.username = user.getUsername();     //if UserDTO() is empty, the Values in Postman will be null
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.fullname = this.firstName + " " +this.lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //Getter and setter and default empty constructor are needed by jaxkson data binder which is used for the spring web dependency
    //So we need to set getter and setter so that fullname is added to the database

    //alt + Fn + insert to set getter, setter or constructor.

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
