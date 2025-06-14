package com.nisum.SpringDemo;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")

public class StudentController {
    @RequestMapping("/hello")
    public String Hello() {
        return "Hello student";
    }
    @RequestMapping("/welcome")
    public String Welcome() {
        return "Welcome to student Portal";
    }
    @RequestMapping("/post")
    public String Post(@RequestBody String requestBody) {
        return "This is a Post method endpoint:";
    }
}