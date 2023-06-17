package com.example.StudentList.entity;

import com.example.StudentList.dto.request.StudentRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surName;
    private int age;

    @ManyToMany(mappedBy = "teachers")
    private Set<Student> students = new HashSet<>();

    public void addStudent(Student student) {
        this.students.add(student);
        student.getTeachers().add(this);
    }
}