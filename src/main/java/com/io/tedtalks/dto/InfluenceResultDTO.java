package com.io.tedtalks.dto;

public class InfluenceResultDTO {
    
    private String author;
    private Long totalInfluenceScore;
    private Integer talkCount;
    
    public InfluenceResultDTO() {
    }
    
    public InfluenceResultDTO(String author, Long totalInfluenceScore, Integer talkCount) {
        this.author = author;
        this.totalInfluenceScore = totalInfluenceScore;
        this.talkCount = talkCount;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public Long getTotalInfluenceScore() {
        return totalInfluenceScore;
    }
    
    public void setTotalInfluenceScore(Long totalInfluenceScore) {
        this.totalInfluenceScore = totalInfluenceScore;
    }
    
    public Integer getTalkCount() {
        return talkCount;
    }
    
    public void setTalkCount(Integer talkCount) {
        this.talkCount = talkCount;
    }
}

