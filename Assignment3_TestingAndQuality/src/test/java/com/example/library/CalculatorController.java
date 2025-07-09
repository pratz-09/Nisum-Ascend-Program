package com.example.library;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
@WebMvcTest(CalculatorController.class)
@RestController
@RequestMapping("/calc")
public class CalculatorController {
    @Autowired
    private CalculatorService service;

    @GetMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return service.add(a, b);
    }
}
