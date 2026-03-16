package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class that analyzes error messages and provides explanations and suggested fixes.
 * This is the core logic of the API Error Translator.
 */
@Service
public class ErrorAnalysisService {

    /**
     * Analyzes a given error string and returns explanations and possible fixes.
     * @param error the raw error message or stacktrace
     * @return AnalysisResult containing explanations and suggested fixes
     */
    public AnalysisResult analyze(String error) {

        // Initialize result object
        AnalysisResult result = new AnalysisResult();
        result.setOriginalError(error);

        // Lists to store explanations and fixes
        List<String> explanations = new ArrayList<>();
        List<String> possibleFixes = new ArrayList<>();

        // ----- Simple pattern matching for common developer errors -----

        // JavaScript: "map of undefined" error
        if (error.contains("map") && error.contains("undefined")) {
            explanations.add("You are calling map() on an undefined variable.");
            possibleFixes.add("Check if the variable exists and is an array.");
            possibleFixes.add("Validate API response before calling map().");
            possibleFixes.add("Use optional chaining (variable?.map(...)).");

        // Generic JS/TS: "cannot read property" error
        } else if (error.contains("cannot read property")) {
            explanations.add("You are trying to access a property of undefined or null.");
            possibleFixes.add("Ensure the object exists before accessing the property.");
            possibleFixes.add("Check API responses and variable assignments.");

        // Java: Null Pointer Exception
        } else if (error.contains("null pointer exception")) {
            explanations.add("A variable is null when you tried to use it.");
            possibleFixes.add("Initialize the variable properly.");
            possibleFixes.add("Add null checks before using it.");

        // Default case: unknown error
        } else {
            explanations.add("No known pattern matched. Try a more specific error message.");
            possibleFixes.add("Check documentation or search online.");
        }

        // Set results
        result.setExplanations(explanations);
        result.setPossibleFixes(possibleFixes);

        return result;
    }
}