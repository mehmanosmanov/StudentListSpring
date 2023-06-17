package com.example.StudentList.dto.response;

import com.example.StudentList.entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class StudentGroupResponse {
    private String name;
    private String number;
    List<StudentResponse> students = new ArrayList<>();
}
