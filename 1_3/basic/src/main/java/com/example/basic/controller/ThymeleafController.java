package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.basic.entity.HolidayParking;
import com.example.basic.repository.HolidayParkingRepository;

@Controller
public class ThymeleafController {
    @Autowired HolidayParkingRepository holidayParkingRepository;
    @GetMapping("/holiday_parking")
    public String holiday_parking(
        @RequestParam (defaultValue = "1") int page,

        Model model

    )
    {
        int startPage = (page -1) / 10 * 10 +1;
        int endPage = startPage + 9;

        
        // Pageable pageable_get10Pageable = PageRequest.of(page-1,100);
        // Page<HolidayParking>p_10Page = holidayParkingRepository.findAll(pageable_get10Pageable);
        // System.out.println("p_10page : "+startPage + " "+p_10Page.getNumberOfElements());
        // if(p_10Page.getNumberOfElements()<100){
        //     endPage = p_10Page.getNumberOfElements()/10 + startPage;
        // }

        Pageable pageable = PageRequest.of(page-1,10);
        Page<HolidayParking> p = holidayParkingRepository.findAll(pageable);
        // System.out.println(p.getNumberOfElements());

        

        model.addAttribute("hp", p.getContent());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);

        
        return "holiday_parking";

    }
    
    @GetMapping("/user")
    public String user(Model model) {
        Map<String, Object> user = new HashMap<>();
        user.put("userId", "z");
        user.put("userName", "zoo");
        user.put("userAge", 25);
        model.addAttribute("user", user);
        return "user"; // / 앞의 녀석은 폴더가 된다.
    }

    @GetMapping("userList")
    public String userList(Model model) {
        List<Map<String, Object>> userList = new ArrayList<>();
        Map<String, Object> user = new HashMap<>();
        user.put("userId", "a");
        user.put("userName", "apple");
        user.put("userAge", 21);
        userList.add(user);
        user = new HashMap<>();
        user.put("userId", "b");
        user.put("userName", "banana");
        user.put("userAge", 22);
        userList.add(user);
        user = new HashMap<>();
        user.put("userId", "c");
        user.put("userName", "carrot");
        user.put("userAge", 23);
        userList.add(user);
        model.addAttribute("userList", userList);
        return "user_list";
    }

    @GetMapping("pagination")
    public String pagination(
            Model model, @RequestParam(defaultValue = "1") int page) {
        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        return "pagination";
    }

    @GetMapping("linkUrl")
    public String linkUrl(
            Model model, @RequestParam(defaultValue = "1") int page) {
        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        return "link_url";
    }

}
