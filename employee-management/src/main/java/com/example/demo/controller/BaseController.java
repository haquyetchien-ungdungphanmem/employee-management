package com.example.demo.controller;

import com.example.demo.dto.EmployeeUpdateDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Roles;
import com.example.demo.jwt.JwtFilter;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class BaseController {
    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    EmployeeService employeeService;



    @Autowired
    AccountService accountService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public static final String giamDoc = "giamdoc";
    public static final String nhanVien = "nhanvien";
    public static final String nhanVienNhanSu = "nhanviennhansu";
    public static final String truongPhong = "truongphong";



    public Employee update(EmployeeUpdateDTO employeeUpdateDTO, Employee employeeUpdate){
        Optional<Roles> roles = roleRepository.findById(employeeUpdateDTO.getRoleId());
        Optional<Department> departments = departmentRepository.findById(employeeUpdateDTO.getDepartment_id());
        Roles role = roles.get();
        Department department = departments.get();
        employeeUpdate.setRoleId(role);
        employeeUpdate.setDepartment_id(department);
        BeanUtils.copyProperties(employeeUpdateDTO, employeeUpdate);
        return employeeService.save(employeeUpdate);
    }


}
