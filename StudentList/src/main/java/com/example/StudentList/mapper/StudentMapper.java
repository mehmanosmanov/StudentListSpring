package com.example.StudentList.mapper;

import com.example.StudentList.dto.request.StudentRequest;
import com.example.StudentList.dto.response.StudentResponse;
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
                .age(rs.getInt("age"))
                .class_no(rs.getString("class_no"))
                .name(rs.getString("first_name"))
                .surname(rs.getString("last_name")).build();
        return student;
    }

    public Student convertToStudent(StudentRequest studentRequest, String image) {
        return Student.builder()
                .image(image)
                .ID(studentRequest.getID())
                .name(studentRequest.getName())
                .surname(studentRequest.getSurname())
                .class_no(studentRequest.getClass_no())
                .age(studentRequest.getAge()).build();

    }

    public StudentResponse convertToStudentDto(Student student) {
        return StudentResponse.builder()
                .name(student.getName())
                .class_no(student.getClass_no())
                .surname(student.getSurname())
                .age(student.getAge()).build();
    }
}
