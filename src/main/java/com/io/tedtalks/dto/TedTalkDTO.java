package com.io.tedtalks.dto;

import java.time.LocalDate;

public class TedTalkDTO {
    
    private Long id;
    private String title;
    private String author;
    private LocalDate date;
    private Long views;
    private Long likes;
    private String link;
    private Long influenceScore;
    
    public TedTalkDTO() {
    }
    
    public TedTalkDTO(Long id, String title, String author, LocalDate date, 
                     Long views, Long likes, String link, Long influenceScore) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.views = views;
        this.likes = likes;
        this.link = link;
        this.influenceScore = influenceScore;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public Long getViews() {
        return views;
    }
    
    public void setViews(Long views) {
        this.views = views;
    }
    
    public Long getLikes() {
        return likes;
    }
    
    public void setLikes(Long likes) {
        this.likes = likes;
    }
    
    public String getLink() {
        return link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    
    public Long getInfluenceScore() {
        return influenceScore;
    }
    
    public void setInfluenceScore(Long influenceScore) {
        this.influenceScore = influenceScore;
    }
}

