package com.example.StudentList.controller;

import com.example.StudentList.dto.StudentDto;
import com.example.StudentList.model.Student;
import com.example.StudentList.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @ApiOperation(value = "Add student", notes = "Adding a new student to the DB.")
    @PostMapping("/create")
    public String create(@RequestBody StudentDto dto) {
        studentService.saveStudent(dto);
        return "Student added";
    }

    @ApiOperation(value = "Get student", notes = "Get student by entered ID")
    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<StudentDto> getStudent(@PathVariable("id") @ApiParam(name = "id", value = "Student ID", example = "1") Long id) {
        return new ResponseEntity<>(studentService.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get students",notes = "Getting all saved students from DB")
    @GetMapping("/students")
    @ResponseBody
    public List<StudentDto> getAllStudents() {
        return studentService.getAll();
    }

    @ApiOperation(value = "Update by ID", notes = "Update student by entered ID ")
    @PutMapping("/update/{id}")
    public int update(@RequestBody StudentDto student, @PathVariable @ApiParam(name = "Student ID", example = "1") Long id) {
        return  studentService.update(student, id);
    }

    @ApiOperation(value = "Delete by ID", notes = "Delete student by entered ID")
    @DeleteMapping("/delete/{id}")
    public int remove(@PathVariable("id") @ApiParam(name = "id", value = "Student ID", example = "1") Long id) {
        return studentService.deleteById(id);
    }


}
