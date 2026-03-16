package fr.uga.miage.l3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class RedirectController {

    @RequestMapping("/")
    public String redirectToIndex() {
        return "forward:/index.html";
    }
}
