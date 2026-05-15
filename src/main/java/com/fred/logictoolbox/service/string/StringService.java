package com.fred.logictoolbox.service.string;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Service containing string manipulation and transformation utilities.
 * <p>
 * This service provides implementations for various string-based
 * logic challenges such as formatting, validation, transformation
 * and text analysis.
 */
@Service
public class StringService {

    /**
     * Returns the length of a string after removing all whitespace characters.
     *
     * @param input the string to process
     * @return the length of the string without whitespace,
     * or 0 if the input is null or blank
     */
    public int getLengthWithoutSpaces(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }

        return input.replaceAll("\\s+", "").length();
    }

    /**
     * Formats a greeting message using the provided name.
     * Each part of the name is capitalized automatically.
     *
     * @param input the name to format
     * @return a formatted greeting message,
     * or "Bonjour" if the input is null or blank
     */
    public String formatGreeting(String input) {

        if (input == null || input.isBlank()) {
            return "Bonjour";
        }

        String formattedName = Arrays.stream(input.trim().split("-"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining("-"));

        return "Bonjour " + formattedName;
    }

    /**
     * Checks whether a string ends with an exclamation mark.
     *
     * @param input the string to check
     * @return true if the string ends with "!",
     * false if the input is null, blank, or does not end with an exclamation mark
     */
    public boolean endsWithExclamationMark(String input) {

        if (input == null || input.isBlank()) {
            return false;
        }

        return input.trim().endsWith("!");
    }

    /**
     * Reverses the order of words in a string.
     *
     * @param input the input string to reverse
     * @return a string containing the words in reverse order,
     * or an empty string if the input is null or blank
     */
    public String reverseWords(String input) {

        if (input == null || input.isBlank()) {
            return "";
        }

        String[] words = input.trim().split("\\s+");

        Collections.reverse(Arrays.asList(words));

        return String.join(" ", words);
    }
}
