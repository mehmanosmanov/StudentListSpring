package com.example.StudentList.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
public class AccountBalance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 50)
  private String name;
  @Column(nullable = false, length = 50)
  private String surName;
  @Size
  @Column(nullable = false)
  private Long accountNum;
  private Double balance;

}
