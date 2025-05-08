package com.example.basic.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.entity.Major;
import com.example.basic.repository.MajorRepository;


@Controller
@RequestMapping(value = "/major")
public class MajorController {
    @Autowired MajorRepository majorRepository;

    @GetMapping("/add")
    @ResponseBody
    public Major majorAdd(@ModelAttribute Major major){
        // ModelAttribute Major 로 데이터가 클래스로 들어오기 때문에 null이 가능한 id와 name 은 굳이 안써도 된다.
        major.setId(major.getId());
        major.setName(major.getName());
        major.setMaxPrsn(major.getMaxPrsn());
        // LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date();
        major.setEbtbDate(date);

        Major  result = majorRepository.save(major);
        return result;
    }
    
    @GetMapping("/list")
    @ResponseBody
    public List<Major> majorList(){
        List<Major> list = majorRepository.findAll();
        return list;
    }
    
}
