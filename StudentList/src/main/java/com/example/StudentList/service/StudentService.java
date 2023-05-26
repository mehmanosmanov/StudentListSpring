package com.example.StudentList.service;

import com.example.StudentList.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    void saveStudent(StudentDto dto);

    StudentDto getById(Long id);

    List<StudentDto> getAll();

    int update(StudentDto studentDto, Long id);

    int deleteById(Long id);


}
