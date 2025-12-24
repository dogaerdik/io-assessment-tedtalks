package com.io.tedtalks.service;

import com.io.tedtalks.dto.TedTalkDTO;
import com.io.tedtalks.dto.TedTalkMapper;
import com.io.tedtalks.model.TedTalk;
import com.io.tedtalks.repository.TedTalkRepository;
import com.io.tedtalks.exception.TedTalkNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TedTalkService {
    
    private final TedTalkRepository repository;
    
    public TedTalkService(TedTalkRepository repository) {
        this.repository = repository;
    }
    
    @Transactional(readOnly = true)
    public List<TedTalkDTO> getAllTedTalks() {
        List<TedTalk> talks = repository.findAll();
        return talks.stream()
                .map(TedTalkMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public TedTalkDTO getTedTalkById(Long id) {
        TedTalk talk = repository.findById(id)
                .orElseThrow(() -> new TedTalkNotFoundException(id));
        return TedTalkMapper.toDTO(talk);
    }
}

