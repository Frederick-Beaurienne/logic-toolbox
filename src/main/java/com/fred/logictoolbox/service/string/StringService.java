package com.fred.logictoolbox.service.string;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class StringService {

    public int getLengthWithoutSpaces(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }

        return input.replaceAll("\\s+", "").length();
    }

    public String formatGreeting(String input) {

        if (input == null || input.isBlank()) {
            return "Bonjour";
        }

        String formattedName = Arrays.stream(input.trim().split("-"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining("-"));

        return "Bonjour " + formattedName;
    }
}
