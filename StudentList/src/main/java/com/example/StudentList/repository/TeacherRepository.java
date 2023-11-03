package com.example.StudentList.repository;


import com.example.StudentList.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    Optional<Teacher> findByNameAndSurName(@NotEmpty String name, @NotEmpty String surName);
}
