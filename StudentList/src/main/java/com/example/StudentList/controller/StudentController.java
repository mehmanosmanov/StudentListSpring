package com.example.StudentList.controller;

import com.example.StudentList.dto.request.StudentRequest;
import com.example.StudentList.dto.response.StudentResponse;
import com.example.StudentList.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @ApiOperation(value = "Add student", notes = "Adding a new student to the DB.")
    @PostMapping("/create")
    public String create(@RequestBody StudentRequest dto) {
        return studentService.saveStudent(dto);
    }

    @ApiOperation(value = "Get student", notes = "Get student by entered ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESSFULLY"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<StudentResponse> getStudent(@ApiParam(name = "id", value = "Student ID", example = "1") @PathVariable Long id) {
        return new ResponseEntity<>(studentService.getById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESSFULLY"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    @ApiOperation(value = "Get students", notes = "Getting all saved students from DB")
    @GetMapping("/students")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAll();
    }

    @ApiOperation(value = "Update by ID", notes = "Update student by entered ID ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESSFULLY"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    @PutMapping("/update/{id}")
    public String update(@RequestBody StudentRequest studentRequest, @ApiParam(name = "id", value = "Student ID", example = "1") @PathVariable Long id) {
        return studentService.update(studentRequest, id);
    }

    @ApiOperation(value = "Delete by ID", notes = "Delete student by entered ID")
    @DeleteMapping("/delete/{id}")
    public String remove(@ApiParam(name = "id", value = "Student ID", example = "1") @PathVariable Long id) {
        return studentService.deleteById(id);
    }


}
