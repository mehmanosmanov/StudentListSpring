package com.example.StudentList.controller;

import com.example.StudentList.dto.request.StudentRequest;
import com.example.StudentList.dto.response.StudentResponse;
import com.example.StudentList.service.StudentService;
import com.example.StudentList.util.FileUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    private final StudentService studentService;

    private final Path path = Paths.get("D:\\java\\StudentsPics");
    private final FileUtil fileUtil;


    @ApiOperation(value = "Add student", notes = "Adding a new student to the DB.")
    @PostMapping("/create")
    public String create(@RequestParam MultipartFile file, StudentRequest dto) {
        return studentService.saveStudent(dto,file.getOriginalFilename());


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

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("size:{}", file.getSize());
        log.info("File name:{}", file.getOriginalFilename());
        file.transferTo(path);
        Files.copy(file.getInputStream(), this.path.resolve(file.getOriginalFilename()));

    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam String fileName) {
        Resource resource = fileUtil.load(fileName, this.path);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}
