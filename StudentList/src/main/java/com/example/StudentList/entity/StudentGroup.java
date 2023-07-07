package com.example.StudentList.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
@Setter
@Getter
@NoArgsConstructor
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
    @NotEmpty
    private String name;
    @NotEmpty
    private String number;

    @OneToMany(mappedBy = "studentGroup")
    private Set<Student> students = new HashSet<>();

    public StudentGroup(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
