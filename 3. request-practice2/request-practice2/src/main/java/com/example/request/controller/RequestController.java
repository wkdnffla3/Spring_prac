package com.example.request.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.request.model.User;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class RequestController {
  User user = new User();
  {
    user.setAge(0);
    user.setName("");
  }
  
  @CrossOrigin(origins = "http://localhost:5500")
  @GetMapping("/query")
  public String getQuery() {
    return "GET Query Received: " + user.getName() + ", " + user.getAge();
  }

  @GetMapping("/main")
    public String main(){
      return "main";
    }
  
  

  @PostMapping("/form")
  public String postForm() {
    return "POST FormData Received: " + user.getName() + ", " + user.getAge();
  }

  @PostMapping("/json")
  public String postJson() {
    return "POST JSON Received: " + user.getName() + ", " + user.getAge();
  }

  @CrossOrigin(origins = "http://localhost:5500") // 포트 다른 클라이언트에서만 허용
  @PostMapping("/cross-json")
  //get 이 지원 되지 않는다.
  // There was an unexpected error (type=Method Not Allowed, status=405).
  // Method 'GET' is not supported.
  // spring 은 controller 를 거쳐서만 접근이 가능하다. end point에 존재하지 않는 주소는 접근이 불가능 하다.
  // 모든 url이 컨트롤러를 거쳐야만 한다면 /css/style.css /js/js.js /images/a.jpg png
  // 내가 직접 써줘야 접근이 가능하지만 이건 귀찮다.
  // spring 포함 static 리소스 들을 특별 취급 해준다.
  public String postCrossJson() {
    return "CORS JSON Received: " + user.getName() + ", " + user.getAge();
  }
}
