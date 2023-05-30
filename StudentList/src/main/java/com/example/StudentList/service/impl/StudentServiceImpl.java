package com.example.StudentList.service.impl;

import com.example.StudentList.dto.request.StudentRequest;
import com.example.StudentList.dto.response.StudentResponse;
import com.example.StudentList.exception.AgeLimitException;
import com.example.StudentList.exception.NotFoundException;
import com.example.StudentList.exception.StudentAlreadyExistsException;
import com.example.StudentList.mapper.StudentMapper;
import com.example.StudentList.model.Student;
import com.example.StudentList.repository.DemoRepository;
import com.example.StudentList.repository.StudentRepository;
import com.example.StudentList.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final DemoRepository demoRepository;
    private final StudentMapper studentMapper;

    @Override
    public String saveStudent(StudentRequest request) {
        log.info("Starting to save a new student");
        if (request.getAge() < 16) throw new AgeLimitException();
        if (studentRepository.findById(request.getID()).orElse(null) != null)
            throw new StudentAlreadyExistsException("Student already exists with id=" + request.getID());
        Student student = studentMapper.convertToStudent(request);
        studentRepository.save(student);
        log.info("New student saved {}", request);
        return "Student saved successfully";
    }

    @Override
    public List<StudentResponse> getAll() {
        log.info("Start to get all possible students");
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            studentResponses.add(studentMapper.convertToStudentDto(student));
        }
        if (studentResponses.isEmpty())
            log.warn("Student list is empty");
        log.info("All students are fetched");
        return studentResponses;
    }

    @Override
    public StudentResponse getById(Long id) {
        log.info("Checking of {} id student", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            log.warn("There is no {} id student", id);
            throw new NotFoundException("Not found student such id=" + id);
        } else log.info("{} id student found", id);
        return studentMapper.convertToStudentDto(student);
    }

    @Override
    public String update(StudentRequest studentRequest, Long id) {
        if (getById(id) == null) {
            log.error("Couldn't find student id={}", id);
            throw new NotFoundException("Not found student such id=" + id);
        } else log.info("Updating {} id student", id);
        demoRepository.updateById(studentMapper.convertToStudent(studentRequest), id);
        log.info("{} id student is updated", id);
        return "Student id=" + id + " successfully updated ";
    }

    @Override
    public String deleteById(Long id) {
        if (getById(id) == null) {
            log.error("There is not a student such id " + id);
            throw new NotFoundException("Not found student such id" + id);
        } else log.info("Starting to delete {} id student", id);
        studentRepository.deleteById(id);
        log.info("{} id student is deleted", id);
        return "Student id=" + id + " is successfully deleted";
    }
}