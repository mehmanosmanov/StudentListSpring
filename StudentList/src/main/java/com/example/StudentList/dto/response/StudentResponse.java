package com.example.StudentList.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private Long id;
    @ApiModelProperty(notes = "Student name", example = "James", required = true)
    private String name;
    @ApiModelProperty(notes = "Student surname", example = "Gosling", required = true)
    private String surname;
    @ApiModelProperty(notes = "Student age", example = "18", required = true)
    private Integer age;

    private StudentInfoResponse studentInfo;
    private List<TeacherResponse> teachers;

}