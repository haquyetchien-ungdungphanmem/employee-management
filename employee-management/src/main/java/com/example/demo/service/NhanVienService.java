package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.NhanVien;

public interface NhanVienService {

	Optional<NhanVien> findById(String username);

	NhanVien update(NhanVien nhanvien);

	List<NhanVien> findAll();

	void save(NhanVien nhanvien);

	List<NhanVien> findAllByPhongBan(String phong_ban);

	List<NhanVien> findAllByNhanVien();

	void deleteById( String username);

	

	
}
