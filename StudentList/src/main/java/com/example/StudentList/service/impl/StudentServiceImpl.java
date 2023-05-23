package com.example.StudentList.service.impl;

import com.example.StudentList.dto.StudentDto;
import com.example.StudentList.model.Student;
import com.example.StudentList.repository.StudentRepository;
import com.example.StudentList.service.StudentService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public void saveStudent(StudentDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setAge(dto.getAge());
        studentRepository.insert(student);
    }

    @Override
    public StudentDto getById(int id) {
        Student student = studentRepository.findById(id);
        if (student != null)
            return new StudentDto(student.getName(), student.getSurname(), student.getAge());
        else return null;
    }

    @Override
    public int update(StudentDto studentDto, int id) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setAge(studentDto.getAge());
        return studentRepository.updateById(student, id);
    }

    @Override
    public String deleteById(int id) {
        if (studentRepository.deleteById(id) == 1)
            return "Student " + id + " deleted";
        else return "Student is not found";

    }
}
