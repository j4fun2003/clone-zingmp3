package com.m2m.zing.api;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/session")
public class SessionAPI {
    @Autowired
    HttpSession httpSession;


    @GetMapping("{sessionName}")
    public ResponseEntity<?> getSessionByAttributesName(@PathVariable String sessionName){
        Map<String, Object> result = new HashMap<>();
        Object object =   httpSession.getAttribute(sessionName);
        if(object != null){
            result.put("status","success");
            result.put("data",object);
        }else{
            result.put("status","failed");
            result.put("detail","not found with this session");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("{sessionName}")
    public ResponseEntity<?> setSessionAttributes(@PathVariable String sessionName, @RequestBody Object object){
        Map<String, Object> result = new HashMap<>();
        httpSession.setAttribute(sessionName,object);
        return ResponseEntity.ok(result);
    }


}