package com.example.demo.restpository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PhongBan;

public interface PhongBanRestPository extends JpaRepository<PhongBan, String>{

}
