package com.example.demo.service;

import com.example.demo.entity.History;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryRepository historyRepository;

    @Override
    public void save(History history) {
        historyRepository.save(history);
    }
}
