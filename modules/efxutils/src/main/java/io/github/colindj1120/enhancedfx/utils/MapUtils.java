/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of EnhancedFX (https://github.com/colindj1120/EnhancedFX).
 *
 * EnhancedFX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EnhancedFX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with EnhancedFX.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.enhancedfx.utils;

import java.util.AbstractMap;
import java.util.Map;

/**
 * Utility class providing methods to easily create mutable and immutable instances of {@link Map.Entry}.
 *
 * <p>
 * {@code MapUtils} is designed to enhance the readability and maintainability of code that manipulates map entries. By abstracting the instantiation of {@link Map.Entry} objects, it allows for clearer
 * expression of intent when creating or manipulating key-value pairs, whether they need to be mutable for subsequent modifications or immutable for read-only use.
 * </p>
 *
 * <h2>Capabilities:</h2>
 * <p>
 * <ul>
 *   <li><strong>Mutable Entries:</strong> Create modifiable map entries that can be updated after creation.</li>
 *   <li><strong>Immutable Entries:</strong> Create read-only map entries that are constant after creation, ideal for situations where data integrity is paramount.</li>
 * </ul>
 * </p>
 *
 * <h2>Usage Examples:</h2>
 * <p>
 * Creating a mutable entry:
 * <pre>{@code
 * Map.Entry<String, Integer> mutableEntry = MapUtils.simpleEntry("apple", 10);
 * mutableEntry.setValue(20); // The value can be changed
 * }</pre>
 * </p>
 *
 * <p>
 * Creating an immutable entry:
 * <pre>{@code
 * Map.Entry<String, Integer> immutableEntry = MapUtils.simpleImmutableEntry("banana", 15);v// Attempting to change the value would result in an UnsupportedOperationException
 * }</pre>
 * </p>
 *
 * <p>
 * These utility methods are especially useful when working with Java streams or when entries need to be created outside the context of a map, simplifying the construction and manipulation of map data.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Map.Entry
 */
public class MapUtils {
    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * <p>
     * The {@code MapUtils} class is a collection of static utility methods for creating {@link Map.Entry} instances both mutable and immutable. Since it is not meant to be instantiated, the constructor is made
     * private. This design pattern ensures that the class usage is limited to accessing its static methods without creating an object instance of {@code MapUtils}.
     * </p>
     */
    private MapUtils() {}

    /**
     * Creates a mutable {@link Map.Entry} representing a key-value pair.
     *
     * <p>
     * This method simplifies the creation of map entries that can later be modified if necessary. It is particularly useful when a temporary or modifiable entry is needed, without the requirement for it to be
     * part of a map data structure.
     * </p>
     *
     * @param <K>
     *         The type of the map entry's key.
     * @param <V>
     *         The type of the map entry's value.
     * @param key
     *         The key for the entry; can be null if the implementation supports it.
     * @param value
     *         The value to be associated with the key; can be null if the implementation supports it.
     *
     * @return A mutable {@link Map.Entry} instance containing the provided key and value.
     */
    public static <K, V> Map.Entry<K, V> simpleEntry(K key, V value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    /**
     * Creates an immutable {@link Map.Entry} representing a key-value pair.
     *
     * <p>
     * This method is used to generate an entry that cannot be modified after creation, ensuring the integrity of the key-value pair. It is ideal for creating constant or read-only entries, providing a
     * guarantee that the entry's data will remain constant over its lifetime.
     * </p>
     *
     * @param <K>
     *         The type of the map entry's key.
     * @param <V>
     *         The type of the map entry's value.
     * @param key
     *         The key for the entry; can be null if the implementation supports it.
     * @param value
     *         The value to be associated with the key; can be null if the implementation supports it.
     *
     * @return An immutable {@link Map.Entry} instance containing the provided key and value.
     */
    public static <K, V> Map.Entry<K, V> simpleImmutableEntry(K key, V value) {
        return new AbstractMap.SimpleImmutableEntry<K, V>(key, value);
    }
}
