package com.m2m.zing.service;

import com.m2m.zing.model.History;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryService {
    History addToHistory(User user, Song song) throws Exception;
    List<History> getHistoryByUser(User user) throws Exception;

    List<History> getHistoryByUserId(Long userId) throws Exception;
    void clearUserHistory(User user) throws Exception;
}

