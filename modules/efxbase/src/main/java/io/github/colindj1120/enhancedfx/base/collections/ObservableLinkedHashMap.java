/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialDesignUI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.enhancedfx.base.collections;

import io.github.colindj1120.enhancedfx.base.collections.base.MapChangeItem;
import io.github.colindj1120.enhancedfx.base.collections.base.UpdateActions;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * ObservableLinkedHashMap is an extension of {@link LinkedHashMap} that supports notifying registered listeners about changes to the map. This functionality allows for a reactive programming style,
 * where changes to the map's contents can trigger updates elsewhere in an application, such as refreshing user interface components or triggering further data processing.
 *
 * <p>
 * This map implementation is ideal for scenarios where it is important to track how the map changes over time, not just its current state. Each change (addition, removal, update of entries) can be
 * observed by registering a {@link Consumer} that accepts {@link MapChangeItem} instances, which describe the change that occurred.
 * </p>
 *
 * <p>
 * Usage of this class is particularly beneficial in applications with complex data flows, where changes to data structures need to be propagated efficiently to various parts of the application,
 * ensuring data consistency and enabling dynamic response to data mutations.
 * </p>
 *
 * @param <K>
 *         the type of keys maintained by this map
 * @param <V>
 *         the type of mapped values
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see LinkedHashMap
 * @see Consumer
 * @see MapChangeItem
 * @see UpdateActions
 */
public class ObservableLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private final List<Consumer<MapChangeItem<K, V>>> onChangeListeners = new ArrayList<>();

    /**
     * Constructs an empty {@code ObservableLinkedHashMap} instance with default initial capacity and load factor.
     */
    public ObservableLinkedHashMap() {
        super();
    }

    /**
     * Constructs an empty {@code ObservableLinkedHashMap} instance with the specified initial capacity and default load factor.
     *
     * @param initialCapacity
     *         the initial capacity
     */
    public ObservableLinkedHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Constructs an empty {@code ObservableLinkedHashMap} instance with the specified initial capacity and load factor.
     *
     * @param initialCapacity
     *         the initial capacity
     * @param loadFactor
     *         the load factor
     */
    public ObservableLinkedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * Constructs an empty {@code ObservableLinkedHashMap} instance with the specified initial capacity, load factor, and ordering mode.
     *
     * @param initialCapacity
     *         the initial capacity
     * @param loadFactor
     *         the load factor
     * @param accessOrder
     *         the ordering mode - {@code true} for access-order, {@code false} for insertion-order
     */
    public ObservableLinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    /**
     * Constructs an {@code ObservableLinkedHashMap} instance with the same mappings as the specified map.
     *
     * @param m
     *         the map whose mappings are to be placed in this map
     */
    public ObservableLinkedHashMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    /**
     * Registers a listener that will be notified of changes to the map. The listener is a {@link Consumer} of {@link MapChangeItem}, which contains details about the change.
     *
     * @param onChange
     *         the listener to be notified of changes
     */
    public void addChangeListener(Consumer<MapChangeItem<K, V>> onChange) {
        onChangeListeners.add(onChange);
    }

    /**
     * Removes a previously registered change listener.
     *
     * @param onChange
     *         the listener to remove
     */
    public void removeChangeListener(Consumer<MapChangeItem<K, V>> onChange) {
        onChangeListeners.remove(onChange);
    }

    /**
     * Notifies all registered change listeners about a change in the map. The method constructs a {@link MapChangeItem} describing the change and passes it to each listener.
     *
     * @param mapChangeItem
     *         the {@link MapChangeItem} describing the change
     */
    private void notifyChangeListeners(MapChangeItem<K, V> mapChangeItem) {
        onChangeListeners.forEach(listener -> listener.accept(mapChangeItem));
    }

    /**
     * Inserts the specified key-value pair into the map. If the map previously contained a mapping for the key, the old value is replaced. After the insertion, all registered change listeners are
     * notified of the change.
     *
     * @param key
     *         key with which the specified value is to be associated
     * @param value
     *         value to be associated with the specified key
     *
     * @return the previous value associated with {@code key}, or {@code null} if there was no mapping for {@code key}.
     */
    @Override
    public V put(K key, V value) {
        V oldValue = super.put(key, value);
        notifyChangeListeners(new MapChangeItem<>(UpdateActions.ADDED, key, oldValue, value));
        return oldValue;
    }

    /**
     * Removes the mapping for a key from this map if it is present. All registered change listeners are notified of the removal.
     *
     * @param key
     *         key whose mapping is to be removed from the map
     *
     * @return the previous value associated with {@code key}, or {@code null} if there was no mapping for {@code key}.
     */
    @SuppressWarnings("unchecked")
    @Override
    public V remove(Object key) {
        boolean containsKey = super.containsKey(key);
        V       oldValue    = super.remove(key);
        if (containsKey) {
            notifyChangeListeners(new MapChangeItem<>(UpdateActions.REMOVED, (K) key, oldValue, null));
        }
        return oldValue;
    }

    /**
     * Adds all the key-value mappings from a given map to this map.
     *
     * @param m
     *         the map containing the key-value mappings to be added to this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Map<K, V> beforeState = getBeforeState();
        super.putAll(m);
        notifyChangeListeners(new MapChangeItem<>(UpdateActions.BULK_ADD, beforeState, this));
    }

    /**
     * Associates the specified value with the specified key in this map if the key is not already associated with a value.
     *
     * @param key
     *         the key with which the specified value is to be associated
     * @param value
     *         the value to be associated with the specified key
     *
     * @return the previous value associated with the specified key, or {@code null} if there was no mapping for the key.
     */
    @Override
    public V putIfAbsent(K key, V value) {
        V oldValue    = super.get(key);
        V returnValue = super.putIfAbsent(key, value);
        if (returnValue == null && super.containsKey(key) && super.containsValue(value)) {
            notifyChangeListeners(new MapChangeItem<>(UpdateActions.ADDED, key, oldValue, value));
        }
        return returnValue;
    }

    /**
     * Removes all the mappings from this map. The map will be empty after this call returns. Notifies all registered change listeners that the map has been cleared by passing {@code null} for both
     * old and new states.
     */
    @Override
    public void clear() {
        if (!isEmpty()) {
            Map<K, V> beforeState = getBeforeState();
            super.clear();
            notifyChangeListeners(new MapChangeItem<>(UpdateActions.CLEARED, beforeState, this));
        }
    }

    /**
     * Replaces the entry for the specified key only if it is currently mapped to some value.
     *
     * @param key
     *         key with which the specified value is associated
     * @param oldValue
     *         value expected to be associated with the specified key
     * @param newValue
     *         value to be associated with the specified key
     *
     * @return {@code true} if the value was replaced
     */
    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        boolean replaced = super.replace(key, oldValue, newValue);
        if (replaced) {
            // Notify change listeners if the value was successfully replaced.
            notifyChangeListeners(new MapChangeItem<>(UpdateActions.REPLACED, key, oldValue, newValue));
        }
        return replaced;
    }

    /**
     * Replaces the value associated with the specified key in this map with the specified value. Notifies change listeners if the old value is successfully replaced.
     *
     * @param key
     *         the key whose value is to be replaced
     * @param value
     *         the value to replace the old value with
     *
     * @return the previous value associated with the specified key, or null if there was no mapping for the key
     */
    @Override
    public V replace(K key, V value) {
        V oldValue = super.replace(key, value);
        // Notify change listeners if the old value was successfully replaced.
        if (super.containsKey(key) && super.containsValue(value)) {
            notifyChangeListeners(new MapChangeItem<>(UpdateActions.REPLACED, key, oldValue, value));
        }
        return oldValue;
    }

    /**
     * Replaces each entry's value with the result of invoking the given function on that entry until all entries have been processed, or the function throws an exception.
     *
     * @param function
     *         the function to apply to each entry
     */
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Map<K, V> beforeState = getBeforeState();
        super.replaceAll(function);
        notifyChangeListeners(new MapChangeItem<>(UpdateActions.BULK_REPLACED, beforeState, this));
    }

    /**
     * Inserts the specified value at the beginning of the map, maintaining the order.
     *
     * @param key
     *         key with which the specified value is associated
     * @param value
     *         value to be associated with the specified key
     *
     * @return the previous value associated with the key, or {@code null} if there was no mapping for the key
     */
    @Override
    public V putFirst(K key, V value) {
        V oldValue = super.putFirst(key, value);
        notifyChangeListeners(new MapChangeItem<>(UpdateActions.ADDED, key, oldValue, value));
        return oldValue;
    }

    /**
     * Appends the specified value at the end of the map, maintaining the order.
     *
     * @param key
     *         key with which the specified value is associated
     * @param value
     *         value to be associated with the specified key
     *
     * @return the previous value associated with the key, or {@code null} if there was no mapping for the key
     */
    @Override
    public V putLast(K key, V value) {
        V oldValue = super.putLast(key, value);
        notifyChangeListeners(new MapChangeItem<>(UpdateActions.ADDED, key, oldValue, value));
        return oldValue;
    }

    /**
     * Attempts to compute a mapping for the specified key and its current mapped value (or {@code null} if there is no current mapping).
     *
     * @param key
     *         key with which the specified value is associated
     * @param value
     *         value to be merged with the existing value associated with the key
     * @param remappingFunction
     *         the function to compute a value
     *
     * @return the new value associated with the specified key, or {@code null} if none
     */
    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        V oldValue = super.get(key); // Capture old value before merge
        V newValue = super.merge(key, value, remappingFunction);
        if (!Objects.equals(oldValue, newValue)) { // Notify only if there's an actual change
            notifyChangeListeners(new MapChangeItem<>(UpdateActions.REPLACED, key, oldValue, newValue));
        }
        return newValue;
    }

    /**
     * Attempts to compute a new mapping given the key and its current mapped value (or {@code null} if there is no current mapping). If the computation is performed and the result is non-null, the
     * map is updated and all registered change listeners are notified of the change. If the result is {@code null}, the mapping is removed (if present), and listeners are notified of the removal.
     *
     * @param key
     *         the key with which the specified value is associated
     * @param remappingFunction
     *         the function to compute a value
     *
     * @return the new value associated with the specified key, or null if none
     *
     * @apiNote The method notifies registered change listeners if the map changes as a result of the computation. This includes adding a new mapping, updating an existing mapping, or removing
     *         a mapping if the computed value is {@code null}. The notification includes the old value (if any) and the new value, except when the computation results in removal, in which case the
     *         new value is {@code null}.
     */
    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        V oldValue = super.get(key); // Capture old value for comparison
        V newValue = super.compute(key, remappingFunction); // Compute new value
        // Notify change listeners of the update
        if (!Objects.equals(oldValue, newValue)) {
            notifyChangeListeners(new MapChangeItem<>(UpdateActions.REPLACED, key, oldValue, newValue));
        }
        return newValue; // Return the newly computed value
    }

    /**
     * Computes and returns the value to which the specified key is mapped if the key is not already associated with a value (or is mapped to null), using the provided mapping function.
     *
     * <p>If the specified key is not already associated with a value (or is mapped to null), attempts to compute its value using the given mapping function and enters it into this map unless
     * null.</p>
     *
     * <p>This method initiates the process of computing a new value for the specified key, using the given mapping function, if the key is not already associated with a value. If the specified key
     * is not already associated with a value (or is mapped to null), this method computes a new value by invoking the given mapping function, passing the key as an argument. The given mapping
     * function should return the new value to be associated with the specified key, or null if the computed value is null and no mapping should occur.</p>
     *
     * <p>After the new value is computed and entered into the map, the provided change listeners are notified with the details of the change.</p>
     *
     * @param key
     *         the key with which the resulting value is to be associated.
     * @param mappingFunction
     *         the function to compute a value.
     *
     * @return the existing value to which the specified key is mapped, or the newly computed value if the specified key is not mapped to any value (or is mapped to null).
     */
    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        V oldValue = super.get(key); // Capture old value for comparison
        V newValue = super.computeIfAbsent(key, mappingFunction); // Compute new value if absent
        // Notify change listeners if a new value was computed and added
        if (Objects.isNull(oldValue)) {
            notifyChangeListeners(new MapChangeItem<>(UpdateActions.ADDED, key, null, newValue));
        }
        return newValue; // Return the newly computed or existing value
    }

    /**
     * If the value for the specified key is present and non-null, attempts to compute a new mapping given the key and its current mapped value. If the function returns {@code null}, the mapping is
     * removed. If the function returns a non-null value, the mapping is replaced with the new value. All registered change listeners are notified of the change.
     *
     * @param key
     *         the key with which the specified value is associated
     * @param remappingFunction
     *         the function to compute a value
     *
     * @return the new value associated with the specified key, or null if none
     *
     * @apiNote The method notifies registered change listeners if the map changes as a result of the computation. This includes updating an existing mapping with a new non-null value or
     *         removing a mapping if the computed value is {@code null}. The notification includes the old value and the new value, or the old value and {@code null} if the mapping is removed.
     */
    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        V oldValue = super.get(key); // Capture old value for comparison
        V newValue = super.computeIfPresent(key, remappingFunction); // Compute new value if present
        // Notify change listeners if a new value was computed and replaced
        if (!Objects.equals(oldValue, newValue)) {
            notifyChangeListeners(new MapChangeItem<>(UpdateActions.REPLACED, key, oldValue, newValue));
        }
        return newValue; // Return the newly computed value or null if the computation did not yield a mapping
    }

    /**
     * Returns the state of the map before any changes were made.
     *
     * <p>If there is no change listeners registered, this method returns null. Otherwise, it returns a new LinkedHashMap with the same key-value mappings as the current map.</p>
     *
     * @return the state of the map before any changes were made, or null if no change listeners are registered
     */
    @Nullable
    private Map<K, V> getBeforeState() {
        return onChangeListeners.isEmpty() ? null : new LinkedHashMap<>(this);
    }
}
