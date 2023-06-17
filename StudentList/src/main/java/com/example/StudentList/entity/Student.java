package com.example.StudentList.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "first_name", length = 55)
    private String name;
    @Column(name = "last_name", length = 55)
    private String surname;

    private Integer age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_info_id")
    private StudentInfo studentInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private StudentGroup studentGroup;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teachers_students",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    private Set<Teacher> teachers = new HashSet<>();

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
        studentGroup.getStudents().add(this);
    }
}