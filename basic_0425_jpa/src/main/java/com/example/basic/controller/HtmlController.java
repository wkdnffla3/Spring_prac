package com.example.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.dao.DemoDAO;
import com.example.basic.entity.TableExam1;
import com.example.basic.repository.TableExam1Repository;

@Controller
public class HtmlController {
  @Autowired DemoDAO demoDAO;
  @Autowired TableExam1Repository tableExam1Repository;

  @GetMapping("html/table1/add")
  @ResponseBody
  public TableExam1 table1Add(
    @ModelAttribute TableExam1 tableExam1
  ) {
    TableExam1 result = 
        tableExam1Repository.save(tableExam1);
    return result;
  }
  @GetMapping("html/table1")
  @ResponseBody
  public List<TableExam1> table1() {
    List<TableExam1> list = tableExam1Repository.findAll();
    return list;
  }

  @GetMapping("html/string")
  public String htmlString(Model model) {
    String name = "홍길동";
    return "html/string";
  }
}
