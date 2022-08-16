package com.example.demo.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.NhanVien;
import com.example.demo.service.NhanVienService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/nhanvien")
public class NhanVienRestController {
	
	@Autowired
	NhanVienService nhanvienService;
	
	@GetMapping("/get/{username}")
	public Optional<NhanVien> findNhanVienById(@PathVariable("username") String username) {
		
		return nhanvienService.findById(username);
	}
	
	@PostMapping("/update/{username}")
	public NhanVien update(@PathVariable("username") String username, @RequestBody NhanVien nhanvien) {
		return nhanvienService.update(nhanvien);
	}

}
