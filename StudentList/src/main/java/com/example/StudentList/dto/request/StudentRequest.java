package com.example.StudentList.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    @ApiModelProperty(notes = "Student name", example = "James", required = true)
    @NotBlank
    private String name;
    @ApiModelProperty(notes = "Student surname", example = "Gosling", required = true)
    @NotBlank
    private String surname;
    @ApiModelProperty(notes = "Student age", example = "18", required = true)
    @Min(17)
    private Integer age;
    @ApiModelProperty(notes = "Phone number", example = "123ABC", required = true)

    private String phoneNumber;

    @ApiModelProperty(notes = "Address", example = "Baku/Azerbaijan", required = true)
    private String address;
    @Email
    @ApiModelProperty(notes = "Email", example = "some.email@gmail.com", required = true)
    private String email;
//    private Long groupId;


    private String teacherName;
    private String teacherSurname;
    @Min(23)
    @Max(65)
    private int teacherAge;
    @NotBlank
    private String groupName;
    @NotBlank
    private String groupNumber;
}