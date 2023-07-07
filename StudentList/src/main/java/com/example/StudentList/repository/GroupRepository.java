package com.example.StudentList.repository;

import com.example.StudentList.entity.StudentGroup;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<StudentGroup, Long> {

    @EntityGraph(type = EntityGraphType.FETCH, attributePaths = {"students", "students.studentInfo", "students.teachers"})
    List<StudentGroup> findAll();

    Optional<StudentGroup> findByNameAndNumber(String name, String number);


}
