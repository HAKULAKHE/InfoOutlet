package com.strugglers.InfoOutlet.Model;


import com.strugglers.InfoOutlet.dto.AdminDTO;
import com.strugglers.InfoOutlet.dto.UserDTO;
import com.strugglers.InfoOutlet.utils.Roles;

import javax.persistence.*;
import java.time.LocalDate;

//Is mapped with database so represents the structure of the table in the database
@Entity     //Maps objects with database
@Table(name = "admin")       //Specify which table to map it with
public class Admin {
    @Id     //For primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //user id is auto incremented sequentially not randomly like in AUTO
    private int id;
    private String workingShift;
    private LocalDate joiningDate;
    private String branch;
    private String position;


    @OneToOne(fetch = FetchType.LAZY)       //When Admin is queried if we use EAGER then all data is retrieved and if LAZY is used only the necessary data is retrieved and the rest is retrieved when needed. Here, user foreign key is the unnecessary data.
    @JoinColumn(name = "user_id")       //user_id is a column in the admin table where the user data is mapped
    private User user;

    public  Admin()
    {}

    public Admin(int id, String workingShift, LocalDate joiningDate, String branch, String position, User user) {
        this.id = id;
        this.workingShift = workingShift;
        this.joiningDate = joiningDate;
        this.branch = branch;
        this.position = position;
        this.user = user;
    }

    public  Admin(AdminDTO adminDTO)
    {
        this.id = adminDTO.getId();
        this.workingShift = adminDTO.getWorkingShift();
        this.joiningDate = adminDTO.getJoiningDate();
        this.branch = adminDTO.getBranch();
        this.position = adminDTO.getPosition();
        //User related is set in Implementation
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkingShift() {
        return workingShift;
    }

    public void setWorkingShift(String workingShift) {
        this.workingShift = workingShift;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}