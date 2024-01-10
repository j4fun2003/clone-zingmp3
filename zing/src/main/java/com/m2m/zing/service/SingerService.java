package com.m2m.zing.service;

import com.m2m.zing.model.Singer;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SingerService {
    List<Singer> getAllSinger() throws Exception;
    Singer getSingerById(Integer singerId) throws Exception;
    Singer createSinger(Singer singer) throws Exception;
    Singer updateSinger(Singer singer) throws Exception;
    void deleteSinger(Integer singerId) throws Exception;
}
