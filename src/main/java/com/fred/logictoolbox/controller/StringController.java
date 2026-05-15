package com.fred.logictoolbox.controller;

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
@Tag(name = "String", description = "Endpoints de manipulation de chaînes de caractères")
public class StringController {

    private final StringService stringService;

    @GetMapping("/length-without-spaces")
    @Operation(
            summary = "Retourne la longueur d'une chaîne sans les espaces",
            description = "Supprime tous les espaces d'une chaîne puis retourne sa longueur"
    )
    public int getLengthWithoutSpaces(
            @RequestParam
            @NotBlank(message = "Le paramètre input ne peut pas être vide")
            String input
    ) {
        return stringService.getLengthWithoutSpaces(input);
    }
}