package com.example.StudentList.repository.impl;

import com.example.StudentList.mapper.StudentMapper;
import com.example.StudentList.model.Student;
import com.example.StudentList.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final StudentMapper studentMapper;

    @Override
    public void insert(Student student) {
//        jdbcTemplate.update("insert into student_list(class_no,name,surname,age,address,language,FIN) values(?,?,?,?,?,?,?)",
//                student.getClass_no(), student.getName(), student.getSurname(), student.getAge(),
//                student.getAddress(), student.getLanguage(), student.getFIN());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("class_no", student.getClass_no());
        parameters.put("name", student.getName());
        parameters.put("surname", student.getSurname());
        parameters.put("age", student.getAge());
        parameters.put("address", student.getAddress());
        parameters.put("language", student.getLanguage());
        parameters.put("fin", student.getFIN());

        SimpleJdbcInsert simpleJdbcInsert1 = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("student_list").usingGeneratedKeyColumns("id");
        Number id = simpleJdbcInsert1.executeAndReturnKey(parameters);
        System.out.println("Generated id: " + id);
    }


    @Override
    public Student findById(int id) {
        String query = "select * from student_list where ID= ?";
        Student st = jdbcTemplate.queryForObject(query, new Integer[]{id}, studentMapper);
        System.out.println(st);
        return st;
//        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert()
//                .withTableName("EMPLOYEE");


//        return null;
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("delete from student_list where id=?", id);
    }

    @Override
    public int updateById(Student student, int id) {
        return jdbcTemplate.update("update student_list set name=?,surname=?,age=? where id=?",
                student.getName(), student.getSurname(), student.getAge(), id);
    }
}






