package com.m2m.zing.service.impl;

import com.m2m.zing.model.Singer;
import com.m2m.zing.repository.SingerRepository;
import com.m2m.zing.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    SingerRepository singerRepository;

    @Override
    public List<Singer> getAllSinger() throws Exception {
        return singerRepository.findAll();
    }

    @Override
    public Singer getSingerById(Integer singerId) throws Exception {
        return singerRepository.findById(singerId).orElse(null);
    }

    @Override
    public Singer createSinger(Singer singer) throws Exception {
        return singerRepository.save(singer);
    }

    @Override
    public Singer updateSinger(Singer singer) throws Exception {
        return singerRepository.save(singer);
    }

    @Override
    public void deleteSinger(Integer singerId) throws Exception {
        singerRepository.deleteById(singerId);
    }
}
