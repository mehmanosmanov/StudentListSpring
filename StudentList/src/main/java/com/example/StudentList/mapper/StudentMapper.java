package com.example.StudentList.mapper;


import com.example.StudentList.model.dto.request.StudentRequest;
import com.example.StudentList.model.dto.response.StudentResponse;
import com.example.StudentList.model.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  @Mapping(target = "studentInfo.phoneNumber",source ="phoneNumber" )
  @Mapping(target = "studentInfo.address",source ="address")
  @Mapping(target = "studentInfo.email",source ="email")
  Student dtoToStudent(StudentRequest request);

  @Mapping(target ="studentInfo" ,source = "studentInfo" )
  @Mapping(target ="teachers" ,source = "teachers" )
  StudentResponse studentToDto(Student student);

}
