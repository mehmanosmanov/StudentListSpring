package com.example.StudentList.service.impl;

import com.example.StudentList.dto.StudentDto;
import com.example.StudentList.mapper.StudentMapper;
import com.example.StudentList.model.Student;
import com.example.StudentList.repository.StudentRepository;
import com.example.StudentList.service.StudentService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public void saveStudent(StudentDto dto) {
        log.info("Starting to save a new student");
        Student student = studentMapper.convertToStudent(dto);
        studentRepository.insert(student);
        log.info("New student saved {}", dto);
    }

    @Override
    public List<StudentDto> getAll() {
        log.info("Start to get all possible students");
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            studentDtos.add(studentMapper.convertToStudentDto(student));
        }
        if (studentDtos.isEmpty())
            log.warn("Student list is empty");
        log.info("All students are fetched");
        return studentDtos;
    }

    @Override
    public StudentDto getById(Long id) {
        try {
            log.info("Checking of {} id student", id);
            Student student = studentRepository.findById(id);
            log.info("{} id student found", id);
            return studentMapper.convertToStudentDto(student);
        } catch (RuntimeException re) {
            log.warn("There is no {} id student", id);
            return null;
        }
    }

    @Override
    public int update(StudentDto studentDto, Long id) {
        try {
            getById(id);
            log.info("Updating {} id student", id);
            int response = studentRepository.updateById(studentMapper.convertToStudent(studentDto), id);
            log.info("{} id student is updated", id);
            return response;
        } catch (RuntimeException re) {
            log.error("Couldn't find student id={}", id);
            return 0;
        }
    }

    @Override
    public int deleteById(Long id) {
        log.info("Starting to delete {} id student", id);
        int response = studentRepository.deleteById(id);
        log.info("{} id student is deleted", id);
        return response;
    }
}