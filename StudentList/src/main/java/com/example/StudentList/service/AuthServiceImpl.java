package com.example.StudentList.service;

import com.example.StudentList.model.dto.response.TokenDto;
import com.example.StudentList.model.dto.request.LoginRequest;
import com.example.StudentList.model.dto.request.RegisterDto;
import com.example.StudentList.model.entity.User;
import com.example.StudentList.model.TokenType;
import com.example.StudentList.repository.UserRepository;
import com.example.StudentList.security.JwtTokenProvider;
import com.example.StudentList.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final AuthenticationManager authenticationManager;
   private final JwtTokenProvider tokenProvider;


   @Override
   public TokenDto login(LoginRequest loginRequest) {
      userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Invalid username"));

      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(//???
                      loginRequest.getUsername(),
                      loginRequest.getPassword()
              ));
      SecurityContextHolder.getContext().setAuthentication(authentication);

      TokenDto tokenDto = new TokenDto();
      tokenDto.setAccessToken(tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal(), TokenType.ACCESS_TOKEN));
      tokenDto.setRefreshToken(tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal(), TokenType.REFRESH_TOKEN));
      return tokenDto;
   }

   @Override
   public TokenDto refresh(UserPrincipal userPrincipal) {
      TokenDto tokenDto = new TokenDto();
      tokenDto.setAccessToken(tokenProvider.generateToken(userPrincipal, TokenType.ACCESS_TOKEN));
      tokenDto.setRefreshToken(tokenProvider.generateToken(userPrincipal, TokenType.REFRESH_TOKEN));
      return tokenDto;
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