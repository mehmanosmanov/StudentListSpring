package com.example.StudentList.model;

import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private long ID;
    private int class_no;
    private String name;
    private String surname;
    private int age;
    private String address;
    private String language;
    private String FIN;
}
