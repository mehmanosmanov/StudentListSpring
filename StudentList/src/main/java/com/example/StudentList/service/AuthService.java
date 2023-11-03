package com.example.StudentList.service;

import com.example.StudentList.model.dto.response.TokenDto;
import com.example.StudentList.model.dto.request.LoginRequest;
import com.example.StudentList.model.dto.request.RegisterDto;
import com.example.StudentList.security.UserPrincipal;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

   TokenDto login(LoginRequest loginRequest);
   TokenDto refresh(UserPrincipal userPrincipal);
   void register(RegisterDto registerDto);
}
