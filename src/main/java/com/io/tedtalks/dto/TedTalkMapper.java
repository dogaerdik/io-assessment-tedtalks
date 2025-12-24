package com.io.tedtalks.dto;

import com.io.tedtalks.model.TedTalk;

public class TedTalkMapper {
    
    public static TedTalkDTO toDTO(TedTalk talk) {
        if (talk == null) {
            return null;
        }
        
        return new TedTalkDTO(
                talk.getId(),
                talk.getTitle(),
                talk.getAuthor(),
                talk.getDate(),
                talk.getViews(),
                talk.getLikes(),
                talk.getLink(),
                talk.calculateInfluenceScore()
        );
    }
}

