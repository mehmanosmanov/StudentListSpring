package com.example.StudentList.service;

import com.example.StudentList.dto.request.RegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

   void login(String username, String password);

   void register(RegisterDto registerDto);
}
