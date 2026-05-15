package com.fred.logictoolbox.service.objectutils;

import com.fred.logictoolbox.model.payload.response.DifferenceDetailResponse;
import com.fred.logictoolbox.model.payload.response.objectStatsResponse.AdvancedStatsResponse;
import com.fred.logictoolbox.model.payload.response.objectStatsResponse.BasicStatsResponse;
import com.fred.logictoolbox.model.payload.response.objectStatsResponse.ObjectStatsResponse;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Service containing utility methods
 * for object and map manipulations.
 */
@Service
public class ObjectUtilsService {

    /**
     * Extracts all values from a map.
     *
     * @param inputMap the map to analyze
     * @param <K>      the key type
     * @param <V>      the value type
     * @return a list containing all map values,
     * or an empty list if the map is null or empty
     */
    public <K, V> List<V> getValues(Map<K, V> inputMap) {

        if (inputMap == null || inputMap.isEmpty()) {
            return Collections.emptyList();
        }

        return new ArrayList<>(inputMap.values());
    }

    /**
     * Transforms all values of a map using a transformation function.
     *
     * @param inputMap    the map to transform
     * @param transformer the transformation function
     * @param <K>         the key type
     * @param <V>         the original value type
     * @param <R>         the transformed value type
     * @return a new map containing transformed values,
     * or an empty map if the input is null or empty
     */
    public <K, V, R> Map<K, R> transformValues(
            Map<K, V> inputMap,
            Function<V, R> transformer
    ) {

        if (inputMap == null
                || inputMap.isEmpty()
                || transformer == null
        ) {
            return Collections.emptyMap();
        }

        return inputMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> transformer.apply(entry.getValue())
                ));
    }

    /**
     * Merges two maps by summing numeric values sharing the same keys.
     *
     * @param firstMap  the first map
     * @param secondMap the second map
     * @return a merged map containing summed numeric values,
     * or an empty map if both maps are null or empty
     */
    public Map<String, Number> mergeObjects(
            Map<String, Number> firstMap,
            Map<String, Number> secondMap
    ) {

        if ((firstMap == null || firstMap.isEmpty())
                && (secondMap == null || secondMap.isEmpty())
        ) {
            return Collections.emptyMap();
        }

        Map<String, Double> temporaryResult = new HashMap<>();

        if (firstMap != null) {

            firstMap.forEach(
                    (key, value) ->
                            temporaryResult.put(
                                    key,
                                    value.doubleValue()
                            )
            );
        }

        if (secondMap != null) {

            secondMap.forEach(
                    (key, value) ->
                            temporaryResult.merge(
                                    key,
                                    value.doubleValue(),
                                    Double::sum
                            )
            );
        }

        Map<String, Number> normalizedResult = new HashMap<>();

        temporaryResult.forEach((key, value) -> {

            if (value % 1 == 0) {

                normalizedResult.put(
                        key,
                        value.intValue()
                );

            } else {

                normalizedResult.put(
                        key,
                        value
                );
            }
        });

        return normalizedResult;
    }

    /**
     * Filters a map according to a value predicate.
     *
     * @param inputMap  the map to filter
     * @param predicate the filtering condition
     * @param <K>       the key type
     * @param <V>       the value type
     * @return a filtered map,
     * or an empty map if the input is null or empty
     */
    public <K, V> Map<K, V> filterObject(
            Map<K, V> inputMap,
            Predicate<V> predicate
    ) {

        if (inputMap == null
                || inputMap.isEmpty()
                || predicate == null
        ) {
            return Collections.emptyMap();
        }

        return inputMap.entrySet()
                .stream()
                .filter(
                        entry -> predicate.test(entry.getValue())
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    /**
     * Converts a flat map into a nested map structure
     * using dots as path separators.
     *
     * @param flatMap the flat map to convert
     * @return a nested map structure,
     * or an empty map if the input is null or empty
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> flatToNested(
            Map<String, Object> flatMap
    ) {

        if (flatMap == null || flatMap.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, Object> result = new HashMap<>();

        flatMap.forEach((key, value) -> {

            String[] parts = key.split("\\.");

            Map<String, Object> currentLevel = result;

            for (int index = 0; index < parts.length - 1; index++) {

                String currentPart = parts[index];

                currentLevel = (Map<String, Object>)
                        currentLevel.computeIfAbsent(
                                currentPart,
                                ignored -> new HashMap<>()
                        );
            }

            currentLevel.put(
                    parts[parts.length - 1],
                    value
            );
        });

        return result;
    }

    /**
     * Finds all keys associated with a specific value.
     *
     * @param inputMap      the map to analyze
     * @param searchedValue the value to search for
     * @param <K>           the key type
     * @param <V>           the value type
     * @return a list containing matching keys,
     * or an empty list if the map is null or empty
     */
    public <K, V> List<K> findKeysByValue(
            Map<K, V> inputMap,
            V searchedValue
    ) {

        if (inputMap == null || inputMap.isEmpty()) {
            return Collections.emptyList();
        }

        return inputMap.entrySet()
                .stream()
                .filter(entry ->
                        Objects.equals(
                                entry.getValue(),
                                searchedValue
                        )
                )
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * Creates a map from two lists representing keys and values.
     *
     * @param keys   the list of keys
     * @param values the list of values
     * @return a map created from matching indexes
     * @throws IllegalArgumentException if list sizes differ
     */
    public Map<String, Object> createObjectFromArrays(
            List<String> keys,
            List<Object> values
    ) {

        if (keys == null
                || values == null
                || keys.isEmpty()
                || values.isEmpty()
        ) {
            return Collections.emptyMap();
        }

        if (keys.size() != values.size()) {

            throw new IllegalArgumentException(
                    "Les listes de clés et valeurs doivent avoir la même taille"
            );
        }

        Map<String, Object> result = new HashMap<>();

        for (int index = 0; index < keys.size(); index++) {

            result.put(
                    keys.get(index),
                    values.get(index)
            );
        }

        return result;
    }

    /**
     * Counts occurrences of values inside a map.
     *
     * @param inputMap the map to analyze
     * @param <K>      the key type
     * @param <V>      the value type
     * @return a map containing occurrence counts for each value,
     * or an empty map if the input is null or empty
     */
    public <K, V> Map<V, Long> countValues(
            Map<K, V> inputMap
    ) {

        if (inputMap == null || inputMap.isEmpty()) {
            return Collections.emptyMap();
        }

        return inputMap.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }

    /**
     * Extracts selected properties from a map.
     *
     * @param inputMap   the source map
     * @param properties the properties to extract
     * @return a filtered map containing only requested properties,
     * or an empty map if the input is null or empty
     */
    public Map<String, Object> extractProperties(
            Map<String, Object> inputMap,
            List<String> properties
    ) {

        if (inputMap == null
                || inputMap.isEmpty()
                || properties == null
                || properties.isEmpty()
        ) {
            return Collections.emptyMap();
        }

        Map<String, Object> result = new HashMap<>();

        for (String property : properties) {

            if (inputMap.containsKey(property)) {

                result.put(
                        property,
                        inputMap.get(property)
                );
            }
        }

        return result;
    }

    /**
     * Sorts a map by its values in ascending order.
     *
     * <p>
     * This method supports any value type implementing
     * {@link Comparable}, not only numeric values.
     * </p>
     *
     * <p>
     * Examples of supported types:
     * </p>
     * <ul>
     *     <li>{@link Integer}</li>
     *     <li>{@link Double}</li>
     *     <li>{@link String}</li>
     *     <li>{@link java.time.LocalDate}</li>
     * </ul>
     *
     * @param inputMap the map to sort
     * @param <K>      the key type
     * @param <V>      the value type extending {@link Comparable}
     * @return a sorted map preserving insertion order,
     * or an empty map if the input is null or empty
     */
    public <K, V extends Comparable<V>> Map<K, V> sortObjectByValue(
            Map<K, V> inputMap
    ) {

        if (inputMap == null || inputMap.isEmpty()) {
            return Collections.emptyMap();
        }

        return inputMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    /**
     * Finds the maximum value in a numeric map.
     *
     * <p>
     * This method supports any numeric type implementing
     * both {@link Number} and {@link Comparable},
     * not only {@link Integer}.
     * </p>
     *
     * <p>
     * Examples of supported types:
     * </p>
     * <ul>
     *     <li>{@link Integer}</li>
     *     <li>{@link Double}</li>
     *     <li>{@link Long}</li>
     *     <li>{@link Float}</li>
     * </ul>
     *
     * @param inputMap the map to analyze
     * @param <K>      the key type
     * @param <V>      the numeric comparable value type
     * @return the maximum value,
     * or {@code null} if the map is null or empty
     */
    public <K, V extends Number & Comparable<V>> V findMaxValue(
            Map<K, V> inputMap
    ) {

        if (inputMap == null || inputMap.isEmpty()) {
            return null;
        }

        return Collections.max(
                inputMap.values()
        );
    }

    /**
     * Creates a map from key-value pairs.
     *
     * <p>
     * Each pair must contain exactly:
     * </p>
     * <ul>
     *     <li>a key at index 0</li>
     *     <li>a value at index 1</li>
     * </ul>
     *
     * @param pairs the list of key-value pairs
     * @return a created map,
     * or an empty map if the input is null or empty
     * @throws IllegalArgumentException if a pair is invalid
     */
    public Map<String, Object> createObjectFromPairs(
            List<List<Object>> pairs
    ) {

        if (pairs == null || pairs.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, Object> result = new HashMap<>();

        for (List<Object> pair : pairs) {

            if (pair == null || pair.size() != 2) {

                throw new IllegalArgumentException(
                        "Chaque paire doit contenir exactement 2 éléments"
                );
            }

            Object key = pair.get(0);

            if (!(key instanceof String stringKey)
                    || stringKey.isBlank()
            ) {

                throw new IllegalArgumentException(
                        "Chaque clé doit être une chaîne valide"
                );
            }

            result.put(
                    stringKey,
                    pair.get(1)
            );
        }

        return result;
    }

    /**
     * Searches for a value inside a nested map structure.
     *
     * <p>
     * Returns the path leading to the searched value.
     * </p>
     *
     * @param inputMap      the nested map to analyze
     * @param searchedValue the value to search for
     * @return the path to the value,
     * or an empty list if the value is not found
     */
    public List<String> findValueInObject(
            Map<String, Object> inputMap,
            Object searchedValue
    ) {

        if (inputMap == null || inputMap.isEmpty()) {
            return Collections.emptyList();
        }

        return findValueRecursively(
                inputMap,
                searchedValue,
                new ArrayList<>()
        );
    }

    @SuppressWarnings("unchecked")
    private List<String> findValueRecursively(
            Map<String, Object> currentMap,
            Object searchedValue,
            List<String> currentPath
    ) {

        for (Map.Entry<String, Object> entry
                : currentMap.entrySet()) {

            List<String> updatedPath =
                    new ArrayList<>(currentPath);

            updatedPath.add(entry.getKey());

            Object currentValue = entry.getValue();

            if (Objects.equals(
                    currentValue,
                    searchedValue
            )) {

                return updatedPath;
            }

            if (currentValue instanceof Map<?, ?> nestedMap) {

                List<String> result =
                        findValueRecursively(
                                (Map<String, Object>) nestedMap,
                                searchedValue,
                                updatedPath
                        );

                if (!result.isEmpty()) {
                    return result;
                }
            }
        }

        return Collections.emptyList();
    }

    /**
     * Groups objects by a specific property.
     *
     * @param objects  the objects to group
     * @param property the grouping property
     * @return grouped objects by property value,
     * or an empty map if the input is null or empty
     */
    public Map<Object, List<Map<String, Object>>> groupByProperty(
            List<Map<String, Object>> objects,
            String property
    ) {

        if (objects == null
                || objects.isEmpty()
                || property == null
                || property.isBlank()
        ) {
            return Collections.emptyMap();
        }

        return objects.stream()
                .filter(object -> object.containsKey(property))
                .collect(Collectors.groupingBy(
                        object -> object.get(property)
                ));
    }

    /**
     * Validates whether an object matches a schema definition.
     *
     * <p>
     * Supported schema types:
     * </p>
     * <ul>
     *     <li>string</li>
     *     <li>number</li>
     *     <li>boolean</li>
     *     <li>object</li>
     *     <li>array</li>
     * </ul>
     *
     * @param object the object to validate
     * @param schema the schema definition
     * @return true if the object matches the schema,
     * false otherwise
     */
    public boolean validateObject(
            Map<String, Object> object,
            Map<String, String> schema
    ) {

        if (object == null
                || schema == null
                || object.isEmpty()
                || schema.isEmpty()
        ) {
            return false;
        }

        for (Map.Entry<String, String> entry
                : schema.entrySet()) {

            String key = entry.getKey();
            String expectedType = entry.getValue();

            if (!object.containsKey(key)) {
                return false;
            }

            Object value = object.get(key);

            if (!matchesType(value, expectedType)) {
                return false;
            }
        }

        return true;
    }

    private boolean matchesType(
            Object value,
            String expectedType
    ) {

        return switch (expectedType.toLowerCase()) {

            case "string" -> value instanceof String;

            case "number" -> value instanceof Number;

            case "boolean" -> value instanceof Boolean;

            case "object" -> value instanceof Map<?, ?>;

            case "array" -> value instanceof List<?>;

            default -> false;
        };
    }

    /**
     * Compares differences between two objects.
     *
     * <p>
     * Supported difference types:
     * </p>
     * <ul>
     *     <li>added</li>
     *     <li>removed</li>
     *     <li>modified</li>
     * </ul>
     *
     * @param oldObject the original object
     * @param newObject the updated object
     * @return detected differences
     */
    public Map<String, DifferenceDetailResponse> compareDifferences(
            Map<String, Object> oldObject,
            Map<String, Object> newObject
    ) {

        Map<String, DifferenceDetailResponse> differences =
                new HashMap<>();

        Set<String> allKeys = new HashSet<>();

        if (oldObject != null) {
            allKeys.addAll(oldObject.keySet());
        }

        if (newObject != null) {
            allKeys.addAll(newObject.keySet());
        }

        for (String key : allKeys) {

            boolean existsInOld =
                    oldObject != null
                            && oldObject.containsKey(key);

            boolean existsInNew =
                    newObject != null
                            && newObject.containsKey(key);

            Object oldValue =
                    existsInOld
                            ? oldObject.get(key)
                            : null;

            Object newValue =
                    existsInNew
                            ? newObject.get(key)
                            : null;

            if (!existsInOld && existsInNew) {

                differences.put(
                        key,
                        new DifferenceDetailResponse(
                                "added",
                                null,
                                newValue
                        )
                );

            } else if (existsInOld && !existsInNew) {

                differences.put(
                        key,
                        new DifferenceDetailResponse(
                                "removed",
                                oldValue,
                                null
                        )
                );

            } else if (!Objects.equals(
                    oldValue,
                    newValue
            )) {

                differences.put(
                        key,
                        new DifferenceDetailResponse(
                                "modified",
                                oldValue,
                                newValue
                        )
                );
            }
        }

        return differences;
    }

    /**
     * Converts a map into URL query parameters.
     *
     * <p>
     * Keys and values are URL-encoded using UTF-8.
     * </p>
     *
     * <p>
     * Spaces are encoded as {@code +},
     * which is the standard behavior of
     * {@link URLEncoder} for query parameters.
     * </p>
     *
     * @param inputMap the map to convert
     * @return a URL query string,
     * or an empty string if the map is null or empty
     */
    public String objectToUrlParams(
            Map<String, Object> inputMap
    ) {

        if (inputMap == null || inputMap.isEmpty()) {
            return "";
        }

        return inputMap.entrySet()
                .stream()
                .map(entry ->
                        encode(entry.getKey())
                                + "="
                                + encode(
                                String.valueOf(
                                        entry.getValue()
                                )
                        )
                )
                .collect(Collectors.joining("&"));
    }

    private String encode(
            String value
    ) {

        return URLEncoder.encode(
                value,
                StandardCharsets.UTF_8
        );
    }

    /**
     * Generates statistical summaries for numeric maps.
     *
     * @param inputMap the numeric map to analyze
     * @return generated statistics
     * @throws IllegalArgumentException if the map is null or empty
     */
    public ObjectStatsResponse getObjectStats(
            Map<String, Number> inputMap
    ) {

        if (inputMap == null || inputMap.isEmpty()) {

            throw new IllegalArgumentException(
                    "L'objet statistique ne peut pas être vide"
            );
        }

        List<Double> values = inputMap.values()
                .stream()
                .map(Number::doubleValue)
                .sorted()
                .toList();

        double total = values.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        double average = total / values.size();

        double min = values.get(0);

        double max = values.get(values.size() - 1);

        double median = calculateMedian(values);

        double variance =
                calculateVariance(values, average);

        double standardDeviation =
                Math.sqrt(variance);

        BasicStatsResponse basic =
                new BasicStatsResponse();

        basic.setMin(min);
        basic.setMax(max);
        basic.setAverage(average);
        basic.setTotal(total);

        AdvancedStatsResponse advanced =
                new AdvancedStatsResponse();

        advanced.setMedian(median);
        advanced.setVariance(variance);
        advanced.setStandardDeviation(
                standardDeviation
        );

        ObjectStatsResponse response =
                new ObjectStatsResponse();

        response.setBasic(basic);
        response.setAdvanced(advanced);

        return response;
    }

    private double calculateMedian(
            List<Double> values
    ) {

        int size = values.size();

        if (size % 2 == 0) {

            return (
                    values.get(size / 2 - 1)
                            + values.get(size / 2)
            ) / 2;
        }

        return values.get(size / 2);
    }

    private double calculateVariance(
            List<Double> values,
            double average
    ) {

        return values.stream()
                .mapToDouble(value ->
                        Math.pow(
                                value - average,
                                2
                        )
                )
                .average()
                .orElse(0);
    }
}