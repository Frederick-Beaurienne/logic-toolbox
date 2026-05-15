package com.fred.logictoolbox.service.arrayobject;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service containing utility methods
 * for array object manipulations.
 */
@Service
public class ArrayObjectService {

    /**
     * Filters objects by property value.
     *
     * @param objects the objects to filter
     * @param property the property name
     * @param value the expected value
     * @return filtered objects
     */
    public List<Map<String, Object>> filterByProperty(
            List<Map<String, Object>> objects,
            String property,
            Object value
    ) {

        if (objects == null
                || objects.isEmpty()
                || property == null
                || property.isBlank()
        ) {

            return Collections.emptyList();
        }

        return objects.stream()
                .filter(object ->
                        Objects.equals(
                                object.get(property),
                                value
                        )
                )
                .toList();
    }

    /**
     * Groups objects by property value.
     *
     * @param objects the objects to group
     * @param property the grouping property
     * @return grouped objects
     */
    public Map<Object, List<Map<String, Object>>> groupBy(
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
                .filter(object ->
                        object.containsKey(property)
                )
                .collect(
                        Collectors.groupingBy(
                                object -> object.get(property)
                        )
                );
    }

    /**
     * Finds intersections between two object arrays.
     *
     * @param firstArray the first array
     * @param secondArray the second array
     * @param property the comparison property
     * @return intersected objects
     */
    public List<Map<String, Object>> findIntersection(
            List<Map<String, Object>> firstArray,
            List<Map<String, Object>> secondArray,
            String property
    ) {

        if (firstArray == null
                || secondArray == null
                || firstArray.isEmpty()
                || secondArray.isEmpty()
                || property == null
                || property.isBlank()
        ) {

            return Collections.emptyList();
        }

        Set<Object> secondArrayValues =
                secondArray.stream()
                        .map(object ->
                                object.get(property)
                        )
                        .collect(Collectors.toSet());

        return firstArray.stream()
                .filter(object ->
                        secondArrayValues.contains(
                                object.get(property)
                        )
                )
                .toList();
    }

    /**
     * Transforms object arrays using a custom mapping function.
     *
     * <p>
     * This method is intentionally generic and allows any kind
     * of object transformation logic through functional mapping.
     * </p>
     *
     * <p>
     * It can be used for:
     * </p>
     *
     * <ul>
     *     <li>field renaming</li>
     *     <li>data enrichment</li>
     *     <li>computed properties</li>
     *     <li>partial projections</li>
     *     <li>custom formatting</li>
     * </ul>
     *
     * @param objects the objects to transform
     * @param transformer the transformation function
     * @return transformed objects
     */
    public List<Map<String, Object>> transformArray(
            List<Map<String, Object>> objects,
            Function<Map<String, Object>, Map<String, Object>>
                    transformer
    ) {

        if (objects == null
                || objects.isEmpty()
                || transformer == null
        ) {

            return Collections.emptyList();
        }

        return objects.stream()
                .map(transformer)
                .toList();
    }

    /**
     * Aggregates numeric values by grouping property.
     *
     * @param objects the objects to aggregate
     * @param groupBy the grouping property
     * @param valueField the numeric value field
     * @return aggregated values
     */
    public Map<Object, Double> aggregateData(
            List<Map<String, Object>> objects,
            String groupBy,
            String valueField
    ) {

        if (objects == null
                || objects.isEmpty()
                || groupBy == null
                || groupBy.isBlank()
                || valueField == null
                || valueField.isBlank()
        ) {

            return Collections.emptyMap();
        }

        return objects.stream()
                .filter(object ->
                        object.get(valueField)
                                instanceof Number
                )
                .collect(
                        Collectors.groupingBy(
                                object -> object.get(groupBy),
                                Collectors.summingDouble(
                                        object ->
                                                ((Number) object.get(valueField))
                                                        .doubleValue()
                                )
                        )
                );
    }
}