package com.miguel.springbackend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
    

    @GetMapping("/creating")
    public String creating() {
        return "creating";
    }

    @GetMapping("/see-editing")
    public String editing() {
        return "seedit";
    }
}
