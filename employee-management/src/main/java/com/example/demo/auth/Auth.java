package com.example.demo.auth;

import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Auth extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountService accountService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(accountService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().cors().disable();

        http.authorizeHttpRequests().antMatchers("/rest/giamdoc/*").hasRole("Giám Đốc")
                .antMatchers("/rest/nhanvien/*").hasRole("Nhân Viên")
                .antMatchers("/rest/truongphong/*").hasRole("Trưởng Phòng")
                .antMatchers("/rest/nvnhansu/*").hasRole("Nhân Viên Nhân Sự")
                .anyRequest().authenticated();

        http.httpBasic();
    }
}
