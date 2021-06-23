package com.strugglers.InfoOutlet.dto;

import com.strugglers.InfoOutlet.Model.Admin;
import com.strugglers.InfoOutlet.Model.User;

import java.time.LocalDate;

public class AdminDTO {
    private int id;
    private String workingShift;
    private LocalDate joiningDate;
    private String branch;
    private String position;
    private UserDTO userDTO;

    public AdminDTO()
    {
    }

    public  AdminDTO(Admin admin)
    {
        this.id = admin.getId();
        this.workingShift = admin.getWorkingShift();
        this.joiningDate = admin.getJoiningDate();
        this.branch = admin.getBranch();
        this.position = admin.getPosition();
        //this.userDTO = new UserDTO(admin.getUser());        //Less good due to type coupling i.e. if there is a case where u need to use this constructor there is a high chance of sending this null.
        //We set the data of userDto in the AdminServiceImplementation.
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

}
