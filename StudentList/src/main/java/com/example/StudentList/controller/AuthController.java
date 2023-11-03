package com.example.StudentList.controller;

import com.example.StudentList.model.dto.response.TokenDto;
import com.example.StudentList.model.dto.request.LoginRequest;
import com.example.StudentList.model.dto.request.RegisterDto;
import com.example.StudentList.model.dto.response.StudentGroupResponse;
import com.example.StudentList.security.JwtTokenProvider;
import com.example.StudentList.security.UserPrincipal;
import com.example.StudentList.service.AuthService;
import com.example.StudentList.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

   private final JwtTokenProvider tokenProvider;
   private final StudentService studentService;

   private final AuthenticationManager authenticationManager;
   private final AuthService authService;

   @PostMapping("/login")
   public ResponseEntity<TokenDto> login(@RequestBody LoginRequest loginRequest) {
      return ResponseEntity.ok(authService.login(loginRequest));
   }

   @PostMapping("/refresh")//@AuthenticationPrincipal login bu url ancaq login olan userler call ede biler
   public ResponseEntity<TokenDto> refreshToken(@AuthenticationPrincipal UserPrincipal userPrincipal) {
      return ResponseEntity.ok(authService.refresh(userPrincipal));
   }

   @PostMapping("/register")
   public void register(@RequestBody RegisterDto registerDto) {
      authService.register(registerDto);
   }

//   @PostMapping("/logout")
//   public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) {
//      // Clear the authentication
//      if (authentication != null) {
//         new SecurityContextLogoutHandler().logout(request, response, authentication);
//      }
//      // Clear the session
//      HttpSession session = request.getSession(false);
//      if (session != null) {
//         session.invalidate();
//      }
//
//      // Perform additional logout-related tasks
//
//      // Return a response indicating successful logout
//
//      return ResponseEntity.ok("Logged out successfully");
//   }

   @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESSFULLY"), @ApiResponse(code = 404, message = "NOT FOUND")})
   @ApiOperation(value = "Get students", notes = "Getting all saved students from DB")
   @GetMapping("/students")
   public ResponseEntity<List<StudentGroupResponse>> getAllStudents() {
      return ResponseEntity.ok(studentService.getAll());
   }
}
