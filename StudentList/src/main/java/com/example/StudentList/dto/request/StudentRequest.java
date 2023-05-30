package com.example.StudentList.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    @ApiModelProperty(notes = "ID", example = "123", required = true)
    private Long ID;
    @ApiModelProperty(notes = "Class number", example = "123ABC", required = true)
    private String class_no;
    @ApiModelProperty(notes = "Student name", example = "James", required = true)
    private String name;
    @ApiModelProperty(notes = "Student surname", example = "Gosling", required = true)
    private String surname;
    @ApiModelProperty(notes = "Student age", example = "18", required = true)
    private Integer age;
}
