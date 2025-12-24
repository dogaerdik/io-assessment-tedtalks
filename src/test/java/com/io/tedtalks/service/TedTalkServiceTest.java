package com.io.tedtalks.service;

import com.io.tedtalks.dto.TedTalkDTO;
import com.io.tedtalks.exception.TedTalkNotFoundException;
import com.io.tedtalks.model.TedTalk;
import com.io.tedtalks.repository.TedTalkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TedTalkServiceTest {
    
    @Mock
    private TedTalkRepository repository;
    
    @InjectMocks
    private TedTalkService service;
    
    private TedTalk testTalk;
    
    @BeforeEach
    void setUp() {
        testTalk = new TedTalk(
                "Test Title",
                "Test Author",
                LocalDate.of(2021, 12, 1),
                1000000L,
                10000L,
                "https://test.com"
        );
        testTalk.setId(1L);
    }
    
    @Test
    void testGetAllTedTalks() {
        List<TedTalk> talks = Arrays.asList(testTalk);
        when(repository.findAll()).thenReturn(talks);
        
        List<TedTalkDTO> result = service.getAllTedTalks();
        
        assertEquals(1, result.size());
        assertEquals("Test Title", result.get(0).getTitle());
    }
    
    @Test
    void testGetTedTalkById() {
        when(repository.findById(1L)).thenReturn(Optional.of(testTalk));
        
        TedTalkDTO result = service.getTedTalkById(1L);
        
        assertEquals("Test Title", result.getTitle());
    }
}

