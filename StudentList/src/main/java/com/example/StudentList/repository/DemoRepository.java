package com.example.StudentList.repository;

import com.example.StudentList.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository {
    int updateById(Student student, Long id);

}
