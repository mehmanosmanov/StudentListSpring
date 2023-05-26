package com.example.StudentList.mapper;

import com.example.StudentList.dto.StudentDto;
import com.example.StudentList.model.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (rs.next()) return null;
        Student student = Student.builder()
                .ID(rs.getLong("id"))
                .FIN(rs.getString("fin"))
                .address(rs.getString("address"))
                .age(rs.getInt("age"))
                .class_no(rs.getInt("class_no"))
                .language(rs.getString("language"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname")).build();

        return student;
    }

    public Student convertToStudent(StudentDto studentDto) {
        return Student.builder()
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .age(studentDto.getAge()).build();
    }

    public StudentDto convertToStudentDto(Student student) {
        return StudentDto.builder()
                .name(student.getName())
                .surname(student.getSurname())
                .age(student.getAge()).build();
    }
}
