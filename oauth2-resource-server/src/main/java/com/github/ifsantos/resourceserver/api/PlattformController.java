package com.github.ifsantos.resourceserver.api;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/api")
public class PlattformController {
    
    @GetMapping("/pattform")
    public String getPLattform() {
        return "{ JavaVersion: %s, CurrentTime: %s}".formatted(Runtime.version().toString(), new Date());
    }


    
}
