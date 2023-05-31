package com.example.StudentList.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(length = 10)
    private String class_no;
    @Column(name = "first_name", length = 55)
    private String name;
    @Column(name = "last_name", length = 55)
    private String surname;
    private Integer age;
    private String image;
}





