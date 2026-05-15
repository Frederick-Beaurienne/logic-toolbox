package com.fred.logictoolbox.controller;

import com.fred.logictoolbox.model.payload.request.*;
import com.fred.logictoolbox.model.payload.response.ApiResponse;
import com.fred.logictoolbox.model.payload.response.DifferenceDetailResponse;
import com.fred.logictoolbox.model.payload.response.objectStatsResponse.ObjectStatsResponse;
import com.fred.logictoolbox.service.objectutils.ObjectUtilsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * REST controller exposing object utility endpoints.
 */
@RestController
@RequestMapping("/api/object")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Object Utilities",
        description = "Endpoints for object and map manipulation utilities"
)
public class ObjectUtilsController {

    private final ObjectUtilsService objectUtilsService;

    @PostMapping("/values")
    @Operation(
            summary = "Récupère toutes les valeurs d'un objet",
            description = """
                    Retourne toutes les valeurs contenues
                    dans un objet JSON.
                    
                    Exemple :
                    {
                      "level1": 100,
                      "level2": 85,
                      "level3": 95
                    }
                    
                    retourne :
                    [100, 85, 95]
                    """
    )
    public ApiResponse<List<Object>> getValues(
            @RequestBody
            Map<String, Object> input
    ) {

        List<Object> result = objectUtilsService.getValues(input);

        return ApiResponse.success(
                "Valeurs récupérées avec succès",
                result
        );
    }

    @PostMapping("/convert-euros-to-dollars")
    @Operation(
            summary = "Convertit des valeurs en euros vers dollars",
            description = """
                    Transforme les valeurs numériques d'un objet
                    représentant des prix en euros vers des montants
                    convertis en dollars.
                    
                    Exemple :
                    {
                      "book": 20,
                      "pen": 5,
                      "notebook": 10
                    }
                    
                    retourne :
                    {
                      "book": 22.0,
                      "pen": 5.5,
                      "notebook": 11.0
                    }
                    """
    )
    public ApiResponse<Map<String, Double>> convertEurosToDollars(
            @RequestBody
            Map<String, Integer> input
    ) {

        Map<String, Double> result =
                objectUtilsService.transformValues(
                        input,
                        euroValue -> euroValue * 1.1
                );

        return ApiResponse.success(
                "Conversion effectuée avec succès",
                result
        );
    }

    @PostMapping("/merge")
    @Operation(
            summary = "Fusionne deux objets numériques",
            description = """
                    Fusionne deux objets JSON en additionnant
                    les valeurs numériques partageant les mêmes clés.
                    
                    Les nombres entiers et décimaux sont supportés.
                    
                    Le résultat tente de conserver le type numérique
                    le plus naturel possible :
                    - une valeur entière reste un entier
                    - une valeur décimale reste un nombre à virgule
                    
                    Exemple :
                    {
                      "firstObject": {
                        "january": 1000,
                        "book": 10.5
                      },
                      "secondObject": {
                        "january": 800,
                        "book": 2
                      }
                    }
                    
                    retourne :
                    {
                      "january": 1800,
                      "book": 12.5
                    }
                    """
    )
    public ApiResponse<Map<String, Number>> mergeObjects(
            @Valid
            @RequestBody
            MergeObjectsRequest request
    ) {

        Map<String, Number> result =
                objectUtilsService.mergeObjects(
                        request.getFirstObject(),
                        request.getSecondObject()
                );

        return ApiResponse.success(
                "Fusion effectuée avec succès",
                result
        );
    }

    @PostMapping("/filter-out-of-stock")
    @Operation(
            summary = "Filtre les produits en rupture de stock",
            description = """
                    Filtre un objet JSON représentant un inventaire
                    afin de retourner uniquement les produits
                    dont le stock est égal à zéro.
                    
                    Exemple :
                    {
                      "laptop": 0,
                      "smartphone": 5,
                      "tablet": 0,
                      "headphones": 8
                    }
                    
                    retourne :
                    {
                      "laptop": 0,
                      "tablet": 0
                    }
                    """
    )
    public ApiResponse<Map<String, Integer>> filterOutOfStockProducts(
            @RequestBody
            Map<String, Integer> input
    ) {

        Map<String, Integer> result =
                objectUtilsService.filterObject(
                        input,
                        stock -> stock == 0
                );

        return ApiResponse.success(
                "Produits en rupture récupérés avec succès",
                result
        );
    }

    @PostMapping("/flat-to-nested")
    @Operation(
            summary = "Convertit un objet plat en objet imbriqué",
            description = """
                    Transforme un objet JSON plat utilisant des points
                    comme séparateurs en une structure JSON imbriquée.
                    
                    Exemple :
                    {
                      "app.name": "MyApp",
                      "app.version": "1.0.0",
                      "database.host": "localhost"
                    }
                    
                    retourne :
                    {
                      "app": {
                        "name": "MyApp",
                        "version": "1.0.0"
                      },
                      "database": {
                        "host": "localhost"
                      }
                    }
                    """
    )
    public ApiResponse<Map<String, Object>> flatToNested(
            @RequestBody
            Map<String, Object> input
    ) {

        Map<String, Object> result =
                objectUtilsService.flatToNested(input);

        return ApiResponse.success(
                "Conversion effectuée avec succès",
                result
        );
    }

    @PostMapping("/find-keys-by-value")
    @Operation(
            summary = "Trouve les clés associées à une valeur",
            description = """
                    Recherche toutes les clés d'un objet JSON
                    possédant une valeur spécifique.
                    
                    Exemple :
                    {
                      "object": {
                        "laptop": 0,
                        "mouse": 5,
                        "keyboard": 0,
                        "monitor": 3
                      },
                      "searchedValue": 0
                    }
                    
                    retourne :
                    ["laptop", "keyboard"]
                    """
    )
    public ApiResponse<List<String>> findKeysByValue(
            @Valid
            @RequestBody
            FindKeysByValueRequest request
    ) {

        List<String> result =
                objectUtilsService.findKeysByValue(
                        request.getObject(),
                        request.getSearchedValue()
                );

        return ApiResponse.success(
                "Clés trouvées avec succès",
                result
        );
    }

    @PostMapping("/create-from-arrays")
    @Operation(
            summary = "Crée un objet à partir de deux tableaux",
            description = """
                    Construit un objet JSON à partir
                    d'une liste de clés et d'une liste de valeurs.
                    
                    Les deux listes doivent avoir la même taille.
                    
                    Exemple :
                    {
                      "keys": ["Alice", "Bob", "Charlie"],
                      "values": [100, 85, 90]
                    }
                    
                    retourne :
                    {
                      "Alice": 100,
                      "Bob": 85,
                      "Charlie": 90
                    }
                    """
    )
    public ApiResponse<Map<String, Object>> createObjectFromArrays(
            @Valid
            @RequestBody
            CreateObjectFromArraysRequest request
    ) {

        Map<String, Object> result =
                objectUtilsService.createObjectFromArrays(
                        request.getKeys(),
                        request.getValues()
                );

        return ApiResponse.success(
                "Objet créé avec succès",
                result
        );
    }

    @PostMapping("/count-values")
    @Operation(
            summary = "Compte les occurrences des valeurs d'un objet",
            description = """
                    Analyse un objet JSON et compte
                    le nombre d'occurrences de chaque valeur.
                    
                    Exemple :
                    {
                      "order1": "pending",
                      "order2": "delivered",
                      "order3": "pending"
                    }
                    
                    retourne :
                    {
                      "pending": 2,
                      "delivered": 1
                    }
                    """
    )
    public ApiResponse<Map<Object, Long>> countValues(
            @RequestBody
            Map<String, Object> input
    ) {

        Map<Object, Long> result =
                objectUtilsService.countValues(input);

        return ApiResponse.success(
                "Occurrences calculées avec succès",
                result
        );
    }

    @PostMapping("/extract-properties")
    @Operation(
            summary = "Extrait certaines propriétés d'un objet",
            description = """
                    Retourne uniquement certaines propriétés
                    d'un objet JSON.
                    
                    Les propriétés absentes sont ignorées.
                    
                    Exemple :
                    {
                      "object": {
                        "name": "Jean Martin",
                        "email": "jean@email.com",
                        "password": "secret123",
                        "age": 35
                      },
                      "properties": ["name", "age"]
                    }
                    
                    retourne :
                    {
                      "name": "Jean Martin",
                      "age": 35
                    }
                    """
    )
    public ApiResponse<Map<String, Object>> extractProperties(
            @Valid
            @RequestBody
            ExtractPropertiesRequest request
    ) {

        Map<String, Object> result =
                objectUtilsService.extractProperties(
                        request.getObject(),
                        request.getProperties()
                );

        return ApiResponse.success(
                "Propriétés extraites avec succès",
                result
        );
    }

    @PostMapping("/sort-by-value")
    @Operation(
            summary = "Trie un objet JSON par valeur",
            description = """
                    Trie les propriétés d'un objet JSON
                    selon leurs valeurs dans l'ordre croissant.
                    
                    Exemple :
                    {
                      "Alice": 85,
                      "Bob": 92,
                      "Charlie": 78,
                      "David": 95
                    }
                    
                    retourne :
                    {
                      "Charlie": 78,
                      "Alice": 85,
                      "Bob": 92,
                      "David": 95
                    }
                    """
    )
    public ApiResponse<Map<String, Integer>> sortObjectByValue(
            @RequestBody
            Map<String, Integer> input
    ) {

        Map<String, Integer> result =
                objectUtilsService.sortObjectByValue(input);

        return ApiResponse.success(
                "Objet trié avec succès",
                result
        );
    }

    @PostMapping("/find-max-value")
    @Operation(
            summary = "Trouve la valeur maximale d'un objet numérique",
            description = """
                    Analyse un objet JSON contenant des nombres
                    et retourne la valeur maximale.
                    
                    Exemple :
                    {
                      "level1": 850,
                      "level2": 920,
                      "level3": 880,
                      "level4": 1020
                    }
                    
                    retourne :
                    1020
                    """
    )
    public ApiResponse<Integer> findMaxValue(
            @RequestBody
            Map<String, Integer> input
    ) {

        Integer result =
                objectUtilsService.findMaxValue(input);

        return ApiResponse.success(
                "Valeur maximale trouvée avec succès",
                result
        );
    }

    @PostMapping("/create-from-pairs")
    @Operation(
            summary = "Crée un objet à partir de paires clé-valeur",
            description = """
                    Construit un objet JSON à partir
                    d'une liste de paires clé-valeur.
                    
                    Chaque paire doit contenir exactement :
                    - une clé
                    - une valeur
                    
                    Exemple :
                    [
                      ["pommes", 2.5],
                      ["bananes", 1.8],
                      ["oranges", 2.2]
                    ]
                    
                    retourne :
                    {
                      "pommes": 2.5,
                      "bananes": 1.8,
                      "oranges": 2.2
                    }
                    """
    )
    public ApiResponse<Map<String, Object>> createObjectFromPairs(
            @Valid
            @RequestBody
            CreateObjectFromPairsRequest request
    ) {

        Map<String, Object> result =
                objectUtilsService.createObjectFromPairs(
                        request.getPairs()
                );

        return ApiResponse.success(
                "Objet créé avec succès",
                result
        );
    }

    @PostMapping("/find-value")
    @Operation(
            summary = "Recherche une valeur dans un objet imbriqué",
            description = """
                    Recherche une valeur dans une structure JSON imbriquée
                    et retourne le chemin menant à cette valeur.
                    
                    Exemple :
                    {
                      "app": {
                        "settings": {
                          "theme": "dark"
                        }
                      }
                    }
                    
                    recherche :
                    "dark"
                    
                    retourne :
                    ["app", "settings", "theme"]
                    """
    )
    public ApiResponse<List<String>> findValueInObject(
            @Valid
            @RequestBody
            FindValueInObjectRequest request
    ) {

        List<String> result =
                objectUtilsService.findValueInObject(
                        request.getObject(),
                        request.getSearchedValue()
                );

        return ApiResponse.success(
                "Recherche effectuée avec succès",
                result
        );
    }

    @PostMapping("/group-by-property")
    @Operation(
            summary = "Groupe des objets par une propriété",
            description = """
                    Groupe une liste d'objets JSON
                    selon la valeur d'une propriété spécifique.
                    
                    Les objets ne contenant pas la propriété
                    sont ignorés.
                    """
    )
    public ApiResponse<Map<Object, List<Map<String, Object>>>> groupByProperty(
            @Valid
            @RequestBody
            GroupByPropertyRequest request
    ) {

        Map<Object, List<Map<String, Object>>> result =
                objectUtilsService.groupByProperty(
                        request.getObjects(),
                        request.getProperty()
                );

        return ApiResponse.success(
                "Regroupement effectué avec succès",
                result
        );
    }

    @PostMapping("/validate")
    @Operation(
            summary = "Valide un objet JSON selon un schéma",
            description = """
                    Vérifie qu'un objet JSON respecte
                    un schéma de types prédéfini.
                    
                    Types supportés :
                    - string
                    - number
                    - boolean
                    - object
                    - array
                    """
    )
    public ApiResponse<Boolean> validateObject(
            @Valid
            @RequestBody
            ValidateObjectRequest request
    ) {

        boolean result =
                objectUtilsService.validateObject(
                        request.getObject(),
                        request.getSchema()
                );

        return ApiResponse.success(
                "Validation effectuée avec succès",
                result
        );
    }

    @PostMapping("/compare-differences")
    @Operation(
            summary = "Compare les différences entre deux objets",
            description = """
                    Compare deux objets JSON
                    et retourne les différences détectées.
                    
                    Types de différences :
                    - added
                    - removed
                    - modified
                    """
    )
    public ApiResponse<Map<String, DifferenceDetailResponse>>
    compareDifferences(
            @Valid
            @RequestBody
            CompareDifferencesRequest request
    ) {

        Map<String, DifferenceDetailResponse> result =
                objectUtilsService.compareDifferences(
                        request.getFirstObject(),
                        request.getSecondObject()
                );

        return ApiResponse.success(
                "Comparaison effectuée avec succès",
                result
        );
    }

    @PostMapping("/to-url-params")
    @Operation(
            summary = "Convertit un objet en paramètres d'URL",
            description = """
                    Convertit un objet JSON en chaîne
                    de paramètres d'URL encodés en UTF-8.
                    
                    Exemple :
                    {
                      "query": "ordinateur portable",
                      "maxPrice": 1000
                    }
                    
                    retourne :
                    query=ordinateur+portable&maxPrice=1000
                    """
    )
    public ApiResponse<String> objectToUrlParams(
            @RequestBody
            Map<String, Object> input
    ) {

        String result =
                objectUtilsService.objectToUrlParams(
                        input
                );

        return ApiResponse.success(
                "Conversion effectuée avec succès",
                result
        );
    }

    @PostMapping("/stats")
    @Operation(
            summary = "Génère un résumé statistique",
            description = """
                    Analyse un objet JSON numérique
                    et retourne un résumé statistique complet.
                    
                    Statistiques calculées :
                    - minimum
                    - maximum
                    - moyenne
                    - total
                    - médiane
                    - variance
                    - écart-type
                    """
    )
    public ApiResponse<ObjectStatsResponse> getObjectStats(
            @RequestBody
            Map<String, Number> input
    ) {

        ObjectStatsResponse result =
                objectUtilsService.getObjectStats(
                        input
                );

        return ApiResponse.success(
                "Statistiques générées avec succès",
                result
        );
    }
}