package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeCreateDTO {
    private String username;
    private String fullname;
    private String email;
    private String pass;
    private Date birthday;
    private String roleId;
    private String department_id;
    private String degree;
    private String specialize;
}
