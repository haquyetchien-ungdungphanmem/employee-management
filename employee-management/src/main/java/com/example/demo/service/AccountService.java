package com.example.demo.service;

import com.example.demo.dto.EmployeeCreateDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Roles;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByUsername(username);
        if (employee != null){
            return User.withUsername(username).password(employee.getPass()).roles(employee.getRoleId().getRoles()).build();
        }
        throw new UsernameNotFoundException("User not found with the name " + username);
    }

    public Employee save(EmployeeCreateDTO employeeCreateDTO){
        Employee employee = new Employee();
        Optional<Roles> roles = roleRepository.findById(employeeCreateDTO.getRoleId());
        Optional<Department> departments = departmentRepository.findById(employeeCreateDTO.getDepartment_id());
        Roles role = roles.get();
        Department department = departments.get();
        employee.setRoleId(role);
        employee.setDepartment_id(department);
        BeanUtils.copyProperties(employeeCreateDTO, employee);

        employee.setPass(passwordEncoder.encode(employeeCreateDTO.getPass()));
        return employeeRepository.save(employee);
    }
}
