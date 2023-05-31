package com.example.StudentList.validator;

import com.example.StudentList.exception.NotFoundException;
import com.example.StudentList.model.Student;
import com.example.StudentList.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class IDChecker {

    private final StudentRepository studentRepository;

    public Student check(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            log.warn("There is not with id={} student", id);
            throw new NotFoundException("Not found student such id=" + id);
        }
        return student;
    }
}