package com.example.demo.service;

import java.util.List;

/**
 * Represents the result of analyzing an error message.
 * Contains:
 * - original error string
 * - explanations for the error
 * - possible fixes
 */
public class AnalysisResult {

    private String originalError;
    private List<String> explanations;
    private List<String> possibleFixes;

    // ------------------- Getters & Setters -------------------

    public String getOriginalError() {
        return originalError;
    }

    public void setOriginalError(String originalError) {
        this.originalError = originalError;
    }

    public List<String> getExplanations() {
        return explanations;
    }

    public void setExplanations(List<String> explanations) {
        this.explanations = explanations;
    }

    public List<String> getPossibleFixes() {
        return possibleFixes;
    }

    public void setPossibleFixes(List<String> possibleFixes) {
        this.possibleFixes = possibleFixes;
    }
}