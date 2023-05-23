package com.example.StudentList.repository;

import com.example.StudentList.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository {

    void insert(Student student);

    Student findById(int id);

    int deleteById(int id);
    int updateById(Student student, int id);
}
