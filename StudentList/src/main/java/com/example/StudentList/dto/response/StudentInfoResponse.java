package com.example.StudentList.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentInfoResponse {
    private String phoneNumber;
    private String address;
}
