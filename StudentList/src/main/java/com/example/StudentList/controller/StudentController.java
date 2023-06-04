package com.example.StudentList.controller;

import com.example.StudentList.dto.request.StudentRequest;
import com.example.StudentList.dto.response.StudentResponse;
import com.example.StudentList.service.StudentService;
import com.example.StudentList.service.StudentServiceImpl;
import com.example.StudentList.util.FileUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    //    private final StudentService studentService;
    private final StudentService studentService;
//    private final FileUtil fileUtil;
//    private final Path upPath = Paths.get("/media/mehman/C0C01276C0127340/JAVA/project_files/upload_from/");
//    private final Path downPath = Paths.get("/media/mehman/C0C01276C0127340/JAVA/project_files/download_to/");

    @ApiOperation(value = "Add student", notes = "Adding a new student to the DB.")
    @PostMapping("/create")
    public String create( StudentRequest dto,@RequestParam MultipartFile file) throws IOException {
        return studentService.saveStudent(dto, file);
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
    public String update(@RequestParam MultipartFile file, StudentRequest studentRequest, @ApiParam(name = "id", value = "Student ID", example = "1") @PathVariable Long id) {
        return studentService.update(studentRequest, id, file.getOriginalFilename());
    }

    @ApiOperation(value = "Delete by ID", notes = "Delete student by entered ID")
    @DeleteMapping("/delete/{id}")
    public String remove(@ApiParam(name = "id", value = "Student ID", example = "1") @PathVariable Long id) {
        return studentService.deleteById(id);
    }

    @GetMapping("/image")
    public ResponseEntity<Resource> getStudentImageById(@RequestParam Long id) {
        return studentService.getStudentImage(id);
    }

//        @PostMapping("/upload")
//    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        log.info("size:{}", file.getSize());
//        log.info("File name:{}", file.getOriginalFilename());
//            Files.copy(file.getInputStream(), this.upPath.resolve(file.getOriginalFilename()));
//    }
////
//    @GetMapping("/download")
//    public ResponseEntity<Resource> download(@RequestParam String fileName) {
//        Resource resource = fileUtil.load(fileName, this.downPath);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
//                .body(resource);
//    }
}
