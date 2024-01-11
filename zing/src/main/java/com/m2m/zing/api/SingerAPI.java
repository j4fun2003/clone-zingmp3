package com.m2m.zing.api;

import com.m2m.zing.dto.SingerRequest;
import com.m2m.zing.model.Singer;
import com.m2m.zing.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/singers")
public class SingerAPI {
    @Autowired
    SingerService singerService;

    @GetMapping
    public ResponseEntity<?> getAllSinger() {
            Map<String, Object> result = new HashMap<>();
            try {
                result.put("status","success");
                result.put("data", singerService.getAllSinger());
            }catch (Exception e){
                result.put("status","failed");
                result.put("detail",e);
            }
            return ResponseEntity.ok(result);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getSingerById(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status","success");
            result.put("data", singerService.getSingerById(id));
        }catch (Exception e){
            result.put("status","failed");
            result.put("detail",e);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> createSinger(@RequestBody SingerRequest singerRequest) {
        Map<String, Object> result = new HashMap<>();
        try {
            Singer singer = new Singer();
            singer.setImage(singerRequest.getSingerImage());
            singer.setSingerFullName(singerRequest.getSingerFullName());
            singer.setSingerDescription(singerRequest.getSingerDescription());
            singer.setSingerEmail(singerRequest.getSingerEmail());
            singer.setSingerBirthday(singerRequest.getSingerBirthDay());
            result.put("status","success");
            result.put("data", singerService.createSinger(singer));
        }catch (Exception e){
            result.put("status","failed");
            result.put("detail",e);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> updateSinger(@RequestBody Singer singer){
        Map<String, Object> result = new HashMap<>();
        try {
            Singer singerUpdate = singerService.updateSinger(singer);
            result.put("status","success");
            result.put("data",singerUpdate);
        }catch (Exception e){
            result.put("status","failed");
            result.put("detail",e);
            System.out.println(e);
        }
        return ResponseEntity.ok(result);
    }
}
