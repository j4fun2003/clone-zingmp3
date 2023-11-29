package com.m2m.zing.service;

import com.m2m.zing.model.History;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryService {
    History addToHistory(User user, Song song);
    List<History> getHistoryByUser(User user);
    void clearUserHistory(User user);
    // Other methods as needed
}

