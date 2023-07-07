package com.example.StudentList.service;

import com.example.StudentList.dto.request.RegisterDto;
import com.example.StudentList.entity.User;
import com.example.StudentList.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
