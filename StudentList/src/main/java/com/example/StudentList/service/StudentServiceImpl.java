package com.example.StudentList.service;

import com.example.StudentList.dto.request.StudentRequest;
import com.example.StudentList.dto.response.StudentResponse;
import com.example.StudentList.exception.AgeLimitException;
import com.example.StudentList.exception.NotFoundException;
import com.example.StudentList.util.StudentConvertor;
import com.example.StudentList.entity.Student;
import com.example.StudentList.repository.StudentRepository;
import com.example.StudentList.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentConvertor studentConvertor;
    //    @Value("${file.directory.path}")
//    private String path;
    private final Path path1 = Paths.get("/media/mehman/C0C01276C0127340/JAVA/project_files/upload_to");
    private final FileUtil fileUtil;

    @Override
    public String saveStudent(StudentRequest request, MultipartFile image) throws IOException {
        log.info("Starting to save a new student");
        if (request.getAge() < 16) throw new AgeLimitException();
        saveFile(image);
        Student student = studentConvertor.convertToStudent(request, image.getOriginalFilename());
        studentRepository.save(student);
        log.info("New student saved {}", request);
        return "Student saved successfully";
    }

    @Override
    public List<StudentResponse> getAll() {
        log.info("Start to get all possible students");
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            studentResponses.add(studentConvertor.convertToStudentDto(student));
        }
        if (studentResponses.isEmpty()) log.warn("Student list is empty");
        log.info("All students are fetched");
        return studentResponses;
    }

    @Override
    public StudentResponse getById(Long id) {
        log.info("Checking of {} id student", id);
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found student with such id=" + id));
        getFile(student.getImage());
        log.info("{} id student found", id);
        return studentConvertor.convertToStudentDto(student);
    }

    @Override
    public String update(StudentRequest studentRequest, Long id, String image) {
        Student oldStudent = studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found student with such id=" + id));
        log.info("Updating {} id student", id);
        oldStudent.setName(studentRequest.getName());
        oldStudent.setSurname(studentRequest.getSurname());
        oldStudent.setAge(studentRequest.getAge());
        oldStudent.setClass_no(studentRequest.getClass_no());
        studentRepository.save(oldStudent);
        log.info("{} id student is updated", id);
        return "Student with id=" + id + " successfully updated ";
    }

    @Override
    public String deleteById(Long id) {
        studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found student with such id=" + id));
        log.info("Starting to delete {} id student", id);
        studentRepository.deleteById(id);
        log.info("{} id student is deleted", id);
        return "Student id=" + id + " is successfully deleted";
    }

    public ResponseEntity<Resource> getStudentImage(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found student with such id=" + id));
        return getFile(student.getImage());
    }

    public void saveFile(MultipartFile file) throws IOException {
        Files.copy(file.getInputStream(),
                this.path1.resolve(Objects.requireNonNull(file.getOriginalFilename())));
    }

    public ResponseEntity<Resource> getFile(String fileName) {
        Resource resource = fileUtil.load(fileName, this.path1);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}