package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	@Query(value = "SELECT * FROM employee nv WHERE nv.department_id = :department", nativeQuery = true)
	List<Employee> findAllByPhongBan(String department);
	@Query(value = "SELECT * FROM employee nv WHERE nv.role_id LIKE 'nhanvien'", nativeQuery = true)
	List<Employee> findAllByNhanVien();




	@Query(value = "SELECT o.* FROM employee o JOIN roles ON roles.Id = o.role_id " +
			"WHERE role_id LIKE 'nhanvien' AND username LIKE :username", nativeQuery = true)
	Optional<Employee> findNhanVienByUsername(String username);




	Employee findByUsername(String username);

	Employee findEmployeeById(Integer id);




}
