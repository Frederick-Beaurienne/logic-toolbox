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
}