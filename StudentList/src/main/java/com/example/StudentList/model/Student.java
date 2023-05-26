package com.example.StudentList.model;

import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Long ID;
    private Integer class_no;
    private String name;
    private String surname;
    private Integer age;
    private String address;
    private String language;
    private String FIN;
}
