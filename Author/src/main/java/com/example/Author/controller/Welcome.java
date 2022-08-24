package com.example.Author.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Welcome {

    @GetMapping
    @Operation(summary="Author application server home")
    public ResponseEntity<String> welcome() {

        return new ResponseEntity<>("Welcome to Author application server", HttpStatus.OK);
    }
}
