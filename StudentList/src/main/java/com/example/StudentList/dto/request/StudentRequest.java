package com.example.StudentList.dto.request;

import com.example.StudentList.entity.Teacher;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    //    @ApiModelProperty(notes = "ID", example = "123", required = true)
//    private Long ID;
    @ApiModelProperty(notes = "Student name", example = "James", required = true)
    private String name;
    @ApiModelProperty(notes = "Student surname", example = "Gosling", required = true)
    private String surname;
    @ApiModelProperty(notes = "Student age", example = "18", required = true)
    private Integer age;
    @ApiModelProperty(notes = "Phone number", example = "123ABC", required = true)
    private String phoneNumber;
    @ApiModelProperty(notes = "Address", example = "Baku/Azerbaijan", required = true)
    private String address;
//    private Long groupId;

    private String teacherName;
    private String teacherSurname;
    private int teacherAge;

    private String groupName;
    private String groupNumber;
}