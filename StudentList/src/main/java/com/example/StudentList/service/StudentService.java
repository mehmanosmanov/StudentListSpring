package com.example.StudentList.service;

import com.example.StudentList.dto.request.StudentRequest;
import com.example.StudentList.dto.response.StudentResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiComboBoxUI;
import java.io.IOException;
import java.util.List;

@Service
public interface StudentService {

    String saveStudent(StudentRequest studentRequest, MultipartFile file) throws IOException;

    StudentResponse getById(Long id);

    List<StudentResponse> getAll();

    ResponseEntity<Resource> getStudentImage(Long id);

    String update(StudentRequest studentRequest, Long id, String image);

    String deleteById(Long id);


}
