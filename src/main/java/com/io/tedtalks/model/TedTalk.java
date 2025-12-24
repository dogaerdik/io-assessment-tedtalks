package com.io.tedtalks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tedtalks", indexes = {
    @Index(name = "idx_author", columnList = "author"),
    @Index(name = "idx_date", columnList = "date")
})
@EntityListeners(AuditingEntityListener.class)
public class TedTalk {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Title is required")
    @Column(nullable = false, length = 500)
    private String title;
    
    @NotBlank(message = "Author is required")
    @Column(nullable = false, length = 200)
    private String author;
    
    @NotNull(message = "Date is required")
    @Column(nullable = false)
    private LocalDate date;
    
    @NotNull(message = "Views is required")
    @Min(value = 0, message = "Views must be non-negative")
    @Column(nullable = false)
    private Long views;
    
    @NotNull(message = "Likes is required")
    @Min(value = 0, message = "Likes must be non-negative")
    @Column(nullable = false)
    private Long likes;
    
    @NotBlank(message = "Link is required")
    @Column(nullable = false, length = 500, unique = true)
    private String link;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    public TedTalk() {
    }
    
    public TedTalk(String title, String author, LocalDate date, Long views, Long likes, String link) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.views = views;
        this.likes = likes;
        this.link = link;
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
    
    public Long calculateInfluenceScore() {
        return views + (likes * 100);
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

