package com.example.StudentList.service;

import com.example.StudentList.dto.request.RegisterDto;
import com.example.StudentList.entity.User;
import com.example.StudentList.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.ContentHandler;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void login(String username, String password)  {
        userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username"));
    }

    @Override
    public void register(RegisterDto registerDto) {
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userRepository.save(user);
    }
}
