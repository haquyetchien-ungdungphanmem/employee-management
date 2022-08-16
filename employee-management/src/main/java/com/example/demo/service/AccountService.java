package com.example.demo.service;

import com.example.demo.entity.NhanVien;
import com.example.demo.restpository.NhanVienRestpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    NhanVienRestpository nhanVienRestpository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            NhanVien nhanVien = nhanVienRestpository.findById(username).get();
            String pass = nhanVien.getPass();
            String roles = nhanVien.getRoleId().getRoles();
            return User.withUsername(username).password(passwordEncoder.encode(pass)).roles(roles).build();
        }catch (Exception e){
            throw  new UsernameNotFoundException(username+"not found");
        }
    }
}
