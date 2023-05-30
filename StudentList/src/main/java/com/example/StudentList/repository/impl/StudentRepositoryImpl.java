package com.example.StudentList.repository.impl;

import com.example.StudentList.model.Student;
import com.example.StudentList.repository.DemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements DemoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int updateById(Student student, Long id) {
        return jdbcTemplate.update("update students set first_name=?,last_name=?,age=?,class_no=? where id=?",
                student.getName(), student.getSurname(), student.getAge(),student.getClass_no(), id);
    }
}






