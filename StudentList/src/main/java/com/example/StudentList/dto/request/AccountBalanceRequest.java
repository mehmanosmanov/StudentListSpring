package com.example.StudentList.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Setter
@Getter
public class AccountBalanceRequest {
  private String name;
  private String surName;
  private Long accountNum;
}
