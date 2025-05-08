package com.example.basic.controller;
// package com.example.basic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.dao.DemoDao;
import com.example.basic.model.Demo;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class JdbcController {
    @Autowired DemoDao demoDao;

    @GetMapping("/jdbc/remove")
    public String jdbcRemove(@RequestParam int seq){
        demoDao.delete(seq);
        return "성공";
    }
    
    @GetMapping("/jdbc/add")
    public String jdbcAdd(@ModelAttribute Demo demo){
        try{
            demoDao.insert(demo);
            
        }catch(DuplicateKeyException e){
            return "중복 키 데이터";
        }catch(BadSqlGrammarException e){
            return "데이터 너무 김";
        }
        return "성공";
    }
    
    @GetMapping("/jdbc/demo1")
    public List<Map<String, Object>> demo1() {
        List<Map<String, Object>> list = demoDao.select1();
        return list;
    }

    @GetMapping("/jdbc/demo2")
    public List<Demo> demo2() {
        List<Demo> list = demoDao.select2();
        return list;
    }
}