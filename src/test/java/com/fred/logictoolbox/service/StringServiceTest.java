package com.fred.logictoolbox.service;

import com.fred.logictoolbox.service.string.StringService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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

    @Nested
    @DisplayName("Tests de endsWithExclamationMark")
    class EndsWithExclamationMarkTests {

        @Test
        @DisplayName("Doit retourner true si la chaîne se termine par un point d'exclamation")
        void shouldReturnTrueWhenStringEndsWithExclamationMark() {

            boolean result = stringService.endsWithExclamationMark("Je suis très satisfait !");

            assertTrue(result);
        }

        @Test
        @DisplayName("Doit retourner false si la chaîne ne se termine pas par un point d'exclamation")
        void shouldReturnFalseWhenStringDoesNotEndWithExclamationMark() {

            boolean result = stringService.endsWithExclamationMark("Bonjour");

            assertFalse(result);
        }

        @Test
        @DisplayName("Doit retourner false pour une chaîne vide")
        void shouldReturnFalseForEmptyString() {

            boolean result = stringService.endsWithExclamationMark("");

            assertFalse(result);
        }

        @Test
        @DisplayName("Doit retourner false pour une valeur null")
        void shouldReturnFalseForNullInput() {

            boolean result = stringService.endsWithExclamationMark(null);

            assertFalse(result);
        }
    }

    @Nested
    @DisplayName("Tests de reverseWords")
    class ReverseWordsTests {

        @Test
        @DisplayName("Doit inverser l'ordre des mots")
        void shouldReverseWordsOrder() {

            String result = stringService.reverseWords("Je mange une pomme");

            assertEquals("pomme une mange Je", result);
        }

        @Test
        @DisplayName("Doit gérer les espaces multiples")
        void shouldHandleMultipleSpaces() {

            String result = stringService.reverseWords("Je   mange    une pomme");

            assertEquals("pomme une mange Je", result);
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une entrée vide")
        void shouldReturnEmptyStringForEmptyInput() {

            String result = stringService.reverseWords("");

            assertEquals("", result);
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur null")
        void shouldReturnEmptyStringForNullInput() {

            String result = stringService.reverseWords(null);

            assertEquals("", result);
        }
    }

    @Nested
    @DisplayName("Tests de countCharacterOccurrences")
    class CountCharacterOccurrencesTests {

        @Test
        @DisplayName("Doit compter correctement les occurrences d'un caractère")
        void shouldCountCharacterOccurrencesCorrectly() {

            int result = stringService.countCharacterOccurrences(
                    "Bonjour",
                    'o'
            );

            assertEquals(2, result);
        }

        @Test
        @DisplayName("Doit ignorer la casse")
        void shouldIgnoreCase() {

            int result = stringService.countCharacterOccurrences(
                    "BonjOur",
                    'o'
            );

            assertEquals(2, result);
        }

        @Test
        @DisplayName("Doit retourner 0 si le caractère est absent")
        void shouldReturnZeroWhenCharacterIsAbsent() {

            int result = stringService.countCharacterOccurrences(
                    "Bonjour",
                    'z'
            );

            assertEquals(0, result);
        }

        @Test
        @DisplayName("Doit retourner 0 pour une chaîne vide")
        void shouldReturnZeroForEmptyString() {

            int result = stringService.countCharacterOccurrences(
                    "",
                    'a'
            );

            assertEquals(0, result);
        }

        @Test
        @DisplayName("Doit retourner 0 pour une valeur null")
        void shouldReturnZeroForNullInput() {

            int result = stringService.countCharacterOccurrences(
                    null,
                    'a'
            );

            assertEquals(0, result);
        }
    }

    @Nested
    @DisplayName("Tests de toCamelCase")
    class ToCamelCaseTests {

        @Test
        @DisplayName("Doit convertir une chaîne en camelCase")
        void shouldConvertStringToCamelCase() {

            String result = stringService.toCamelCase(
                    "bonjour le monde"
            );

            assertEquals("bonjourLeMonde", result);
        }

        @Test
        @DisplayName("Doit supprimer les espaces multiples")
        void shouldHandleMultipleSpaces() {

            String result = stringService.toCamelCase(
                    "bonjour    le     monde"
            );

            assertEquals("bonjourLeMonde", result);
        }

        @Test
        @DisplayName("Doit convertir les majuscules correctement")
        void shouldHandleUppercaseLetters() {

            String result = stringService.toCamelCase(
                    "BONJOUR LE MONDE"
            );

            assertEquals("bonjourLeMonde", result);
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur vide")
        void shouldReturnEmptyStringForEmptyInput() {

            String result = stringService.toCamelCase("");

            assertEquals("", result);
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur null")
        void shouldReturnEmptyStringForNullInput() {

            String result = stringService.toCamelCase(null);

            assertEquals("", result);
        }
    }

    @Nested
    @DisplayName("Tests de countVowels")
    class CountVowelsTests {

        @Test
        @DisplayName("Doit compter correctement les voyelles")
        void shouldCountVowelsCorrectly() {

            int result = stringService.countVowels("Bonjour");

            assertEquals(3, result);
        }

        @Test
        @DisplayName("Doit retourner 0 si aucune voyelle n'est présente")
        void shouldReturnZeroWhenNoVowelsArePresent() {

            int result = stringService.countVowels("bcdfg");

            assertEquals(0, result);
        }

        @Test
        @DisplayName("Doit retourner 0 pour une chaîne vide")
        void shouldReturnZeroForEmptyString() {

            int result = stringService.countVowels("");

            assertEquals(0, result);
        }

        @Test
        @DisplayName("Doit retourner 0 pour une valeur null")
        void shouldReturnZeroForNullInput() {

            int result = stringService.countVowels(null);

            assertEquals(0, result);
        }
    }

    @Nested
    @DisplayName("Tests de alternateCase")
    class AlternateCaseTests {

        @Test
        @DisplayName("Doit alterner majuscules et minuscules")
        void shouldAlternateUppercaseAndLowercase() {

            String result = stringService.alternateCase(
                    "bonjour"
            );

            assertEquals("BoNjOuR", result);
        }

        @Test
        @DisplayName("Doit conserver les espaces")
        void shouldKeepSpaces() {

            String result = stringService.alternateCase(
                    "bonjour le monde"
            );

            assertEquals("BoNjOuR lE mOnDe", result);
        }

        @Test
        @DisplayName("Doit conserver les caractères spéciaux")
        void shouldKeepSpecialCharacters() {

            String result = stringService.alternateCase(
                    "bonjour !"
            );

            assertEquals("BoNjOuR !", result);
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur vide")
        void shouldReturnEmptyStringForEmptyInput() {

            String result = stringService.alternateCase("");

            assertEquals("", result);
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur null")
        void shouldReturnEmptyStringForNullInput() {

            String result = stringService.alternateCase(null);

            assertEquals("", result);
        }
    }

    @Nested
    @DisplayName("Tests de removeConsecutiveDuplicates")
    class RemoveConsecutiveDuplicatesTests {

        @Test
        @DisplayName("Doit réduire les répétitions excessives à un seul caractère")
        void shouldReduceExcessiveRepetitionsToSingleCharacter() {

            String result = stringService.removeConsecutiveDuplicates(
                    "Bonjouuuur !!! J'ai besoiiiin d'aide...."
            );

            assertEquals(
                    "Bonjour ! J'ai besoin d'aide.",
                    result
            );
        }

        @Test
        @DisplayName("Doit conserver les doubles lettres légitimes")
        void shouldKeepLegitimateDoubleLetters() {

            String result = stringService.removeConsecutiveDuplicates(
                    "Belle adresse"
            );

            assertEquals(
                    "Belle adresse",
                    result
            );
        }

        @Test
        @DisplayName("Doit réduire les longues répétitions internes")
        void shouldReduceLongInternalRepetitions() {

            String result = stringService.removeConsecutiveDuplicates(
                    "Belllllle"
            );

            assertEquals(
                    "Bele",
                    result
            );
        }

        @Test
        @DisplayName("Doit conserver les caractères uniques")
        void shouldKeepUniqueCharacters() {

            String result = stringService.removeConsecutiveDuplicates(
                    "Bonjour"
            );

            assertEquals(
                    "Bonjour",
                    result
            );
        }

        @Test
        @DisplayName("Doit gérer les chiffres")
        void shouldHandleNumbers() {

            String result = stringService.removeConsecutiveDuplicates(
                    "1111222333"
            );

            assertEquals(
                    "123",
                    result
            );
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur vide")
        void shouldReturnEmptyStringForEmptyInput() {

            String result = stringService.removeConsecutiveDuplicates("");

            assertEquals(
                    "",
                    result
            );
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur null")
        void shouldReturnEmptyStringForNullInput() {

            String result = stringService.removeConsecutiveDuplicates(null);

            assertEquals(
                    "",
                    result
            );
        }
    }

    @Nested
    @DisplayName("Tests de extractInitials")
    class ExtractInitialsTests {

        @Test
        @DisplayName("Doit extraire les initiales d'un nom complet")
        void shouldExtractInitials() {

            String result = stringService.extractInitials(
                    "Jean Pierre Dupont"
            );

            assertEquals("JPD", result);
        }

        @Test
        @DisplayName("Doit convertir les initiales en majuscules")
        void shouldConvertInitialsToUppercase() {

            String result = stringService.extractInitials(
                    "marie curie"
            );

            assertEquals("MC", result);
        }

        @Test
        @DisplayName("Doit gérer les espaces multiples")
        void shouldHandleMultipleSpaces() {

            String result = stringService.extractInitials(
                    "Jean     Pierre    Dupont"
            );

            assertEquals("JPD", result);
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur vide")
        void shouldReturnEmptyStringForEmptyInput() {

            String result = stringService.extractInitials("");

            assertEquals("", result);
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur null")
        void shouldReturnEmptyStringForNullInput() {

            String result = stringService.extractInitials(null);

            assertEquals("", result);
        }
    }

    @Nested
    @DisplayName("Tests de maskString")
    class MaskStringTests {

        @Test
        @DisplayName("Doit masquer tous les caractères sauf les derniers")
        void shouldMaskAllCharactersExceptLastOnes() {

            String result = stringService.maskString(
                    "1234567890123456",
                    4
            );

            assertEquals(
                    "************3456",
                    result
            );
        }

        @Test
        @DisplayName("Doit retourner la chaîne complète si le nombre visible est supérieur à la longueur")
        void shouldReturnFullStringWhenVisibleCountIsTooLarge() {

            String result = stringService.maskString(
                    "1234",
                    10
            );

            assertEquals(
                    "1234",
                    result
            );
        }

        @Test
        @DisplayName("Doit masquer toute la chaîne si le nombre visible est inférieur ou égal à zéro")
        void shouldMaskEntireStringWhenVisibleCountIsInvalid() {

            String result = stringService.maskString(
                    "123456",
                    0
            );

            assertEquals(
                    "******",
                    result
            );
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur vide")
        void shouldReturnEmptyStringForEmptyInput() {

            String result = stringService.maskString(
                    "",
                    4
            );

            assertEquals(
                    "",
                    result
            );
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur null")
        void shouldReturnEmptyStringForNullInput() {

            String result = stringService.maskString(
                    null,
                    4
            );

            assertEquals(
                    "",
                    result
            );
        }
    }

    @Nested
    @DisplayName("Tests de isPalindrome")
    class IsPalindromeTests {

        @Test
        @DisplayName("Doit retourner true pour un palindrome simple")
        void shouldReturnTrueForSimplePalindrome() {

            boolean result = stringService.isPalindrome("radar");

            assertTrue(result);
        }

        @Test
        @DisplayName("Doit ignorer la casse")
        void shouldIgnoreCase() {

            boolean result = stringService.isPalindrome("Radar");

            assertTrue(result);
        }

        @Test
        @DisplayName("Doit ignorer les espaces et la ponctuation")
        void shouldIgnoreSpacesAndPunctuation() {

            boolean result = stringService.isPalindrome(
                    "Eh ! ça va la vache ?"
            );

            assertTrue(result);
        }

        @Test
        @DisplayName("Doit retourner false pour une chaîne non palindrome")
        void shouldReturnFalseForNonPalindrome() {

            boolean result = stringService.isPalindrome("Bonjour");

            assertFalse(result);
        }

        @Test
        @DisplayName("Doit retourner false pour une chaîne vide")
        void shouldReturnFalseForEmptyString() {

            boolean result = stringService.isPalindrome("");

            assertFalse(result);
        }

        @Test
        @DisplayName("Doit retourner false pour une valeur null")
        void shouldReturnFalseForNullInput() {

            boolean result = stringService.isPalindrome(null);

            assertFalse(result);
        }
    }

    @Nested
    @DisplayName("Tests de longestSequence")
    class LongestSequenceTests {

        @Test
        @DisplayName("Doit retourner la plus longue séquence")
        void shouldReturnLongestSequence() {

            String result = stringService.longestSequence(
                    "aaabbbbbcccc"
            );

            assertEquals("bbbbb", result);
        }

        @Test
        @DisplayName("Doit retourner la première séquence en cas d'égalité")
        void shouldReturnFirstSequenceInCaseOfTie() {

            String result = stringService.longestSequence(
                    "aaabbb"
            );

            assertEquals("aaa", result);
        }

        @Test
        @DisplayName("Doit gérer une chaîne avec un seul caractère")
        void shouldHandleSingleCharacter() {

            String result = stringService.longestSequence(
                    "a"
            );

            assertEquals("a", result);
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur vide")
        void shouldReturnEmptyStringForEmptyInput() {

            String result = stringService.longestSequence("");

            assertEquals("", result);
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur null")
        void shouldReturnEmptyStringForNullInput() {

            String result = stringService.longestSequence(null);

            assertEquals("", result);
        }
    }

    @Nested
    @DisplayName("Tests de truncate")
    class TruncateTests {

        @Test
        @DisplayName("Doit tronquer une chaîne trop longue")
        void shouldTruncateLongString() {

            String result = stringService.truncate(
                    "Ceci est une très longue description",
                    20
            );

            assertEquals(
                    "Ceci est une très...",
                    result
            );
        }

        @Test
        @DisplayName("Doit retourner la chaîne complète si elle est suffisamment courte")
        void shouldReturnFullStringWhenShortEnough() {

            String result = stringService.truncate(
                    "Bonjour",
                    20
            );

            assertEquals(
                    "Bonjour",
                    result
            );
        }

        @Test
        @DisplayName("Doit retourner uniquement des points de suspension pour une longueur invalide")
        void shouldReturnOnlySuspensionPointsForInvalidLength() {

            String result = stringService.truncate(
                    "Bonjour",
                    0
            );

            assertEquals(
                    "...",
                    result
            );
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur vide")
        void shouldReturnEmptyStringForEmptyInput() {

            String result = stringService.truncate(
                    "",
                    10
            );

            assertEquals(
                    "",
                    result
            );
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur null")
        void shouldReturnEmptyStringForNullInput() {

            String result = stringService.truncate(
                    null,
                    10
            );

            assertEquals(
                    "",
                    result
            );
        }
    }

    @Nested
    @DisplayName("Tests de capitalizeWords")
    class CapitalizeWordsTests {

        @Test
        @DisplayName("Doit capitaliser chaque mot")
        void shouldCapitalizeEachWord() {

            String result = stringService.capitalizeWords(
                    "bienvenue sur notre site web"
            );

            assertEquals(
                    "Bienvenue Sur Notre Site Web",
                    result
            );
        }

        @Test
        @DisplayName("Doit gérer les espaces multiples")
        void shouldHandleMultipleSpaces() {

            String result = stringService.capitalizeWords(
                    "bonjour     le    monde"
            );

            assertEquals(
                    "Bonjour Le Monde",
                    result
            );
        }

        @Test
        @DisplayName("Doit convertir les majuscules correctement")
        void shouldNormalizeUppercaseLetters() {

            String result = stringService.capitalizeWords(
                    "BONJOUR LE MONDE"
            );

            assertEquals(
                    "Bonjour Le Monde",
                    result
            );
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur vide")
        void shouldReturnEmptyStringForEmptyInput() {

            String result = stringService.capitalizeWords("");

            assertEquals(
                    "",
                    result
            );
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur null")
        void shouldReturnEmptyStringForNullInput() {

            String result = stringService.capitalizeWords(null);

            assertEquals(
                    "",
                    result
            );
        }
    }
}