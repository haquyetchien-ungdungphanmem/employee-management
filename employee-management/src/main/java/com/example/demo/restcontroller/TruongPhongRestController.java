package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.NhanVien;
import com.example.demo.service.NhanVienService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/truongphong")
public class TruongPhongRestController {
	@Autowired
	NhanVienService nhanVienService;
	
	
	@GetMapping("{phong_ban}")
	public List<NhanVien> findAllByPhongBan(@PathVariable("phong_ban") String phong_ban){
		return nhanVienService.findAllByPhongBan(phong_ban);
	}
	@PostMapping("/update/{username}")
	public NhanVien update(@PathVariable("username") String username, @RequestBody NhanVien nhanvien) {
		return nhanVienService.update(nhanvien);
	}
}
