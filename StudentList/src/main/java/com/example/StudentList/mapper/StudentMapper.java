package com.example.StudentList.mapper;

import com.example.StudentList.model.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

        Student student = Student.builder()
                .ID(rs.getInt("id"))
                .FIN(rs.getString("fin"))
                .address(rs.getString("address"))
                .age(rs.getInt("age"))
                .class_no(rs.getInt("class_no"))
                .language(rs.getString("language"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname")).build();

        return student;
    }
}
