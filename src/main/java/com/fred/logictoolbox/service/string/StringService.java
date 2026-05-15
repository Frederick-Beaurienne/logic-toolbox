package com.fred.logictoolbox.service.string;

import org.springframework.stereotype.Service;

@Service
public class StringService {

    public int getLengthWithoutSpaces(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }

        return input.replaceAll("\\s+", "").length();
    }
}
