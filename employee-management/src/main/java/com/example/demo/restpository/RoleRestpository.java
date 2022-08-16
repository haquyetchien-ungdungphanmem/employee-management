package com.example.demo.restpository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Roles;

public interface RoleRestpository extends JpaRepository<Roles, String>{

}
