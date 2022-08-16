package com.example.demo.restpository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.NhanVien;

public interface NhanVienRestpository extends JpaRepository<NhanVien, String>{
	@Query(value = "SELECT * FROM nhanvien nv WHERE nv.phong_ban = :phong_ban", nativeQuery = true)
	List<NhanVien> findAllByPhongBan(String phong_ban);
	@Query(value = "SELECT * FROM nhanvien nv WHERE nv.vi_tri LIKE 'nhanvien'", nativeQuery = true)
	List<NhanVien> findAllByNhanVien();
	
	

}
