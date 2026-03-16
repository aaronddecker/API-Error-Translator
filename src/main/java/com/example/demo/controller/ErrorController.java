package com.example.demo.controller;

import com.example.demo.service.AnalysisResult;
import com.example.demo.service.ErrorAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for the API Error Translator.
 * Handles POST requests to analyze developer error messages.
 */
@RestController
@RequestMapping("/api") // Base URL for all endpoints in this controller
public class ErrorController {

    @Autowired
    private ErrorAnalysisService errorAnalysisService; // Inject the service

    /**
     * POST endpoint to analyze an error message.
     * @param error the raw error string sent by the client
     * @return AnalysisResult containing explanations and suggested fixes
     */
    @PostMapping("/analyze")
    public AnalysisResult analyzeError(@RequestBody String error){
        return errorAnalysisService.analyze(error);
    }

    /**
     * Optional GET endpoint to quickly test if API is running.
     */
    @GetMapping("/test")
    public String test() {
        return "API is running!";
    }
}