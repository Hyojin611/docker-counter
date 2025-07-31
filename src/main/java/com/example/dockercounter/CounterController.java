package com.example.dockercounter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CounterController {
    
    private int counter = 0;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("counter", formatCounter(counter));
        return "index";
    }
    
    @PostMapping("/increment")
    public String increment(Model model) {
        counter++;
        model.addAttribute("counter", formatCounter(counter));
        return "index";
    }
    
    @PostMapping("/decrement")
    public String decrement(Model model) {
        counter--;
        model.addAttribute("counter", formatCounter(counter));
        return "index";
    }
    
  

    
    private String formatCounter(int value) {
        if (value > 0) {
            return "+" + value;
        } else if (value < 0) {
            return String.valueOf(value); 
        } else {
            return "0";
        }
    }
} 