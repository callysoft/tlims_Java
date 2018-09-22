package com.tlimskech.marketplace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {

    @GetMapping("tlims-kech")
    public String index() {
        System.out.println("Entered here *");
        return "index";
    }
}
