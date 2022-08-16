package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.NhanVien;
import com.example.demo.service.NhanVienService;

//@CrossOrigin()
@RestController
@RequestMapping("/rest/giamdoc")
public class GiamDocRestController {
	@Autowired
	NhanVienService nhanVienService;
	
	@GetMapping("/getAll")
	public List<NhanVien> findAll(){
		return nhanVienService.findAll();
	}
	
	@PostMapping("/create")
	public NhanVien create(@RequestBody NhanVien nhanvien) {
		nhanVienService.save(nhanvien);
		return nhanvien;
	}
	
	@PostMapping("/update/{username}")
	public NhanVien update(@PathVariable("username") String username, @RequestBody NhanVien nhanVien) {
		return nhanVienService.update(nhanVien);	
	}
	
	@PostMapping("/delete/{username}")
	public void delete(@PathVariable("username") String username) {
		nhanVienService.deleteById(username);
	}
	
}
