package com.example.StudentList.util;

import com.example.StudentList.dto.request.StudentRequest;
import com.example.StudentList.dto.response.StudentResponse;
import com.example.StudentList.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConvertor {

    public Student convertToStudent(StudentRequest studentRequest, String image) {
        return Student.builder()
                .image(image)
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
