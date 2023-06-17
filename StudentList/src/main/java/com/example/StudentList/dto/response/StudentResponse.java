package com.example.StudentList.dto.response;

import com.example.StudentList.entity.StudentInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    @ApiModelProperty(notes = "Student name", example = "James", required = true)
    private String name;
    @ApiModelProperty(notes = "Student surname", example = "Gosling", required = true)
    private String surname;
    @ApiModelProperty(notes = "Student age", example = "18", required = true)
    private Integer age;

    private StudentInfo studentInfo;
    private List<TeacherResponse> teachers = new ArrayList<>();

}