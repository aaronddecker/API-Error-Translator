package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for analyzing error messages
 * and returning explanations and possible fixes.
 */
@Service
public class ErrorAnalysisService {

    /**
     * Internal class representing a known error pattern.
     * Each pattern has:
     * - a keyword to detect the error
     * - an explanation
     * - a list of possible fixes
     */
    private static class ErrorPattern {
        String keyword;
        String explanation;
        List<String> fixes;

        public ErrorPattern(String keyword, String explanation, List<String> fixes) {
            this.keyword = keyword;
            this.explanation = explanation;
            this.fixes = fixes;
        }
    }

    /**
     * Main method that analyzes an error string.
     * @param error raw error message from user
     * @return AnalysisResult with explanations and fixes
     */
    public AnalysisResult analyze(String error) {

        // Normalize input (lowercase for easier matching)
        String normalizedError = error.toLowerCase();

        // Prepare result lists
        List<String> explanations = new ArrayList<>();
        List<String> possibleFixes = new ArrayList<>();

        // Define known error patterns
        List<ErrorPattern> patterns = List.of(

            new ErrorPattern(
                "map of undefined",
                "You are calling map() on an undefined variable.",
                List.of(
                    "Check if the variable exists",
                    "Ensure API response is correct",
                    "Use optional chaining (variable?.map(...))"
                )
            ),

            new ErrorPattern(
                "cannot read property",
                "You are trying to access a property of undefined or null.",
                List.of(
                    "Check if the object exists",
                    "Validate API response before accessing properties"
                )
            ),

            new ErrorPattern(
                "nullpointer",
                "A variable is null when accessed.",
                List.of(
                    "Initialize the variable",
                    "Add null checks before usage"
                )
            ),

            new ErrorPattern(
                "undefined is not a function",
                "You are calling something that is not a function.",
                List.of(
                    "Check imports",
                    "Ensure the function exists",
                    "Verify variable types"
                )
            )
        );

        // Match error against known patterns
        for (ErrorPattern pattern : patterns) {
            if (normalizedError.contains(pattern.keyword)) {
                explanations.add(pattern.explanation);
                possibleFixes.addAll(pattern.fixes);
            }
        }

        // Fallback if no pattern matched
        if (explanations.isEmpty()) {
            explanations.add("No known pattern matched. Try a more specific error message.");
            possibleFixes.add("Check documentation or search online.");
        }

        // Build result object
        AnalysisResult result = new AnalysisResult();
        result.setOriginalError(error);
        result.setExplanations(explanations);
        result.setPossibleFixes(possibleFixes);

        return result;
    }
}