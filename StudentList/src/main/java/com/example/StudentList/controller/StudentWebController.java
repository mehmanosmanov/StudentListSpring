package com.example.StudentList.controller;

import com.example.StudentList.dto.request.RegisterDto;
import com.example.StudentList.dto.response.StudentResponse;
import com.example.StudentList.entity.Student;
import com.example.StudentList.service.AuthService;
import com.example.StudentList.service.StudentServiceWeb;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/web")
public class StudentWebController {

    private final StudentServiceWeb studentService;
    private final AuthService authService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String viewHomePage(Model model) {
        List<StudentResponse> dtos = studentService.getAll();
        model.addAttribute("students", dtos);
        return "index";
    }

    @RequestMapping(value = "/addnew",method = RequestMethod.GET)
    public String addNewEmployee(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "newstudent";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/web/";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/web/";
    }
    @RequestMapping(value = "/showFormForUpdate/{id}",method = RequestMethod.GET)
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        StudentResponse studentResponse = studentService.getById(id);
        model.addAttribute("student", studentResponse);
        return "update";
    }

    @RequestMapping(value = "/deleteStudent/{id}",method = RequestMethod.GET)
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        studentService.deleteViaId(id);
        return "redirect:/web/";
    }

    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String register(Model model){
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("register",registerDto);
        return "register2";
    }

    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public String regInfo(@ModelAttribute ("regDto") RegisterDto registerDto) {
        authService.register(registerDto);
        return "redirect:/web/";
    }
}
