package com.example.StudentList.mapper;


import com.example.StudentList.dto.request.StudentRequest;
import com.example.StudentList.dto.response.StudentResponse;
import com.example.StudentList.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  @Mapping(source ="phoneNumber", target = "studentInfo.phoneNumber")
  @Mapping(source ="address", target = "studentInfo.address")
  @Mapping(source ="email", target = "studentInfo.email")
  Student dtoToStudent(StudentRequest request);

  @Mapping(target ="studentInfo" ,source = "studentInfo" )
  @Mapping(target ="teachers" ,source = "teachers" )
  StudentResponse studentToDto(Student student);

}
