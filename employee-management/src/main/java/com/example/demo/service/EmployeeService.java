package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Employee;

public interface EmployeeService {



	Employee update(Employee nhanvien);

	List<Employee> findAll();

	Employee save(Employee nhanvien);

	List<Employee> findAllByPhongBan(String department);

	List<Employee> findAllByNhanVien();

	void deleteById( Integer id);


    Optional<Employee> findNhanVienByUsername(String username);


	Employee findByUsername(String usernameLogin);


	Employee findEmployeeById(Integer id);
}
