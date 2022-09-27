package com.example.demo.restcontroller;

import com.example.demo.entity.NhanVien;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
