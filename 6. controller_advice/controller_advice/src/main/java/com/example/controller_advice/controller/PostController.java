package com.example.controller_advice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

  @PostMapping("/create")
  public String createPost(
    @RequestParam(required = false) String title
  ) {
    //System.out.println(4/0);
    // 코드 작성
    if(title == null)
    throw new NullPointerException("제목 없음");
    if(title.length() <4){
      throw new IllegalArgumentException("제목 짧음");
    }


    return "게시글 등록 완료";
  }
}
