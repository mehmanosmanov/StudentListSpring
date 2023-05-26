package com.example.StudentList.repository.impl;

import com.example.StudentList.mapper.StudentMapper;
import com.example.StudentList.model.Student;
import com.example.StudentList.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final StudentMapper studentMapper;

    @Override
    public void insert(Student student) {
        jdbcTemplate.update("INSERT INTO student_list(class_no,name,surname,age,address,language,FIN) VALUES(?,?,?,?,?,?,?)",
                student.getClass_no(), student.getName(), student.getSurname(), student.getAge(),
                student.getAddress(), student.getLanguage(), student.getFIN());

//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("class_no", student.getClass_no());
//        parameters.put("name", student.getName());
//        parameters.put("surname", student.getSurname());
//        parameters.put("age", student.getAge());
//        parameters.put("address", student.getAddress());
//        parameters.put("language", student.getLanguage());
//        parameters.put("fin", student.getFIN());
//
//        SimpleJdbcInsert simpleJdbcInsert1 = new SimpleJdbcInsert(jdbcTemplate)
//                .withTableName("student_list").usingGeneratedKeyColumns("id");
//        Number id = simpleJdbcInsert1.executeAndReturnKey(parameters);
//        System.out.println("Generated id: " + id);
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("SELECT * FROM student_list", BeanPropertyRowMapper.newInstance(Student.class));
    }


    @Override
    public Student findById(Long id) {

            String query = "select * from student_list where ID= ?";
            return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Student.class), id);

    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from student_list where id=?", id);
    }

    @Override
    public int updateById(Student student, Long id) {
        return jdbcTemplate.update("update student_list set name=?,surname=?,age=? where id=?",
                student.getName(), student.getSurname(), student.getAge(), id);
    }
}






