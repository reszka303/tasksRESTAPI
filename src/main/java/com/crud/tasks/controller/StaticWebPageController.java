package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("variable", "My Thymeleaf variable");
        model.put("one", 1);
        model.put("two", 2);
        model.put("three", 3);
        model.put("four", 4);
        model.put("five", 5);
        model.put("six", 6);
        model.put("seven", 7);
        model.put("eight", 8);
        model.put("nine", 9);
        model.put("zero", 0);
        model.put("plus", "+");
        model.put("minus", "-");
        model.put("multiply", "*");
        model.put("sum", "=");
        return "index";
    }
}
