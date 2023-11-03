package com.example.StudentList.mapper;


import com.example.StudentList.model.dto.request.StudentRequest;
import com.example.StudentList.model.dto.response.TeacherResponse;
import com.example.StudentList.model.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(target = "name", source = "teacherName")
    @Mapping(target = "surName", source = "teacherSurname")
    @Mapping(target = "age", source = "teacherAge")
    Teacher dtoToEntity(StudentRequest request);

    TeacherResponse entityToDto(Teacher teacher);

}