package com.fred.logictoolbox.service;

import com.fred.logictoolbox.service.arrayobject.ArrayObjectService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DisplayName("Tests ArrayObjectService")
class ArrayObjectServiceTest {

    // ---------- DEPENDENCIES ---------- //

    @Autowired
    private ArrayObjectService arrayObjectService;

    @Nested
    @DisplayName("Tests de filterByProperty")
    class FilterByPropertyTests {

        @Test
        @DisplayName("Doit filtrer les objets correctement")
        void shouldFilterObjectsCorrectly() {

            List<Map<String, Object>> users = List.of(
                    Map.of(
                            "name", "Alice",
                            "active", true
                    ),
                    Map.of(
                            "name", "Bob",
                            "active", false
                    ),
                    Map.of(
                            "name", "Charlie",
                            "active", true
                    )
            );

            List<Map<String, Object>> result =
                    arrayObjectService.filterByProperty(
                            users,
                            "active",
                            true
                    );

            assertEquals(2, result.size());
        }

        @Test
        @DisplayName("Doit retourner une liste vide pour une liste vide")
        void shouldReturnEmptyListForEmptyInput() {

            List<Map<String, Object>> result =
                    arrayObjectService.filterByProperty(
                            Collections.emptyList(),
                            "active",
                            true
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une liste vide pour une valeur null")
        void shouldReturnEmptyListForNullInput() {

            List<Map<String, Object>> result =
                    arrayObjectService.filterByProperty(
                            null,
                            "active",
                            true
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de groupBy")
    class GroupByTests {

        @Test
        @DisplayName("Doit regrouper les objets correctement")
        void shouldGroupObjectsCorrectly() {

            List<Map<String, Object>> employees = List.of(
                    Map.of(
                            "name", "Alice",
                            "department", "IT"
                    ),
                    Map.of(
                            "name", "Bob",
                            "department", "HR"
                    ),
                    Map.of(
                            "name", "Charlie",
                            "department", "IT"
                    )
            );

            Map<Object, List<Map<String, Object>>> result =
                    arrayObjectService.groupBy(
                            employees,
                            "department"
                    );

            assertEquals(2, result.size());

            assertEquals(
                    2,
                    result.get("IT").size()
            );

            assertEquals(
                    1,
                    result.get("HR").size()
            );
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une liste vide")
        void shouldReturnEmptyMapForEmptyInput() {

            Map<Object, List<Map<String, Object>>> result =
                    arrayObjectService.groupBy(
                            Collections.emptyList(),
                            "department"
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une valeur null")
        void shouldReturnEmptyMapForNullInput() {

            Map<Object, List<Map<String, Object>>> result =
                    arrayObjectService.groupBy(
                            null,
                            "department"
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de findIntersection")
    class FindIntersectionTests {

        @Test
        @DisplayName("Doit trouver les intersections correctement")
        void shouldFindIntersectionsCorrectly() {

            List<Map<String, Object>> firstUsers = List.of(
                    Map.of(
                            "id", 1,
                            "name", "Alice"
                    ),
                    Map.of(
                            "id", 2,
                            "name", "Bob"
                    )
            );

            List<Map<String, Object>> secondUsers = List.of(
                    Map.of(
                            "id", 2,
                            "name", "Bob"
                    ),
                    Map.of(
                            "id", 3,
                            "name", "Charlie"
                    )
            );

            List<Map<String, Object>> result =
                    arrayObjectService.findIntersection(
                            firstUsers,
                            secondUsers,
                            "id"
                    );

            assertEquals(1, result.size());

            assertEquals(
                    "Bob",
                    result.get(0).get("name")
            );
        }

        @Test
        @DisplayName("Doit retourner une liste vide pour une liste vide")
        void shouldReturnEmptyListForEmptyInput() {

            List<Map<String, Object>> result =
                    arrayObjectService.findIntersection(
                            Collections.emptyList(),
                            Collections.emptyList(),
                            "id"
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une liste vide pour une valeur null")
        void shouldReturnEmptyListForNullInput() {

            List<Map<String, Object>> result =
                    arrayObjectService.findIntersection(
                            null,
                            null,
                            "id"
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de transformArray")
    class TransformArrayTests {

        @Test
        @DisplayName("Doit transformer les noms complets")
        void shouldTransformFullNames() {

            List<Map<String, Object>> employees = List.of(
                    Map.of(
                            "id", 1,
                            "firstName", "John",
                            "lastName", "Doe"
                    )
            );

            List<Map<String, Object>> result =
                    arrayObjectService.transformArray(
                            employees,
                            object -> {

                                Map<String, Object> transformed =
                                        new HashMap<>();

                                transformed.put(
                                        "id",
                                        object.get("id")
                                );

                                transformed.put(
                                        "fullName",
                                        object.get("firstName")
                                                + " "
                                                + object.get("lastName")
                                );

                                return transformed;
                            }
                    );

            assertEquals(1, result.size());

            assertEquals(
                    "John Doe",
                    result.get(0).get("fullName")
            );
        }

        @Test
        @DisplayName("Doit calculer les salaires annuels")
        void shouldCalculateAnnualSalaries() {

            List<Map<String, Object>> employees = List.of(
                    Map.of(
                            "salary", 5000
                    )
            );

            List<Map<String, Object>> result =
                    arrayObjectService.transformArray(
                            employees,
                            object -> {

                                Map<String, Object> transformed =
                                        new HashMap<>(object);

                                Object salary =
                                        object.get("salary");

                                if (salary instanceof Number number) {

                                    transformed.put(
                                            "annualSalary",
                                            number.doubleValue() * 12
                                    );
                                }

                                return transformed;
                            }
                    );

            assertEquals(
                    60000.0,
                    result.get(0).get("annualSalary")
            );
        }

        @Test
        @DisplayName("Doit retourner une liste vide pour une fonction null")
        void shouldReturnEmptyListForNullTransformer() {

            List<Map<String, Object>> result =
                    arrayObjectService.transformArray(
                            List.of(Map.of()),
                            null
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de aggregateData")
    class AggregateDataTests {

        @Test
        @DisplayName("Doit agréger les données correctement")
        void shouldAggregateDataCorrectly() {

            List<Map<String, Object>> transactions = List.of(
                    Map.of(
                            "category", "Food",
                            "amount", 100
                    ),
                    Map.of(
                            "category", "Food",
                            "amount", 50
                    ),
                    Map.of(
                            "category", "Income",
                            "amount", 75
                    )
            );

            Map<Object, Double> result =
                    arrayObjectService.aggregateData(
                            transactions,
                            "category",
                            "amount"
                    );

            assertEquals(
                    150.0,
                    result.get("Food")
            );

            assertEquals(
                    75.0,
                    result.get("Income")
            );
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une liste vide")
        void shouldReturnEmptyMapForEmptyInput() {

            Map<Object, Double> result =
                    arrayObjectService.aggregateData(
                            Collections.emptyList(),
                            "category",
                            "amount"
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une valeur null")
        void shouldReturnEmptyMapForNullInput() {

            Map<Object, Double> result =
                    arrayObjectService.aggregateData(
                            null,
                            "category",
                            "amount"
                    );

            assertTrue(result.isEmpty());
        }
    }
}