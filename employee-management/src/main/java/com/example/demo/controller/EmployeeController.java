package com.example.demo.controller;


import com.example.demo.dto.EmployeeCreateDTO;
import com.example.demo.dto.EmployeeUpdateDTO;
import com.example.demo.entity.Employee;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;


import java.util.List;


@RestController
@RequestMapping("/api/management")
public class EmployeeController extends BaseController {

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('Giám Đốc')")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('Giám Đốc')")
    public ResponseEntity<?> create(@RequestBody EmployeeCreateDTO employeeCreateDTO) {
        return ResponseEntity.ok(accountService.save(employeeCreateDTO));
    }

    @PostMapping("/update")
    @PermitAll
    public Employee update(@RequestParam("id") Integer id, @RequestBody EmployeeUpdateDTO employeeUpdateDTO,
                           HttpServletRequest httpServletRequest) {

        try {
            String token = jwtFilter.jwtFromRequest(httpServletRequest);
            String usernameLogin = jwtUtil.getUsernameFromToken(token);
            Employee employeeLogin = employeeService.findByUsername(usernameLogin);
            Employee employeeUpdate = employeeService.findEmployeeById(id);
            String username = employeeUpdate.getUsername();
            if (employeeLogin.getUsername().equals(username)
                    && employeeLogin.getRoleId().getId().equals(nhanVien)
                    || employeeLogin.getRoleId().getId().equals(truongPhong)
                    || employeeLogin.getRoleId().getId().equals(nhanVienNhanSu)
                    || employeeLogin.getRoleId().getId().equals(giamDoc)) {
                return update(employeeUpdateDTO, employeeUpdate);
            } else if (employeeLogin.getUsername() != username
                    && employeeLogin.getRoleId().getId().equals(truongPhong)
                    && employeeLogin.getDepartment_id().equals(employeeUpdate.getDepartment_id())
                    && employeeUpdate.getRoleId().getId().equals(nhanVien)) {
                return update(employeeUpdateDTO, employeeUpdate);
            } else if (employeeLogin.getUsername() != username
                    && employeeLogin.getRoleId().getId().equals(nhanVienNhanSu)
                    && employeeUpdate.getRoleId().getId().equals(nhanVien)) {
                return update(employeeUpdateDTO, employeeUpdate);
            } else if (employeeLogin.getRoleId().getId().equals(giamDoc)) {
                return update(employeeUpdateDTO, employeeUpdate);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/deleteInDepartment")
    @PreAuthorize("hasRole('Trưởng Phòng')")
    public Employee deleteInDepartment(@RequestParam("id") Integer id
            , HttpServletRequest httpServletRequest) {
        try {
            String token = jwtFilter.jwtFromRequest(httpServletRequest);
            String usernameLogin = jwtUtil.getUsernameFromToken(token);
            Employee employeeLogin = employeeService.findByUsername(usernameLogin);
            Employee employeeDelete = employeeService.findEmployeeById(id);
            if (employeeLogin.getDepartment_id() != employeeDelete.getDepartment_id()) {
                throw new Exception();
            } else {
                employeeDelete.setDepartment_id(null);
                return employeeService.save(employeeDelete);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/createInDepartment")
    @PreAuthorize("hasRole('Trưởng Phòng')")
    public Employee createInDepartment(@RequestParam("id") Integer id
            , HttpServletRequest httpServletRequest) {
        try {
            String token = jwtFilter.jwtFromRequest(httpServletRequest);
            String usernameLogin = jwtUtil.getUsernameFromToken(token);
            Employee employeeLogin = employeeService.findByUsername(usernameLogin);
            Employee employeeCreate = employeeService.findEmployeeById(id);
            if (employeeCreate.getDepartment_id() == null && employeeCreate.getRoleId().getId().equals(nhanVien)) {
                employeeCreate.setDepartment_id(employeeLogin.getDepartment_id());
                return employeeService.save(employeeCreate);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('Giám Đốc')")
    public void delete(@RequestParam("id") Integer id) {
        employeeService.deleteById(id);
    }


    @GetMapping("/getEmployeeByUsername")
    @PermitAll
    public Employee findNhanVienByUsername(@RequestParam("id") Integer id
            , HttpServletRequest httpServletRequest) {
        try {
            String token = jwtFilter.jwtFromRequest(httpServletRequest);
            String usernameLogin = jwtUtil.getUsernameFromToken(token);
            Employee employeeLogin = employeeService.findByUsername(usernameLogin);

            Employee employeeFind = employeeService.findEmployeeById(id);
            String username = employeeFind.getUsername();
            if (usernameLogin.equals(username)) {
                return employeeService.findEmployeeById(id);
            } else if (employeeLogin.getRoleId().getId().equals(giamDoc)) {
                return employeeService.findEmployeeById(id);
            } else if (employeeLogin.getRoleId().getId().equals(truongPhong)
                    && employeeLogin.getDepartment_id().equals(employeeFind.getDepartment_id())) {
                return employeeService.findEmployeeById(id);
            } else if (employeeLogin.getRoleId().getId().equals(nhanVienNhanSu)) {
                return employeeService.findEmployeeById(id);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getByNhanVien")
    @PreAuthorize("hasAnyRole('Nhân Viên Nhân Sự','Giám Đốc')")
    public List<Employee> findByNhanVien() {
        return employeeService.findAllByNhanVien();
    }

    @GetMapping("/getByDepartment")
    @PreAuthorize("hasAnyRole('Trưởng Phòng','Giám Đốc')")
    public List<Employee> findByPhongBan(@RequestParam("department") String department
            , HttpServletRequest httpServletRequest) {
        try {
            String token = jwtFilter.jwtFromRequest(httpServletRequest);
            String usernameLogin = jwtUtil.getUsernameFromToken(token);
            Employee employeeLogin = employeeService.findByUsername(usernameLogin);

            if (employeeLogin.getRoleId().getId().equals(giamDoc)) {
                return employeeService.findAllByPhongBan(department);
            } else if (employeeLogin.getDepartment_id().getDepartment_id().equals(department)
                    && employeeLogin.getRoleId().getId().equals(truongPhong)) {
                return employeeService.findAllByPhongBan(department);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
