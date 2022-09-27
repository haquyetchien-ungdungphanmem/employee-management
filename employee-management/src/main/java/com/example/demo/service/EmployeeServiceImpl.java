package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
    EmployeeRepository nhVienRestpository;

	@Autowired
	PasswordEncoder passwordEncoder;



	@Override
	public Employee update(Employee nhanvien) {

		return nhVienRestpository.save(nhanvien);
	}

	@Override
	public List<Employee> findAll() {
		
		return nhVienRestpository.findAll();
	}

	@Override
	public Employee save(Employee nhanvien) {

		nhVienRestpository.save(nhanvien);
		return nhanvien;
	}

	@Override
	public List<Employee> findAllByPhongBan(String phong_ban) {
		
		return nhVienRestpository.findAllByPhongBan(phong_ban);
	}

	@Override
	public List<Employee> findAllByNhanVien() {
		
		return nhVienRestpository.findAllByNhanVien();
	}

	@Override
	public void deleteById(Integer id) {
		nhVienRestpository.deleteById(id);
		
	}

	@Override
	public Optional<Employee> findNhanVienByUsername(String username) {
		return nhVienRestpository.findNhanVienByUsername(username);
	}

	@Override
	public Employee findByUsername(String usernameLogin) {
		return nhVienRestpository.findByUsername(usernameLogin);
	}

	@Override
	public Employee findEmployeeById(Integer id) {
		return nhVienRestpository.findEmployeeById(id);
	}


}
