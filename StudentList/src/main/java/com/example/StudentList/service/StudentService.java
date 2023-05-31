package com.example.StudentList.service;

import com.example.StudentList.dto.request.StudentRequest;
import com.example.StudentList.dto.response.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    String saveStudent(StudentRequest studentRequest,String fileName);

    StudentResponse getById(Long id);

    List<StudentResponse> getAll();

    String update(StudentRequest studentRequest, Long id);

    String deleteById(Long id);


}
