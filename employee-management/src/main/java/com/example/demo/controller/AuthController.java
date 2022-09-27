package com.example.demo.controller;

import com.example.demo.dto.EmployeeCreateDTO;
import com.example.demo.dto.SinginDTO;
import com.example.demo.dto.ResponseTokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController{

    @PostMapping("/singin")
    public ResponseEntity<?> singin(@RequestBody SinginDTO requestDTO) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPass()));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = accountService.loadUserByUsername(requestDTO.getUsername());

        final String token = jwtUtil.GenerateToken(userDetails);

        return ResponseEntity.ok(new ResponseTokenDTO(token));
    }

}
