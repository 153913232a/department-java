package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("student")
public class StudentController {

    @RequestMapping("/test")
    public String login() {

        return "";
    }
}
