package com.example.StudentList.service;

import com.example.StudentList.model.dto.response.StudentResponse;
import com.example.StudentList.model.entity.Student;
import com.example.StudentList.mapper.StudentMapper;
import com.example.StudentList.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceWeb {
   private final StudentRepository studentRepository;
   private final StudentMapper studentMapper;

   public List<StudentResponse> getAll() {
      List<StudentResponse> list = new ArrayList<>();
      for (Student student : studentRepository.findAll()) {
         list.add(studentMapper.studentToDto(student));
      }
      return list;
   }

   public void save(Student student) {
      studentRepository.save(student);
   }

   public void deleteViaId(Long id) {
      studentRepository.deleteById(id);
   }


   public StudentResponse getById(Long id) {
      return studentMapper.studentToDto(studentRepository.findById(id).get());
   }
}
