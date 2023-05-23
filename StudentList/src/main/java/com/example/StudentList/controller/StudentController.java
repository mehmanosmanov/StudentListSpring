package com.example.StudentList.controller;

import com.example.StudentList.dto.StudentDto;
import com.example.StudentList.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @PostMapping("/create")
    public String create(@RequestBody StudentDto dto) {
        studentService.saveStudent(dto);
        return "Student added";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<StudentDto> getStudent(@PathVariable int id) {
            return new ResponseEntity<>(studentService.getById(id), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public int update(@RequestBody StudentDto student, @PathVariable int id) {
        return studentService.update(student, id);
    }

    @DeleteMapping("/delete/{id}")
    public String remove(@PathVariable int id) {
        return studentService.deleteById(id);
    }


}
