package com.m2m.zing.service.impl;

import com.m2m.zing.model.History;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.HistoryRepository;
import com.m2m.zing.service.HistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public History addToHistory(User user, Song song) {
        History history = new History();
        history.setUser(user);
        history.setSong(song);
        history.setListenDate(LocalDateTime.now());

        return historyRepository.save(history);
    }

    @Override
    public List<History> getHistoryByUser(User user) {
        return historyRepository.findAllByUserOrderByListenDateDesc(user);
    }

    @Override
    public void clearUserHistory(User user) {
        List<History> userHistory = historyRepository.findAllByUserOrderByListenDateDesc(user);
        historyRepository.deleteAll(userHistory);
    }

    // Additional methods or functionalities
}
