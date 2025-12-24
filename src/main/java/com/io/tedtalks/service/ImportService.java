package com.io.tedtalks.service;

import com.io.tedtalks.model.TedTalk;
import com.io.tedtalks.repository.TedTalkRepository;
import com.io.tedtalks.exception.ImportException;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.List;

@Service
public class ImportService {
    
    private final TedTalkRepository repository;
    private final String csvFilePath;
    
    public ImportService(TedTalkRepository repository,
                         @Value("${app.csv.file.path}") String csvFilePath) {
        this.repository = repository;
        this.csvFilePath = csvFilePath;
    }
    
    @Transactional
    public int importFromCsv() {
        int importedCount = 0;
        
        try {
            ClassPathResource resource = new ClassPathResource(csvFilePath);
            Reader reader = new InputStreamReader(resource.getInputStream());
            CSVReader csvReader = new CSVReader(reader);
            
            List<String[]> records = csvReader.readAll();
            
            if (records.isEmpty()) {
                return 0;
            }
            
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);
                
                try {
                    TedTalk talk = parseRecord(record);
                    
                    if (repository.findByLink(talk.getLink()).isPresent()) {
                        continue;
                    }
                    
                    repository.save(talk);
                    importedCount++;
                    
                } catch (Exception e) {
                    // Skip invalid records
                }
            }
            
            csvReader.close();
            
            return importedCount;
            
        } catch (Exception e) {
            throw new ImportException("Failed to import CSV file", e);
        }
    }
    
    private TedTalk parseRecord(String[] record) {
        if (record.length < 6) {
            throw new IllegalArgumentException("Invalid record: insufficient columns");
        }
        
        String title = record[0].trim();
        String author = record[1].trim();
        String dateString = record[2].trim();
        String viewsString = record[3].trim();
        String likesString = record[4].trim();
        String link = record[5].trim();
        
        if (title.isEmpty() || author.isEmpty() || dateString.isEmpty() || 
            viewsString.isEmpty() || likesString.isEmpty() || link.isEmpty()) {
            throw new IllegalArgumentException("Invalid record: empty required field");
        }
        
        LocalDate date = DateParser.parseDate(dateString);
        Long views = Long.parseLong(viewsString.replace(",", ""));
        Long likes = Long.parseLong(likesString.replace(",", ""));
        
        return new TedTalk(title, author, date, views, likes, link);
    }
}

