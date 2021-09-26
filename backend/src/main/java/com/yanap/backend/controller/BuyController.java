package com.yanap.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;

@RestController
@CrossOrigin
public class BuyController {
    @AllArgsConstructor
    public class Response {
        @Getter
        private boolean result;
    }
    
    @PostMapping("/buy")
    public Response buy(@RequestParam("token") String token) {
        System.out.println(token);
        return new Response(true);
    }
}
