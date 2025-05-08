package com.example.basic.model;

import lombok.Getter;
import lombok.Setter;

// 스프링이 실행될때
// (Java 컴파일러가 컴파일 할때)
@Setter @Getter
public class Member {
  String name;

  public Member() {}
  
  public Member(String name) {
    this.name = name;
  }
}
