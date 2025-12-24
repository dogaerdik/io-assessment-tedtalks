package com.io.tedtalks.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class TedTalkTest {
    
    @Test
    void testCalculateInfluenceScore() {
        TedTalk talk = new TedTalk(
                "Test Title",
                "Test Author",
                LocalDate.of(2021, 12, 1),
                1000000L,
                10000L,
                "https://test.com"
        );
        
        Long expectedScore = 1000000L + (10000L * 100);
        assertEquals(expectedScore, talk.calculateInfluenceScore());
    }
    
}

