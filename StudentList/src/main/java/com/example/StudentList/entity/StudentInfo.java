package com.example.StudentList.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "student_info")
@Setter
@Getter
@ToString
public class StudentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String phoneNumber;
    @Column(length = 25)
    private String address;
    @Column(length = 25)
    private String email;
    @Column(length = 55)
    private String image;
}
