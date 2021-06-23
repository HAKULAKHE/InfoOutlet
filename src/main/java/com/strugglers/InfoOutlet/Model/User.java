package com.strugglers.InfoOutlet.Model;

import com.strugglers.InfoOutlet.dto.UserDTO;
import com.strugglers.InfoOutlet.utils.Roles;

import javax.persistence.*;
//Is mapped with database so represents the structure of the table in the database
@Entity     //Maps objects with database
@Table(name = "user")     //Specify which table to map it with

public class User
{
    @Id     //For primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //user id is auto incremented sequentially not randomly like in AUTO
    private int id;
    @Column(unique = true)      //username are not unique then server error is shown
    private String username;
    private String password;
    @Column(unique = true)      //email are not unique then server error is shown
    private String email;
    private String firstName;
    @Column(name = "my_last_name")      //Just like AS in the mysql queires
    private String lastName;
    private String address;
    private Roles roles;

    public User()
    {
    }

    public User(int id, String username, String password, String email, String firstName, String lastName, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public User(UserDTO userDTO)        //if this is empty, the Values in Postman will be null
    {
        this.id = userDTO.getId();
        this.username = userDTO.getUsername();
        this.address = userDTO.getAddress();
        this.email = userDTO.getEmail();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.password = userDTO.getPassword();
        this.roles = userDTO.getRoles();
    }
    //alt + Fn + insert to set getter, setter or constructor.
    //We need getter setter because this is being done through bin i.e.@Entity, @Table, etc. and there is no other way of inserting data provided by the bin.
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

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
