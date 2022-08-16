package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.NhanVien;
import com.example.demo.restpository.NhanVienRestpository;
import com.example.demo.service.NhanVienService;

@Service
public class NhanVienServiceImpl implements NhanVienService{
	
	@Autowired
	NhanVienRestpository nhVienRestpository;

	@Override
	public Optional<NhanVien> findById(String username) {
		
		return nhVienRestpository.findById(username);
	}

	@Override
	public NhanVien update(NhanVien nhanvien) {
		
		return nhVienRestpository.save(nhanvien);
	}

	@Override
	public List<NhanVien> findAll() {
		
		return nhVienRestpository.findAll();
	}

	@Override
	public void save(NhanVien nhanvien) {
		nhVienRestpository.save(nhanvien);
	}

	@Override
	public List<NhanVien> findAllByPhongBan(String phong_ban) {
		
		return nhVienRestpository.findAllByPhongBan(phong_ban);
	}

	@Override
	public List<NhanVien> findAllByNhanVien() {
		
		return nhVienRestpository.findAllByNhanVien();
	}

	@Override
	public void deleteById(String username) {
		nhVienRestpository.deleteById(username);
		
	}

	

	

	
	
	
	
}
