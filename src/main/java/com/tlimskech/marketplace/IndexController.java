package com.tlimskech.marketplace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "redirect:/tlims";
    }

    @GetMapping("tlims/**")
    public String tlims() {
        return "index";
    }

}
