package com.io.tedtalks.service;

import com.io.tedtalks.dto.InfluenceResultDTO;
import com.io.tedtalks.dto.TedTalkDTO;
import com.io.tedtalks.dto.TedTalkMapper;
import com.io.tedtalks.model.TedTalk;
import com.io.tedtalks.repository.TedTalkRepository;
import com.io.tedtalks.exception.TedTalkNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfluenceAnalysisService {
    
    private final TedTalkRepository repository;
    private final int topSpeakersLimit;
    
    public InfluenceAnalysisService(TedTalkRepository repository,
                                    @Value("${app.influence.top.speakers.limit}") int topSpeakersLimit) {
        this.repository = repository;
        this.topSpeakersLimit = topSpeakersLimit;
    }
    
    @Transactional(readOnly = true)
    public List<InfluenceResultDTO> getTopInfluentialSpeakers() {
        List<Object[]> results = repository.findInfluentialSpeakers();
        
        return results.stream()
                .limit(topSpeakersLimit)
                .map(result -> {
                    String author = (String) result[0];
                    Long totalScore = ((Number) result[1]).longValue();
                    Integer talkCount = ((Number) result[2]).intValue();
                    
                    return new InfluenceResultDTO(author, totalScore, talkCount);
                })
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public TedTalkDTO getMostInfluentialTalkByYear(int year) {
        TedTalk talk = repository.findMostInfluentialByYear(year)
                .orElseThrow(() -> new TedTalkNotFoundException(
                    "No TedTalk found for year: " + year));
        
        return TedTalkMapper.toDTO(talk);
    }
}

