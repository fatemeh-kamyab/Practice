package com.example.controller;

import com.example.service.WelcomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/")
public class WelcomeController {

    private final WelcomeService service;

    public WelcomeController(WelcomeService service) {
        this.service = service;
    }

    @GetMapping("/helloworld")
    public ResponseEntity<String> helloworld() {
        return new ResponseEntity<>("Hello Stranger",HttpStatus.OK);
    }

    @PostMapping("/helloworld")
    public ResponseEntity<String> helloworldByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(service.helloworldByName(name),HttpStatus.OK);
    }

    @GetMapping("/author")
    public ResponseEntity<String> author() {
        return new ResponseEntity<>("Fatemeh Kamyab",HttpStatus.OK);
    }
}
