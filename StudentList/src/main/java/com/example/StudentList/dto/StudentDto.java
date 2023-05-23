package com.example.StudentList.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String name;
    private String surname;
    private int age;
}