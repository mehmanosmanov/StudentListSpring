package com.example.StudentList.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
@Setter
@Getter
//@NamedEntityGraph(name = "group_graph",
//        attributeNodes = @NamedAttributeNode(value = "students", subgraph = "subgraph.students"),
//        subgraphs = {
//                @NamedSubgraph(name = "subgraph.students", attributeNodes = {
//                        @NamedAttributeNode(value = "studentInfo"),
//                        @NamedAttributeNode(value = "teachers")})
//        })

public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String number;

    @OneToMany(mappedBy = "studentGroup")
    private Set<Student> students = new HashSet<>();

}
