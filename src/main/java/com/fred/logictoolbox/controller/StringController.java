package com.fred.logictoolbox.controller;

import com.fred.logictoolbox.common.response.ApiResponse;
import com.fred.logictoolbox.service.string.StringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/string")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "String",
        description = "API de manipulation de chaînes de caractères"
)
public class StringController {

    private final StringService stringService;

    @GetMapping("/length-without-spaces")
    @Operation(
            summary = "Calcule la longueur d'une chaîne sans les espaces",
            description = "Supprime tous les espaces d'une chaîne puis retourne sa longueur"
    )
    public ApiResponse<Integer> getLengthWithoutSpaces(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {
        int result = stringService.getLengthWithoutSpaces(input);

        return ApiResponse.success(
                "Longueur calculée avec succès",
                result
        );
    }

    @GetMapping("/greeting")
    @Operation(
            summary = "Retourne une salutation personnalisée",
            description = "Met la première lettre du prénom en majuscule puis retourne une salutation"
    )
    public ApiResponse<String> formatGreeting(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {

        String result = stringService.formatGreeting(input);

        return ApiResponse.success(
                "Salutation générée avec succès",
                result
        );
    }

    @GetMapping("/ends-with-exclamation")
    @Operation(
            summary = "Vérifie si une chaîne se termine par un point d'exclamation",
            description = "Retourne true si la chaîne se termine par le caractère !"
    )
    public ApiResponse<Boolean> endsWithExclamationMark(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {

        boolean result = stringService.endsWithExclamationMark(input);

        return ApiResponse.success(
                "Vérification effectuée avec succès",
                result
        );
    }

    @GetMapping("/reverse-words")
    @Operation(
            summary = "Inverse l'ordre des mots d'une chaîne",
            description = "Retourne une nouvelle chaîne avec les mots dans l'ordre inverse"
    )
    public ApiResponse<String> reverseWords(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {

        String result = stringService.reverseWords(input);

        return ApiResponse.success(
                "Ordre des mots inversé avec succès",
                result
        );
    }

    @GetMapping("/count-character-occurrences")
    @Operation(
            summary = "Compte le nombre d'occurrences d'un caractère dans une chaîne",
            description = "Retourne le nombre d'occurrences d'un caractère donné dans une chaîne"
    )
    public ApiResponse<Integer> countCharacterOccurrences(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input,

            @RequestParam
            @NotBlank(message = "Le paramètre character ne peut pas être vide")
            String character
    ) {

        int result = stringService.countCharacterOccurrences(
                input,
                character.charAt(0)
        );

        return ApiResponse.success(
                "Nombre d'occurrences calculé avec succès",
                result
        );
    }

    @GetMapping("/to-camel-case")
    @Operation(
            summary = "Convertit une chaîne en camelCase",
            description = "Retourne une chaîne formatée selon la convention camelCase"
    )
    public ApiResponse<String> toCamelCase(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {

        String result = stringService.toCamelCase(input);

        return ApiResponse.success(
                "Conversion camelCase effectuée avec succès",
                result
        );
    }

    @GetMapping("/count-vowels")
    @Operation(
            summary = "Compte le nombre de voyelles dans une chaîne",
            description = "Retourne le nombre de voyelles présentes dans une chaîne de caractères"
    )
    public ApiResponse<Integer> countVowels(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {

        int result = stringService.countVowels(input);

        return ApiResponse.success(
                "Nombre de voyelles calculé avec succès",
                result
        );
    }

    @GetMapping("/alternate-case")
    @Operation(
            summary = "Alterne majuscules et minuscules dans une chaîne",
            description = "Retourne une chaîne avec une alternance de caractères majuscules et minuscules"
    )
    public ApiResponse<String> alternateCase(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {

        String result = stringService.alternateCase(input);

        return ApiResponse.success(
                "Transformation alternée effectuée avec succès",
                result
        );
    }

    @GetMapping("/remove-consecutive-duplicates")
    @Operation(
            summary = "Supprime les caractères en double consécutifs",
            description = """
                Nettoie une chaîne en réduisant les répétitions consécutives
                de caractères supérieures à deux occurrences.
                                
                Les doubles caractères légitimes sont conservés afin de
                préserver l'orthographe normale des mots.
                                
                Cette approche représente un compromis volontaire entre
                correction automatique et simplicité algorithmique.
                                
                Exemples :
                - "Bonjouuuur !!!" devient "Bonjour !"
                - "Belle adresse" reste inchangé
                - "Belllllle" devient "Bele" (tolérance acceptée)
                """
    )
    public ApiResponse<String> removeConsecutiveDuplicates(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {

        String result = stringService.removeConsecutiveDuplicates(input);

        return ApiResponse.success(
                "Nettoyage des doublons effectué avec succès",
                result
        );
    }

    @GetMapping("/extract-initials")
    @Operation(
            summary = "Extrait les initiales d'un nom complet",
            description = """
                Analyse un nom complet et retourne les initiales
                correspondantes en majuscules.
                                
                Exemples :
                - "Jean Pierre Dupont" devient "JPD"
                - "marie curie" devient "MC"
                """
    )
    public ApiResponse<String> extractInitials(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {

        String result = stringService.extractInitials(input);

        return ApiResponse.success(
                "Initiales extraites avec succès",
                result
        );
    }

    @GetMapping("/mask")
    @Operation(
            summary = "Masque une chaîne sauf les derniers caractères",
            description = """
                Masque les caractères sensibles d'une chaîne
                tout en conservant visibles les N derniers caractères.
                                
                Contrairement à l'exemple simplifié de l'exercice,
                cette implémentation conserve volontairement la longueur
                originale de la chaîne en remplaçant les caractères
                masqués par des '*'.
                                
                Cette approche est plus proche des pratiques réelles
                utilisées dans les applications bancaires et systèmes
                manipulant des données sensibles.
                                
                Exemple :
                - "1234567890123456" avec 4 devient "************3456"
                """
    )
    public ApiResponse<String> maskString(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input,

            @RequestParam
            int visibleCharactersCount
    ) {

        String result = stringService.maskString(
                input,
                visibleCharactersCount
        );

        return ApiResponse.success(
                "Masquage effectué avec succès",
                result
        );
    }

    @GetMapping("/is-palindrome")
    @Operation(
            summary = "Vérifie si une chaîne est un palindrome",
            description = """
                Vérifie si une chaîne est identique lorsqu'elle est lue
                de gauche à droite et de droite à gauche.
                                
                La vérification ignore :
                - les espaces
                - la casse
                - les caractères spéciaux
                - la ponctuation
                                
                Exemple :
                - "Eh ! ça va la vache ?" retourne true
                """
    )
    public ApiResponse<Boolean> isPalindrome(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {

        boolean result = stringService.isPalindrome(input);

        return ApiResponse.success(
                "Vérification du palindrome effectuée avec succès",
                result
        );
    }

    @GetMapping("/longest-sequence")
    @Operation(
            summary = "Trouve la plus longue séquence de caractères identiques",
            description = """
                Analyse une chaîne et retourne la plus longue suite
                de caractères identiques consécutifs.
                                
                Exemple :
                - "aaabbbbbcccc" retourne "bbbbb"
                """
    )
    public ApiResponse<String> longestSequence(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {

        String result = stringService.longestSequence(input);

        return ApiResponse.success(
                "Plus longue séquence trouvée avec succès",
                result
        );
    }

    @GetMapping("/truncate")
    @Operation(
            summary = "Tronque un texte avec des points de suspension",
            description = """
                Réduit un texte à une longueur maximale
                puis ajoute des points de suspension.
                                
                Cas d'usage typiques :
                - aperçu de descriptions
                - listes d'articles
                - cartes de contenu
                                
                Exemple :
                - "Ceci est une très longue description" avec 20
                  devient "Ceci est une très..."
                """
    )
    public ApiResponse<String> truncate(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input,

            @RequestParam
            int maxLength
    ) {

        String result = stringService.truncate(
                input,
                maxLength
        );

        return ApiResponse.success(
                "Texte tronqué avec succès",
                result
        );
    }

    @GetMapping("/capitalize-words")
    @Operation(
            summary = "Capitalise la première lettre de chaque mot",
            description = """
                Formate une chaîne en mettant la première lettre
                de chaque mot en majuscule.
                                
                Cas d'usage typiques :
                - titres d'articles
                - noms de documents
                - formatage de contenu
                                
                Exemple :
                - "bienvenue sur notre site web"
                  devient
                  "Bienvenue Sur Notre Site Web"
                """
    )
    public ApiResponse<String> capitalizeWords(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {

        String result = stringService.capitalizeWords(input);

        return ApiResponse.success(
                "Texte formaté avec succès",
                result
        );
    }
}