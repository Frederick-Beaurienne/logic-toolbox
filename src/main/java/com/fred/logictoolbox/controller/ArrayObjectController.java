package com.fred.logictoolbox.controller;

import com.fred.logictoolbox.model.payload.request.*;
import com.fred.logictoolbox.model.payload.response.ApiResponse;
import com.fred.logictoolbox.service.arrayobject.ArrayObjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * REST controller exposing array object utility endpoints.
 */
@RestController
@RequestMapping("/api/array-object")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Array Object Utilities",
        description = "Endpoints for array object manipulation utilities"
)
public class ArrayObjectController {

    private final ArrayObjectService arrayObjectService;

    @PostMapping("/filter-by-property")
    @Operation(
            summary = "Filtre un tableau d'objets selon une propriété",
            description = """
                Filtre une liste d'objets JSON
                en fonction d'une propriété et d'une valeur.
                """
    )
    public ApiResponse<List<Map<String, Object>>>
    filterByProperty(
            @Valid
            @RequestBody
            FilterByPropertyRequest request
    ) {

        List<Map<String, Object>> result =
                arrayObjectService.filterByProperty(
                        request.getObjects(),
                        request.getProperty(),
                        request.getValue()
                );

        return ApiResponse.success(
                "Filtrage effectué avec succès",
                result
        );
    }

    @PostMapping("/group-by")
    @Operation(
            summary = "Regroupe des objets par propriété",
            description = """
                Regroupe une liste d'objets JSON
                selon la valeur d'une propriété donnée.
                """
    )
    public ApiResponse<Map<Object, List<Map<String, Object>>>>
    groupBy(
            @Valid
            @RequestBody
            GroupByPropertyRequest request
    ) {

        Map<Object, List<Map<String, Object>>> result =
                arrayObjectService.groupBy(
                        request.getObjects(),
                        request.getProperty()
                );

        return ApiResponse.success(
                "Regroupement effectué avec succès",
                result
        );
    }

    @PostMapping("/find-intersection")
    @Operation(
            summary = "Trouve l'intersection entre deux tableaux d'objets",
            description = """
                Retourne les objets présents
                dans les deux tableaux selon
                une propriété commune.
                """
    )
    public ApiResponse<List<Map<String, Object>>>
    findIntersection(
            @Valid
            @RequestBody
            FindIntersectionRequest request
    ) {

        List<Map<String, Object>> result =
                arrayObjectService.findIntersection(
                        request.getFirstArray(),
                        request.getSecondArray(),
                        request.getProperty()
                );

        return ApiResponse.success(
                "Intersection trouvée avec succès",
                result
        );
    }

    @PostMapping("/transform")
    @Operation(
            summary = "Transforme un tableau d'objets",
            description = """
                Transforme une liste d'objets JSON
                selon une transformation prédéfinie.

                Transformations disponibles :
                - FULL_NAME
                - ANNUAL_SALARY
                """
    )
    public ApiResponse<List<Map<String, Object>>>
    transformArray(
            @Valid
            @RequestBody
            TransformArrayRequest request
    ) {

        Function<Map<String, Object>, Map<String, Object>>
                transformer =
                switch (
                        request.getTransformation()
                                .toUpperCase()
                        ) {

                    case "FULL_NAME" -> object -> {

                        Map<String, Object> result =
                                new HashMap<>();

                        result.put(
                                "id",
                                object.get("id")
                        );

                        result.put(
                                "fullName",
                                object.get("firstName")
                                        + " "
                                        + object.get("lastName")
                        );

                        return result;
                    };

                    case "ANNUAL_SALARY" -> object -> {

                        Map<String, Object> result =
                                new HashMap<>(object);

                        Object salary =
                                object.get("salary");

                        if (salary instanceof Number number) {

                            result.put(
                                    "annualSalary",
                                    number.doubleValue() * 12
                            );
                        }

                        return result;
                    };

                    default -> null;
                };

        List<Map<String, Object>> result =
                arrayObjectService.transformArray(
                        request.getObjects(),
                        transformer
                );

        return ApiResponse.success(
                "Transformation effectuée avec succès",
                result
        );
    }

    @PostMapping("/aggregate")
    @Operation(
            summary = "Agrège les données d'un tableau d'objets",
            description = """
                Regroupe des objets JSON
                et additionne une valeur numérique.
                """
    )
    public ApiResponse<Map<Object, Double>>
    aggregateData(
            @Valid
            @RequestBody
            AggregateDataRequest request
    ) {

        Map<Object, Double> result =
                arrayObjectService.aggregateData(
                        request.getObjects(),
                        request.getGroupBy(),
                        request.getValueField()
                );

        return ApiResponse.success(
                "Agrégation effectuée avec succès",
                result
        );
    }
}