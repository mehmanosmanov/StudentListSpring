package com.example.StudentList.controller;

import com.example.StudentList.dto.TokenDto;
import com.example.StudentList.dto.request.LoginRequest;
import com.example.StudentList.dto.request.RegisterDto;
import com.example.StudentList.model.TokenType;
import com.example.StudentList.security.JwtTokenProvider;
import com.example.StudentList.security.UserPrincipal;
import com.example.StudentList.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(//???
                        request.getUsername(),
                        request.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal(), TokenType.ACCESS_TOKEN));
        tokenDto.setRefreshToken(tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal(), TokenType.REFRESH_TOKEN));
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/logout")
    public String logOut(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return "log out";
    }



    @PostMapping("/refresh")//@AuthenticationPrincipal login bu url ancaq login olan userler call ede biler
    public ResponseEntity<TokenDto> refreshToken(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(tokenProvider.generateToken(userPrincipal, TokenType.ACCESS_TOKEN));
        tokenDto.setRefreshToken(tokenProvider.generateToken(userPrincipal, TokenType.REFRESH_TOKEN));
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterDto registerDto) {
        authService.register(registerDto);

    }
}
