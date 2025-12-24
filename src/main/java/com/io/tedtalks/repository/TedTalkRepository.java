package com.io.tedtalks.repository;

import com.io.tedtalks.model.TedTalk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TedTalkRepository extends JpaRepository<TedTalk, Long> {
    
    Optional<TedTalk> findByLink(String link);
    
    List<TedTalk> findByAuthor(String author);
    
    @Query(value = "SELECT * FROM tedtalks WHERE EXTRACT(YEAR FROM date) = :year ORDER BY (views + likes * 100) DESC LIMIT 1", nativeQuery = true)
    Optional<TedTalk> findMostInfluentialByYear(int year);
    
    @Query("SELECT t.author, SUM(t.views + t.likes * 100) as totalScore, COUNT(t) as talkCount " +
           "FROM TedTalk t " +
           "GROUP BY t.author " +
           "ORDER BY totalScore DESC")
    List<Object[]> findInfluentialSpeakers();
}

