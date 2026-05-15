package com.fred.logictoolbox.service;

import com.fred.logictoolbox.model.payload.response.DifferenceDetailResponse;
import com.fred.logictoolbox.model.payload.response.objectStatsResponse.ObjectStatsResponse;
import com.fred.logictoolbox.service.objectutils.ObjectUtilsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ObjectUtilsServiceTest {

    @Autowired
    private ObjectUtilsService objectUtilsService;

    @Nested
    @DisplayName("Tests de getValues")
    class GetValuesTests {

        @Test
        @DisplayName("Doit retourner toutes les valeurs d'une map")
        void shouldReturnAllMapValues() {

            Map<String, Integer> inputMap = Map.of(
                    "level1", 100,
                    "level2", 85,
                    "level3", 95
            );

            List<Integer> result =
                    objectUtilsService.getValues(inputMap);

            assertTrue(
                    result.containsAll(
                            List.of(100, 85, 95)
                    )
            );

            assertEquals(3, result.size());
        }

        @Test
        @DisplayName("Doit retourner une liste vide pour une map vide")
        void shouldReturnEmptyListForEmptyMap() {

            List<Object> result =
                    objectUtilsService.getValues(Collections.emptyMap());

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une liste vide pour une valeur null")
        void shouldReturnEmptyListForNullMap() {

            List<Object> result =
                    objectUtilsService.getValues(null);

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de transformValues")
    class TransformValuesTests {

        @Test
        @DisplayName("Doit transformer les valeurs d'une map")
        void shouldTransformMapValues() {

            Map<String, Integer> pricesInEuros = Map.of(
                    "book", 20,
                    "pen", 5,
                    "notebook", 10
            );

            Map<String, Double> result =
                    objectUtilsService.transformValues(
                            pricesInEuros,
                            euroValue -> euroValue * 1.1
                    );

            assertEquals(22.0, result.get("book"));
            assertEquals(5.5, result.get("pen"));
            assertEquals(11.0, result.get("notebook"));
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une map vide")
        void shouldReturnEmptyMapForEmptyInput() {

            Map<Object, Object> result =
                    objectUtilsService.transformValues(
                            Collections.emptyMap(),
                            value -> value
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une valeur null")
        void shouldReturnEmptyMapForNullInput() {

            Map<Object, Object> result =
                    objectUtilsService.transformValues(
                            null,
                            value -> value
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une fonction null")
        void shouldReturnEmptyMapForNullTransformer() {

            Map<String, Integer> inputMap = Map.of(
                    "test", 1
            );

            Map<String, Object> result =
                    objectUtilsService.transformValues(
                            inputMap,
                            null
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de mergeObjects")
    class MergeObjectsTests {

        @Test
        @DisplayName("Doit fusionner deux maps en additionnant les valeurs")
        void shouldMergeMapsAndSumValues() {

            Map<String, Number> firstMap = Map.of(
                    "january", 1000,
                    "february", 1200
            );

            Map<String, Number> secondMap = Map.of(
                    "january", 800,
                    "february", 950
            );

            Map<String, Number> result =
                    objectUtilsService.mergeObjects(
                            firstMap,
                            secondMap
                    );

            assertEquals(1800, result.get("january"));
            assertEquals(2150, result.get("february"));

            assertInstanceOf(
                    Integer.class,
                    result.get("january")
            );
        }

        @Test
        @DisplayName("Doit gérer les valeurs décimales")
        void shouldHandleDecimalValues() {

            Map<String, Number> firstMap = Map.of(
                    "book", 10.5
            );

            Map<String, Number> secondMap = Map.of(
                    "book", 2
            );

            Map<String, Number> result =
                    objectUtilsService.mergeObjects(
                            firstMap,
                            secondMap
                    );

            assertEquals(12.5, result.get("book"));

            assertInstanceOf(
                    Double.class,
                    result.get("book")
            );
        }

        @Test
        @DisplayName("Doit fusionner les clés uniques")
        void shouldMergeUniqueKeys() {

            Map<String, Number> firstMap = Map.of(
                    "january", 1000
            );

            Map<String, Number> secondMap = Map.of(
                    "february", 950
            );

            Map<String, Number> result =
                    objectUtilsService.mergeObjects(
                            firstMap,
                            secondMap
                    );

            assertEquals(1000, result.get("january"));
            assertEquals(950, result.get("february"));
        }

        @Test
        @DisplayName("Doit retourner uniquement la première map si la seconde est null")
        void shouldReturnFirstMapWhenSecondMapIsNull() {

            Map<String, Number> firstMap = Map.of(
                    "january", 1000
            );

            Map<String, Number> result =
                    objectUtilsService.mergeObjects(
                            firstMap,
                            null
                    );

            assertEquals(1000, result.get("january"));
        }

        @Test
        @DisplayName("Doit retourner uniquement la seconde map si la première est null")
        void shouldReturnSecondMapWhenFirstMapIsNull() {

            Map<String, Number> secondMap = Map.of(
                    "february", 950
            );

            Map<String, Number> result =
                    objectUtilsService.mergeObjects(
                            null,
                            secondMap
                    );

            assertEquals(950, result.get("february"));
        }

        @Test
        @DisplayName("Doit retourner une map vide si les deux maps sont null")
        void shouldReturnEmptyMapWhenBothMapsAreNull() {

            Map<String, Number> result =
                    objectUtilsService.mergeObjects(
                            null,
                            null
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide si les deux maps sont vides")
        void shouldReturnEmptyMapWhenBothMapsAreEmpty() {

            Map<String, Number> result =
                    objectUtilsService.mergeObjects(
                            Collections.emptyMap(),
                            Collections.emptyMap()
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de filterObject")
    class FilterObjectTests {

        @Test
        @DisplayName("Doit filtrer les valeurs selon une condition")
        void shouldFilterValuesAccordingToPredicate() {

            Map<String, Integer> inventory = Map.of(
                    "laptop", 0,
                    "smartphone", 5,
                    "tablet", 0,
                    "headphones", 8
            );

            Map<String, Integer> result =
                    objectUtilsService.filterObject(
                            inventory,
                            stock -> stock == 0
                    );

            assertEquals(2, result.size());

            assertTrue(result.containsKey("laptop"));
            assertTrue(result.containsKey("tablet"));
        }

        @Test
        @DisplayName("Doit retourner une map vide si aucune valeur ne correspond")
        void shouldReturnEmptyMapWhenNoValuesMatch() {

            Map<String, Integer> inventory = Map.of(
                    "smartphone", 5,
                    "headphones", 8
            );

            Map<String, Integer> result =
                    objectUtilsService.filterObject(
                            inventory,
                            stock -> stock == 0
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une map vide")
        void shouldReturnEmptyMapForEmptyInput() {

            Map<Object, Object> result =
                    objectUtilsService.filterObject(
                            Collections.emptyMap(),
                            value -> true
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une valeur null")
        void shouldReturnEmptyMapForNullInput() {

            Map<Object, Object> result =
                    objectUtilsService.filterObject(
                            null,
                            value -> true
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour un prédicat null")
        void shouldReturnEmptyMapForNullPredicate() {

            Map<String, Integer> inventory = Map.of(
                    "laptop", 0
            );

            Map<String, Integer> result =
                    objectUtilsService.filterObject(
                            inventory,
                            null
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de flatToNested")
    class FlatToNestedTests {

        @Test
        @SuppressWarnings("unchecked")
        @DisplayName("Doit convertir une map plate en structure imbriquée")
        void shouldConvertFlatMapToNestedStructure() {

            Map<String, Object> flatMap = Map.of(
                    "app.name", "MyApp",
                    "app.version", "1.0.0",
                    "database.host", "localhost",
                    "database.port", 5432
            );

            Map<String, Object> result =
                    objectUtilsService.flatToNested(flatMap);

            Map<String, Object> app =
                    (Map<String, Object>) result.get("app");

            Map<String, Object> database =
                    (Map<String, Object>) result.get("database");

            assertEquals("MyApp", app.get("name"));
            assertEquals("1.0.0", app.get("version"));

            assertEquals("localhost", database.get("host"));
            assertEquals(5432, database.get("port"));
        }

        @Test
        @SuppressWarnings("unchecked")
        @DisplayName("Doit gérer plusieurs niveaux d'imbrication")
        void shouldHandleMultipleNestedLevels() {

            Map<String, Object> flatMap = Map.of(
                    "app.settings.theme", "dark"
            );

            Map<String, Object> result =
                    objectUtilsService.flatToNested(flatMap);

            Map<String, Object> app =
                    (Map<String, Object>) result.get("app");

            Map<String, Object> settings =
                    (Map<String, Object>) app.get("settings");

            assertEquals("dark", settings.get("theme"));
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une map vide")
        void shouldReturnEmptyMapForEmptyInput() {

            Map<String, Object> result =
                    objectUtilsService.flatToNested(
                            Collections.emptyMap()
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une valeur null")
        void shouldReturnEmptyMapForNullInput() {

            Map<String, Object> result =
                    objectUtilsService.flatToNested(null);

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de findKeysByValue")
    class FindKeysByValueTests {

        @Test
        @DisplayName("Doit retourner les clés correspondant à la valeur recherchée")
        void shouldReturnMatchingKeys() {

            Map<String, Integer> inventory = Map.of(
                    "laptop", 0,
                    "mouse", 5,
                    "keyboard", 0,
                    "monitor", 3
            );

            List<String> result =
                    objectUtilsService.findKeysByValue(
                            inventory,
                            0
                    );

            assertEquals(2, result.size());

            assertTrue(result.contains("laptop"));
            assertTrue(result.contains("keyboard"));
        }

        @Test
        @DisplayName("Doit retourner une liste vide si aucune valeur ne correspond")
        void shouldReturnEmptyListWhenNoMatchExists() {

            Map<String, Integer> inventory = Map.of(
                    "mouse", 5,
                    "monitor", 3
            );

            List<String> result =
                    objectUtilsService.findKeysByValue(
                            inventory,
                            0
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une liste vide pour une map vide")
        void shouldReturnEmptyListForEmptyMap() {

            List<Object> result =
                    objectUtilsService.findKeysByValue(
                            Collections.emptyMap(),
                            0
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une liste vide pour une map null")
        void shouldReturnEmptyListForNullMap() {

            List<Object> result =
                    objectUtilsService.findKeysByValue(
                            null,
                            0
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de createObjectFromArrays")
    class CreateObjectFromArraysTests {

        @Test
        @DisplayName("Doit créer un objet à partir de deux listes")
        void shouldCreateObjectFromLists() {

            List<String> keys = List.of(
                    "Alice",
                    "Bob",
                    "Charlie"
            );

            List<Object> values = List.of(
                    100,
                    85,
                    90
            );

            Map<String, Object> result =
                    objectUtilsService.createObjectFromArrays(
                            keys,
                            values
                    );

            assertEquals(100, result.get("Alice"));
            assertEquals(85, result.get("Bob"));
            assertEquals(90, result.get("Charlie"));
        }

        @Test
        @DisplayName("Doit lever une exception si les tailles diffèrent")
        void shouldThrowExceptionWhenSizesDiffer() {

            List<String> keys = List.of(
                    "Alice",
                    "Bob"
            );

            List<Object> values = List.of(
                    100
            );

            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> objectUtilsService.createObjectFromArrays(
                                    keys,
                                    values
                            )
                    );

            assertEquals(
                    "Les listes de clés et valeurs doivent avoir la même taille",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Doit retourner une map vide pour des listes vides")
        void shouldReturnEmptyMapForEmptyLists() {

            Map<String, Object> result =
                    objectUtilsService.createObjectFromArrays(
                            Collections.emptyList(),
                            Collections.emptyList()
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour des listes null")
        void shouldReturnEmptyMapForNullLists() {

            Map<String, Object> result =
                    objectUtilsService.createObjectFromArrays(
                            null,
                            null
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de countValues")
    class CountValuesTests {

        @Test
        @DisplayName("Doit compter les occurrences des valeurs")
        void shouldCountValueOccurrences() {

            Map<String, String> orderStatuses = Map.of(
                    "order1", "pending",
                    "order2", "delivered",
                    "order3", "pending",
                    "order4", "cancelled",
                    "order5", "pending"
            );

            Map<String, Long> result =
                    objectUtilsService.countValues(
                            orderStatuses
                    );

            assertEquals(3L, result.get("pending"));
            assertEquals(1L, result.get("delivered"));
            assertEquals(1L, result.get("cancelled"));
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une map vide")
        void shouldReturnEmptyMapForEmptyInput() {

            Map<Object, Long> result =
                    objectUtilsService.countValues(
                            Collections.emptyMap()
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une valeur null")
        void shouldReturnEmptyMapForNullInput() {

            Map<Object, Long> result =
                    objectUtilsService.countValues(
                            null
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit gérer différents types de valeurs")
        void shouldHandleDifferentValueTypes() {

            Map<String, Object> values = Map.of(
                    "a", true,
                    "b", true,
                    "c", 10,
                    "d", 10
            );

            Map<Object, Long> result =
                    objectUtilsService.countValues(values);

            assertEquals(2L, result.get(true));
            assertEquals(2L, result.get(10));
        }
    }

    @Nested
    @DisplayName("Tests de extractProperties")
    class ExtractPropertiesTests {

        @Test
        @DisplayName("Doit extraire uniquement les propriétés demandées")
        void shouldExtractRequestedProperties() {

            Map<String, Object> userProfile = Map.of(
                    "name", "Jean Martin",
                    "email", "jean@email.com",
                    "password", "secret123",
                    "age", 35
            );

            List<String> properties = List.of(
                    "name",
                    "age"
            );

            Map<String, Object> result =
                    objectUtilsService.extractProperties(
                            userProfile,
                            properties
                    );

            assertEquals(2, result.size());

            assertEquals(
                    "Jean Martin",
                    result.get("name")
            );

            assertEquals(
                    35,
                    result.get("age")
            );
        }

        @Test
        @DisplayName("Doit ignorer les propriétés absentes")
        void shouldIgnoreMissingProperties() {

            Map<String, Object> userProfile = Map.of(
                    "name", "Jean Martin"
            );

            List<String> properties = List.of(
                    "name",
                    "age"
            );

            Map<String, Object> result =
                    objectUtilsService.extractProperties(
                            userProfile,
                            properties
                    );

            assertEquals(1, result.size());

            assertFalse(result.containsKey("age"));
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une map vide")
        void shouldReturnEmptyMapForEmptyInput() {

            Map<String, Object> result =
                    objectUtilsService.extractProperties(
                            Collections.emptyMap(),
                            List.of("name")
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une liste vide")
        void shouldReturnEmptyMapForEmptyProperties() {

            Map<String, Object> input = Map.of(
                    "name", "Jean Martin"
            );

            Map<String, Object> result =
                    objectUtilsService.extractProperties(
                            input,
                            Collections.emptyList()
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour des valeurs null")
        void shouldReturnEmptyMapForNullValues() {

            Map<String, Object> result =
                    objectUtilsService.extractProperties(
                            null,
                            null
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de sortObjectByValue")
    class SortObjectByValueTests {

        @Test
        @DisplayName("Doit trier les valeurs par ordre croissant")
        void shouldSortValuesInAscendingOrder() {

            Map<String, Integer> playerScores = Map.of(
                    "Alice", 85,
                    "Bob", 92,
                    "Charlie", 78,
                    "David", 95
            );

            Map<String, Integer> result =
                    objectUtilsService.sortObjectByValue(
                            playerScores
                    );

            List<Integer> sortedValues =
                    new ArrayList<>(result.values());

            assertEquals(
                    List.of(78, 85, 92, 95),
                    sortedValues
            );
        }

        @Test
        @DisplayName("Doit conserver les clés associées")
        void shouldPreserveAssociatedKeys() {

            Map<String, Integer> playerScores = Map.of(
                    "Alice", 85,
                    "Bob", 92
            );

            Map<String, Integer> result =
                    objectUtilsService.sortObjectByValue(
                            playerScores
                    );

            assertEquals(85, result.get("Alice"));
            assertEquals(92, result.get("Bob"));
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une map vide")
        void shouldReturnEmptyMapForEmptyInput() {

            // Explicit generic types required here:
            // without them, Collections.emptyMap() is inferred
            // as Map<Object, Object>, which breaks generic constraints.
            Map<String, Integer> result =
                    objectUtilsService.sortObjectByValue(
                            Collections.<String, Integer>emptyMap()
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une valeur null")
        void shouldReturnEmptyMapForNullInput() {

            Map<String, Integer> result =
                    objectUtilsService.sortObjectByValue(
                            null
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de findMaxValue")
    class FindMaxValueTests {

        @Test
        @DisplayName("Doit retourner la valeur maximale")
        void shouldReturnMaximumValue() {

            Map<String, Integer> gameScores = Map.of(
                    "level1", 850,
                    "level2", 920,
                    "level3", 880,
                    "level4", 1020
            );

            Integer result =
                    objectUtilsService.findMaxValue(
                            gameScores
                    );

            assertEquals(1020, result);
        }

        @Test
        @DisplayName("Doit gérer les valeurs décimales")
        void shouldHandleDecimalValues() {

            Map<String, Double> prices = Map.of(
                    "book", 10.5,
                    "pen", 2.2,
                    "notebook", 15.8
            );

            Double result =
                    objectUtilsService.findMaxValue(
                            prices
                    );

            assertEquals(15.8, result);
        }

        @Test
        @DisplayName("Doit retourner null pour une map vide")
        void shouldReturnNullForEmptyMap() {

            Integer result =
                    objectUtilsService.findMaxValue(
                            Collections.<String, Integer>emptyMap()
                    );

            assertNull(result);
        }

        @Test
        @DisplayName("Doit retourner null pour une valeur null")
        void shouldReturnNullForNullInput() {

            Integer result =
                    objectUtilsService.findMaxValue(
                            null
                    );

            assertNull(result);
        }
    }

    @Nested
    @DisplayName("Tests de createObjectFromPairs")
    class CreateObjectFromPairsTests {

        @Test
        @DisplayName("Doit créer un objet à partir de paires clé-valeur")
        void shouldCreateObjectFromPairs() {

            List<List<Object>> pairs = List.of(
                    List.of("pommes", 2.5),
                    List.of("bananes", 1.8),
                    List.of("oranges", 2.2)
            );

            Map<String, Object> result =
                    objectUtilsService.createObjectFromPairs(
                            pairs
                    );

            assertEquals(2.5, result.get("pommes"));
            assertEquals(1.8, result.get("bananes"));
            assertEquals(2.2, result.get("oranges"));
        }

        @Test
        @DisplayName("Doit lever une exception pour une paire invalide")
        void shouldThrowExceptionForInvalidPair() {

            List<List<Object>> pairs = List.of(
                    List.of("pommes")
            );

            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> objectUtilsService.createObjectFromPairs(
                                    pairs
                            )
                    );

            assertEquals(
                    "Chaque paire doit contenir exactement 2 éléments",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Doit lever une exception pour une clé invalide")
        void shouldThrowExceptionForInvalidKey() {

            List<List<Object>> pairs = List.of(
                    List.of("", 2.5)
            );

            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> objectUtilsService.createObjectFromPairs(
                                    pairs
                            )
                    );

            assertEquals(
                    "Chaque clé doit être une chaîne valide",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une liste vide")
        void shouldReturnEmptyMapForEmptyInput() {

            Map<String, Object> result =
                    objectUtilsService.createObjectFromPairs(
                            Collections.emptyList()
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une valeur null")
        void shouldReturnEmptyMapForNullInput() {

            Map<String, Object> result =
                    objectUtilsService.createObjectFromPairs(
                            null
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de findValueInObject")
    class FindValueInObjectTests {

        @Test
        @DisplayName("Doit trouver une valeur dans un objet imbriqué")
        void shouldFindValueInNestedObject() {

            Map<String, Object> config = Map.of(
                    "app", Map.of(
                            "name", "MonApp",
                            "settings", Map.of(
                                    "theme", "dark",
                                    "notifications", Map.of(
                                            "email", true,
                                            "push", false
                                    )
                            )
                    )
            );

            List<String> result =
                    objectUtilsService.findValueInObject(
                            config,
                            "dark"
                    );

            assertEquals(
                    List.of(
                            "app",
                            "settings",
                            "theme"
                    ),
                    result
            );
        }

        @Test
        @DisplayName("Doit retourner une liste vide si la valeur n'existe pas")
        void shouldReturnEmptyListWhenValueDoesNotExist() {

            Map<String, Object> config = Map.of(
                    "theme", "dark"
            );

            List<String> result =
                    objectUtilsService.findValueInObject(
                            config,
                            "light"
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une liste vide pour une map vide")
        void shouldReturnEmptyListForEmptyInput() {

            List<String> result =
                    objectUtilsService.findValueInObject(
                            Collections.emptyMap(),
                            "dark"
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une liste vide pour une valeur null")
        void shouldReturnEmptyListForNullInput() {

            List<String> result =
                    objectUtilsService.findValueInObject(
                            null,
                            "dark"
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de groupByProperty")
    class GroupByPropertyTests {

        @Test
        @DisplayName("Doit grouper les objets par propriété")
        void shouldGroupObjectsByProperty() {

            List<Map<String, Object>> students = List.of(
                    Map.of(
                            "name", "Alice",
                            "level", "Débutant"
                    ),
                    Map.of(
                            "name", "Bob",
                            "level", "Intermédiaire"
                    ),
                    Map.of(
                            "name", "Charlie",
                            "level", "Débutant"
                    )
            );

            Map<Object, List<Map<String, Object>>> result =
                    objectUtilsService.groupByProperty(
                            students,
                            "level"
                    );

            assertEquals(
                    2,
                    result.get("Débutant").size()
            );

            assertEquals(
                    1,
                    result.get("Intermédiaire").size()
            );
        }

        @Test
        @DisplayName("Doit ignorer les objets sans propriété")
        void shouldIgnoreObjectsWithoutProperty() {

            List<Map<String, Object>> students = List.of(
                    Map.of(
                            "name", "Alice",
                            "level", "Débutant"
                    ),
                    Map.of(
                            "name", "Bob"
                    )
            );

            Map<Object, List<Map<String, Object>>> result =
                    objectUtilsService.groupByProperty(
                            students,
                            "level"
                    );

            assertEquals(
                    1,
                    result.get("Débutant").size()
            );
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une liste vide")
        void shouldReturnEmptyMapForEmptyInput() {

            Map<Object, List<Map<String, Object>>> result =
                    objectUtilsService.groupByProperty(
                            Collections.emptyList(),
                            "level"
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour une propriété vide")
        void shouldReturnEmptyMapForBlankProperty() {

            List<Map<String, Object>> students = List.of(
                    Map.of("name", "Alice")
            );

            Map<Object, List<Map<String, Object>>> result =
                    objectUtilsService.groupByProperty(
                            students,
                            " "
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une map vide pour des valeurs null")
        void shouldReturnEmptyMapForNullValues() {

            Map<Object, List<Map<String, Object>>> result =
                    objectUtilsService.groupByProperty(
                            null,
                            null
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de validateObject")
    class ValidateObjectTests {

        @Test
        @DisplayName("Doit valider un objet conforme")
        void shouldValidateMatchingObject() {

            Map<String, Object> userInput = Map.of(
                    "name", "Marie",
                    "age", 25,
                    "email", "marie@email.com"
            );

            Map<String, String> schema = Map.of(
                    "name", "string",
                    "age", "number",
                    "email", "string"
            );

            boolean result =
                    objectUtilsService.validateObject(
                            userInput,
                            schema
                    );

            assertTrue(result);
        }

        @Test
        @DisplayName("Doit refuser un type invalide")
        void shouldRejectInvalidType() {

            Map<String, Object> userInput = Map.of(
                    "name", "Marie",
                    "age", "25"
            );

            Map<String, String> schema = Map.of(
                    "name", "string",
                    "age", "number"
            );

            boolean result =
                    objectUtilsService.validateObject(
                            userInput,
                            schema
                    );

            assertFalse(result);
        }

        @Test
        @DisplayName("Doit refuser une propriété manquante")
        void shouldRejectMissingProperty() {

            Map<String, Object> userInput = Map.of(
                    "name", "Marie"
            );

            Map<String, String> schema = Map.of(
                    "name", "string",
                    "age", "number"
            );

            boolean result =
                    objectUtilsService.validateObject(
                            userInput,
                            schema
                    );

            assertFalse(result);
        }

        @Test
        @DisplayName("Doit retourner false pour une map vide")
        void shouldReturnFalseForEmptyInput() {

            boolean result =
                    objectUtilsService.validateObject(
                            Collections.emptyMap(),
                            Collections.emptyMap()
                    );

            assertFalse(result);
        }

        @Test
        @DisplayName("Doit retourner false pour des valeurs null")
        void shouldReturnFalseForNullValues() {

            boolean result =
                    objectUtilsService.validateObject(
                            null,
                            null
                    );

            assertFalse(result);
        }
    }

    @Nested
    @DisplayName("Tests de compareDifferences")
    class CompareDifferencesTests {

        @Test
        @DisplayName("Doit détecter les propriétés ajoutées")
        void shouldDetectAddedProperties() {

            Map<String, Object> oldProfile = Map.of(
                    "name", "Jean"
            );

            Map<String, Object> newProfile = Map.of(
                    "name", "Jean",
                    "phone", "0123456789"
            );

            Map<String, DifferenceDetailResponse> result =
                    objectUtilsService.compareDifferences(
                            oldProfile,
                            newProfile
                    );

            assertEquals(
                    "added",
                    result.get("phone").getType()
            );
        }

        @Test
        @DisplayName("Doit détecter les propriétés supprimées")
        void shouldDetectRemovedProperties() {

            Map<String, Object> oldProfile = Map.of(
                    "name", "Jean",
                    "phone", "0123456789"
            );

            Map<String, Object> newProfile = Map.of(
                    "name", "Jean"
            );

            Map<String, DifferenceDetailResponse> result =
                    objectUtilsService.compareDifferences(
                            oldProfile,
                            newProfile
                    );

            assertEquals(
                    "removed",
                    result.get("phone").getType()
            );
        }

        @Test
        @DisplayName("Doit détecter les propriétés modifiées")
        void shouldDetectModifiedProperties() {

            Map<String, Object> oldProfile = Map.of(
                    "age", 30
            );

            Map<String, Object> newProfile = Map.of(
                    "age", 31
            );

            Map<String, DifferenceDetailResponse> result =
                    objectUtilsService.compareDifferences(
                            oldProfile,
                            newProfile
                    );

            assertEquals(
                    "modified",
                    result.get("age").getType()
            );
        }

        @Test
        @DisplayName("Ne doit retourner aucune différence pour des objets identiques")
        void shouldReturnNoDifferencesForIdenticalObjects() {

            Map<String, Object> oldProfile = Map.of(
                    "name", "Jean"
            );

            Map<String, Object> newProfile = Map.of(
                    "name", "Jean"
            );

            Map<String, DifferenceDetailResponse> result =
                    objectUtilsService.compareDifferences(
                            oldProfile,
                            newProfile
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit gérer des valeurs null")
        void shouldHandleNullValues() {

            Map<String, DifferenceDetailResponse> result =
                    objectUtilsService.compareDifferences(
                            null,
                            null
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de objectToUrlParams")
    class ObjectToUrlParamsTests {

        @Test
        @DisplayName("Doit convertir un objet en paramètres d'URL")
        void shouldConvertObjectToUrlParams() {

            Map<String, Object> searchParams = Map.of(
                    "query", "ordinateur portable",
                    "maxPrice", 1000,
                    "brand", "Dell",
                    "inStock", true
            );

            String result =
                    objectUtilsService.objectToUrlParams(
                            searchParams
                    );

            assertTrue(
                    result.contains(
                            "query=ordinateur+portable"
                    )
            );

            assertTrue(
                    result.contains(
                            "maxPrice=1000"
                    )
            );

            assertTrue(
                    result.contains(
                            "brand=Dell"
                    )
            );

            assertTrue(
                    result.contains(
                            "inStock=true"
                    )
            );
        }

        @Test
        @DisplayName("Doit encoder les caractères spéciaux")
        void shouldEncodeSpecialCharacters() {

            Map<String, Object> params = Map.of(
                    "city", "Montréal"
            );

            String result =
                    objectUtilsService.objectToUrlParams(
                            params
                    );

            assertTrue(
                    result.contains(
                            "Montr%C3%A9al"
                    )
            );
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une map vide")
        void shouldReturnEmptyStringForEmptyInput() {

            String result =
                    objectUtilsService.objectToUrlParams(
                            Collections.emptyMap()
                    );

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une chaîne vide pour une valeur null")
        void shouldReturnEmptyStringForNullInput() {

            String result =
                    objectUtilsService.objectToUrlParams(
                            null
                    );

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests de getObjectStats")
    class GetObjectStatsTests {

        @Test
        @DisplayName("Doit générer les statistiques correctement")
        void shouldGenerateStatisticsCorrectly() {

            Map<String, Number> revenues = Map.of(
                    "january", 1000,
                    "february", 1200,
                    "march", 900,
                    "april", 1500
            );

            ObjectStatsResponse result =
                    objectUtilsService.getObjectStats(
                            revenues
                    );

            assertEquals(
                    900,
                    result.getBasic().getMin()
            );

            assertEquals(
                    1500,
                    result.getBasic().getMax()
            );

            assertEquals(
                    4600,
                    result.getBasic().getTotal()
            );

            assertEquals(
                    1150,
                    result.getBasic().getAverage()
            );

            assertEquals(
                    1100,
                    result.getAdvanced().getMedian()
            );
        }

        @Test
        @DisplayName("Doit gérer les nombres décimaux")
        void shouldHandleDecimalValues() {

            Map<String, Number> prices = Map.of(
                    "a", 10.5,
                    "b", 15.5,
                    "c", 20.0
            );

            ObjectStatsResponse result =
                    objectUtilsService.getObjectStats(
                            prices
                    );

            assertEquals(
                    10.5,
                    result.getBasic().getMin()
            );

            assertEquals(
                    20.0,
                    result.getBasic().getMax()
            );
        }

        @Test
        @DisplayName("Doit lever une exception pour une map vide")
        void shouldThrowExceptionForEmptyInput() {

            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> objectUtilsService.getObjectStats(
                                    Collections.emptyMap()
                            )
                    );

            assertEquals(
                    "L'objet statistique ne peut pas être vide",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Doit lever une exception pour une valeur null")
        void shouldThrowExceptionForNullInput() {

            IllegalArgumentException exception =
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> objectUtilsService.getObjectStats(
                                    null
                            )
                    );

            assertEquals(
                    "L'objet statistique ne peut pas être vide",
                    exception.getMessage()
            );
        }
    }
}