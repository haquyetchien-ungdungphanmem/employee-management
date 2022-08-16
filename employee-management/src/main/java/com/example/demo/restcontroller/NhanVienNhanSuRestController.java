package com.example.demo.restcontroller;

import java.util.List;

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

//@CrossOrigin("*")
@RestController
@RequestMapping("/rest/nvnhansu")
public class NhanVienNhanSuRestController {
	@Autowired
	NhanVienService nhanVienService;
	
	@GetMapping("/getAllByNhanVien")
	public List<NhanVien> findAllByNhanVien(){
		return nhanVienService.findAllByNhanVien();
	}
	
	@PostMapping("/update/{username}")
	public NhanVien update(@PathVariable("username") String username, @RequestBody NhanVien nhanvien) {
		return nhanVienService.update(nhanvien);
	}
}
