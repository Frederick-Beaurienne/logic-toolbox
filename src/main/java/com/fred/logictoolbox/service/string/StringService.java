package com.fred.logictoolbox.service.string;

import org.springframework.stereotype.Service;

import java.text.Normalizer;
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

    /**
     * Counts the number of occurrences of a character in a string.
     *
     * @param input the string to analyze
     * @param character the character to search for
     * @return the number of occurrences found,
     * or 0 if the input is null or blank
     */
    public int countCharacterOccurrences(String input, char character) {

        if (input == null || input.isBlank()) {
            return 0;
        }

        int count = 0;

        for (char currentCharacter : input.toCharArray()) {

            if (Character.toLowerCase(currentCharacter)
                    == Character.toLowerCase(character)) {

                count++;
            }
        }

        return count;
    }

    /**
     * Converts a string to camelCase format.
     *
     * @param input the string to convert
     * @return the camelCase version of the string,
     * or an empty string if the input is null or blank
     */
    public String toCamelCase(String input) {

        if (input == null || input.isBlank()) {
            return "";
        }

        String[] words = input
                .trim()
                .toLowerCase()
                .split("[\\s_]+");

        StringBuilder result = new StringBuilder(words[0]);

        for (int index = 1; index < words.length; index++) {

            String word = words[index];

            result.append(
                    Character.toUpperCase(word.charAt(0))
            );

            result.append(word.substring(1));
        }

        return result.toString();
    }

    /**
     * Counts the number of vowels in a string.
     *
     * @param input the string to analyze
     * @return the number of vowels found in the string,
     * or 0 if the input is null or blank
     */
    public int countVowels(String input) {

        if (input == null || input.isBlank()) {
            return 0;
        }

        int count = 0;

        String normalizedInput = input.toLowerCase();

        for (char character : normalizedInput.toCharArray()) {

            if ("aeiouy".indexOf(character) != -1) {
                count++;
            }
        }

        return count;
    }

    /**
     * Alternates uppercase and lowercase characters in a string.
     *
     * @param input the string to transform
     * @return the transformed string with alternating cases,
     * or an empty string if the input is null or blank
     */
    public String alternateCase(String input) {

        if (input == null || input.isBlank()) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        boolean uppercase = true;

        for (char character : input.toCharArray()) {

            if (Character.isLetter(character)) {

                if (uppercase) {
                    result.append(Character.toUpperCase(character));
                } else {
                    result.append(Character.toLowerCase(character));
                }

                uppercase = !uppercase;

            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    /**
     * Reduces excessive consecutive character repetitions in a string.
     * <p>
     * Legitimate double characters are preserved, while repetitions
     * greater than two are reduced to a single character.
     *
     * @param input the string to clean
     * @return the cleaned string with normalized repetitions,
     * or an empty string if the input is null or blank
     */
    public String removeConsecutiveDuplicates(String input) {

        if (input == null || input.isBlank()) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        int index = 0;

        while (index < input.length()) {

            char currentCharacter = input.charAt(index);

            int repetitionCount = 1;

            while (
                    index + repetitionCount < input.length()
                            && input.charAt(index + repetitionCount) == currentCharacter
            ) {
                repetitionCount++;
            }

            if (repetitionCount > 2) {

                result.append(currentCharacter);

            } else {

                result.append(
                        String.valueOf(currentCharacter)
                                .repeat(repetitionCount)
                );
            }

            index += repetitionCount;
        }

        return result.toString();
    }

    /**
     * Extracts the initials from a full name.
     *
     * @param input the full name to analyze
     * @return the extracted initials in uppercase,
     * or an empty string if the input is null or blank
     */
    public String extractInitials(String input) {

        if (input == null || input.isBlank()) {
            return "";
        }

        String[] words = input.trim().split("\\s+");

        StringBuilder initials = new StringBuilder();

        for (String word : words) {

            initials.append(
                    Character.toUpperCase(word.charAt(0))
            );
        }

        return initials.toString();
    }

    /**
     * Masks a string while preserving the last visible characters.
     * <p>
     * Unlike the simplified example provided in the exercise,
     * this implementation preserves the original string length
     * by replacing hidden characters with '*' symbols.
     * <p>
     * This approach is closer to real-world security practices
     * commonly used for sensitive data such as bank card numbers
     * or account identifiers.
     *
     * @param input the string to mask
     * @param visibleCharactersCount the number of visible characters to keep
     * @return the masked string,
     * or an empty string if the input is null or blank
     */
    public String maskString(
            String input,
            int visibleCharactersCount
    ) {

        if (input == null || input.isBlank()) {
            return "";
        }

        if (visibleCharactersCount <= 0) {
            return "*".repeat(input.length());
        }

        if (visibleCharactersCount >= input.length()) {
            return input;
        }

        int maskedLength = input.length() - visibleCharactersCount;

        return "*".repeat(maskedLength)
                + input.substring(maskedLength);
    }

    /**
     * Checks whether a string is a palindrome.
     * <p>
     * The comparison ignores:
     * - uppercase and lowercase differences
     * - spaces
     * - punctuation and special characters
     * - accents and diacritical marks
     *
     * @param input the string to analyze
     * @return true if the string is a palindrome,
     * false otherwise
     */
    public boolean isPalindrome(String input) {

        if (input == null || input.isBlank()) {
            return false;
        }

        String normalizedInput = Normalizer.normalize(
                        input,
                        Normalizer.Form.NFD
                )
                .replaceAll("\\p{M}", "")
                .replaceAll("[^\\p{L}\\p{N}]", "")
                .toLowerCase();

        String reversedInput = new StringBuilder(normalizedInput)
                .reverse()
                .toString();

        return normalizedInput.equals(reversedInput);
    }

    /**
     * Finds the longest sequence of identical consecutive characters.
     *
     * @param input the string to analyze
     * @return the longest sequence found,
     * or an empty string if the input is null or blank
     */
    public String longestSequence(String input) {

        if (input == null || input.isBlank()) {
            return "";
        }

        String longestSequence = "";
        StringBuilder currentSequence = new StringBuilder();

        currentSequence.append(input.charAt(0));

        for (int index = 1; index < input.length(); index++) {

            char currentCharacter = input.charAt(index);
            char previousCharacter = input.charAt(index - 1);

            if (currentCharacter == previousCharacter) {

                currentSequence.append(currentCharacter);

            } else {

                if (currentSequence.length() > longestSequence.length()) {
                    longestSequence = currentSequence.toString();
                }

                currentSequence.setLength(0);
                currentSequence.append(currentCharacter);
            }
        }

        if (currentSequence.length() > longestSequence.length()) {
            longestSequence = currentSequence.toString();
        }

        return longestSequence;
    }

    /**
     * Truncates a string and appends suspension points when necessary.
     *
     * @param input the string to truncate
     * @param maxLength the maximum final length including suspension points
     * @return the formatted string,
     * or an empty string if the input is null or blank
     */
    public String truncate(String input, int maxLength) {

        if (input == null || input.isBlank()) {
            return "";
        }

        if (maxLength <= 3) {
            return "...";
        }

        if (input.length() <= maxLength) {
            return input;
        }

        int truncatedLength = maxLength - 3;

        return input.substring(0, truncatedLength).trim() + "...";
    }

    /**
     * Capitalizes the first letter of each word in a string.
     *
     * @param input the string to format
     * @return the formatted string with capitalized words,
     * or an empty string if the input is null or blank
     */
    public String capitalizeWords(String input) {

        if (input == null || input.isBlank()) {
            return "";
        }

        String[] words = input
                .trim()
                .toLowerCase()
                .split("\\s+");

        StringBuilder result = new StringBuilder();

        for (String word : words) {

            result.append(
                    Character.toUpperCase(word.charAt(0))
            );

            result.append(word.substring(1));

            result.append(" ");
        }

        return result.toString().trim();
    }
}
