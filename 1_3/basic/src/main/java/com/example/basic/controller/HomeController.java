package com.example.basic.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class HomeController {
    @Autowired ObjectMapper objectMapper;

    @GetMapping("/home")
    public String home(Model model){
        Map <String,Object> map = new HashMap<>();
        map.put("id","abcd");
        map.put("pw", "1234");
        //
        String json= "";
        try{
            json = objectMapper.writeValueAsString(map);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        model.addAttribute("data", json);
        return "home";
    }
    
}
