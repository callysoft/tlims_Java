package com.tlimskech.marketplace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequestMapping("")
public class IndexController {

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:4200")
    public String index() {
        return "redirect:/tlims";
    }

    @GetMapping("tlims/**")
     @CrossOrigin(origins = "http://localhost:4200")
    public String tlims() {
        return "index";
    }

}
