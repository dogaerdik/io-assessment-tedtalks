package com.io.tedtalks.controller;

import com.io.tedtalks.dto.InfluenceResultDTO;
import com.io.tedtalks.dto.TedTalkDTO;
import com.io.tedtalks.service.ImportService;
import com.io.tedtalks.service.InfluenceAnalysisService;
import com.io.tedtalks.service.TedTalkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tedtalks")
@Tag(name = "TedTalks API")
@Validated
public class TedTalkController {
    
    private final TedTalkService tedTalkService;
    private final ImportService importService;
    private final InfluenceAnalysisService influenceAnalysisService;
    
    public TedTalkController(TedTalkService tedTalkService, 
                             ImportService importService,
                             InfluenceAnalysisService influenceAnalysisService) {
        this.tedTalkService = tedTalkService;
        this.importService = importService;
        this.influenceAnalysisService = influenceAnalysisService;
    }
    
    @GetMapping
    @Operation(summary = "Get all TedTalks")
    public ResponseEntity<List<TedTalkDTO>> getAllTedTalks() {
        List<TedTalkDTO> talks = tedTalkService.getAllTedTalks();
        return ResponseEntity.ok(talks);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get TedTalk by ID")
    public ResponseEntity<TedTalkDTO> getTedTalkById(@PathVariable Long id) {
        TedTalkDTO talk = tedTalkService.getTedTalkById(id);
        return ResponseEntity.ok(talk);
    }
    
    @PostMapping("/import")
    @Operation(summary = "Import TedTalks from CSV")
    public ResponseEntity<Map<String, Object>> importTedTalks() {
        int importedCount = importService.importFromCsv();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Import completed successfully");
        response.put("importedCount", importedCount);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/influential")
    @Operation(summary = "Get top influential speakers")
    public ResponseEntity<List<InfluenceResultDTO>> getTopInfluentialSpeakers() {
        List<InfluenceResultDTO> speakers = influenceAnalysisService.getTopInfluentialSpeakers();
        return ResponseEntity.ok(speakers);
    }
    
    @GetMapping("/influential/{year}")
    @Operation(summary = "Get most influential talk by year")
    public ResponseEntity<TedTalkDTO> getMostInfluentialTalkByYear(
            @PathVariable @Min(1900) @Max(2100) int year) {
        TedTalkDTO talk = influenceAnalysisService.getMostInfluentialTalkByYear(year);
        return ResponseEntity.ok(talk);
    }
}

