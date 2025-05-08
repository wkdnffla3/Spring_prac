package com.example.basic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.dao.DemoDao;
import com.example.basic.entity.ServiceCenter;
import com.example.basic.entity.TableExam1;
import com.example.basic.repository.ServiceCenterRepository;
import com.example.basic.repository.TableExam1Repository;


@Controller
public class HtmlController {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired TableExam1Repository tableExam1Repository;
    // @Autowired DemoDAO demoDAO;

    

    @GetMapping("html/table1/add")
    @ResponseBody
    public TableExam1 table1add(
        // @RequestParam Integer id,
        // @RequestParam String title
        @ModelAttribute TableExam1 tableExam1
        ){
            
            TableExam1 result = tableExam1Repository.save(tableExam1);
            
        return result;
    }
    @GetMapping("html/table1")
    @ResponseBody
    public List<TableExam1> table1() {
        
        List<TableExam1> list = tableExam1Repository.findAll();
        return list;
    }
    
    @GetMapping("html/string")
    public String htmlString(){
        return "html/string";
    }

    @GetMapping("html/void")
    public void htmlVoid(){
        
    }
    @GetMapping("html/map")
    
    public Map<String, Object> htmlMap(){
        Map<String, Object> map = new HashMap<>();
        return map;
    }
    
    
    
}
