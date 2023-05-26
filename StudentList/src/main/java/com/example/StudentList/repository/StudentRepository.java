package com.example.StudentList.repository;

import com.example.StudentList.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository {

    void insert(Student student);
    Student findById(Long id);
    List<Student> findAll();
    int deleteById(Long id);
    int updateById(Student student, Long id);
}
