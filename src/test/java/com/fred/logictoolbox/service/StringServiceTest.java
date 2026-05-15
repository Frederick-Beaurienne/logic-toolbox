package com.fred.logictoolbox.service;

import com.fred.logictoolbox.service.string.StringService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StringServiceTest {

    @Autowired
    private StringService stringService;

    @Nested
    @DisplayName("Tests de getLengthWithoutSpaces")
    class GetLengthWithoutSpacesTests {

        @Test
        @DisplayName("Doit retourner la longueur d'une chaîne sans espaces")
        void shouldReturnLengthWithoutSpaces() {
            int result = stringService.getLengthWithoutSpaces("Bonjour le monde !");

            assertEquals(15, result);
        }

        @Test
        @DisplayName("Doit retourner 0 pour une chaîne vide")
        void shouldReturnZeroForEmptyString() {
            int result = stringService.getLengthWithoutSpaces("");

            assertEquals(0, result);
        }

        @Test
        @DisplayName("Doit retourner 0 pour une chaîne null")
        void shouldReturnZeroForNullString() {
            int result = stringService.getLengthWithoutSpaces(null);

            assertEquals(0, result);
        }

        @Test
        @DisplayName("Doit ignorer les espaces multiples")
        void shouldIgnoreMultipleSpaces() {
            int result = stringService.getLengthWithoutSpaces(" a  b   c ");

            assertEquals(3, result);
        }
    }

    @Nested
    @DisplayName("Tests de formatGreeting")
    class FormatGreetingTests {

        @Test
        @DisplayName("Doit retourner une salutation avec prénom formaté")
        void shouldReturnFormattedGreeting() {

            String result = stringService.formatGreeting("jean-pierre");

            assertEquals("Bonjour Jean-Pierre", result);
        }

        @Test
        @DisplayName("Doit retourner Bonjour pour une chaîne vide")
        void shouldReturnDefaultGreetingForEmptyInput() {

            String result = stringService.formatGreeting("");

            assertEquals("Bonjour", result);
        }

        @Test
        @DisplayName("Doit retourner Bonjour pour une valeur null")
        void shouldReturnDefaultGreetingForNullInput() {

            String result = stringService.formatGreeting(null);

            assertEquals("Bonjour", result);
        }

        @Test
        @DisplayName("Doit formater correctement les majuscules et minuscules")
        void shouldFormatUpperAndLowerCasesCorrectly() {

            String result = stringService.formatGreeting("jEaN-pIErRe");

            assertEquals("Bonjour Jean-Pierre", result);
        }
    }
}