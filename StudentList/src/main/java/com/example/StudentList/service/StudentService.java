package com.example.StudentList.service;

import com.example.StudentList.dto.StudentDto;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    void saveStudent(StudentDto dto);
    StudentDto getById(int id);
    int update(StudentDto studentDto,int id);
    String deleteById(int id);


}
