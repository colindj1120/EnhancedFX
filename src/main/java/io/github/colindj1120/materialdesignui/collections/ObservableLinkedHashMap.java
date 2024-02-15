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
package io.github.colindj1120.materialdesignui.collections;

import io.github.colindj1120.materialdesignui.utils.MapUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * An extension of {@link LinkedHashMap} that allows observers to listen to changes in the map. This class notifies registered listeners of any put, remove, or update operations performed on the map.
 *
 * @param <K>
 *         the type of keys maintained by this map
 * @param <V>
 *         the type of mapped values
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public class ObservableLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private final List<BiConsumer<Map.Entry<K, V>, Map.Entry<K, V>>> onChangeListeners = new ArrayList<>();
    private final List<BiConsumer<Map<K, V>, Map<K, V>>> bulkChangeListeners = new ArrayList<>();


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
     * Adds a change listener that will be notified whenever the map is updated.
     *
     * @param onChange
     *         the change listener to add
     */
    public void addChangeListener(BiConsumer<Map.Entry<K, V>, Map.Entry<K, V>> onChange) {
        onChangeListeners.add(onChange);
    }

    /**
     * Removes the specified change listener so that it no longer receives change notifications.
     *
     * @param onChange
     *         the change listener to remove
     */
    public void removeChangeListener(BiConsumer<Map.Entry<K, V>, Map.Entry<K, V>> onChange) {
        onChangeListeners.remove(onChange);
    }

    /**
     * Adds a bulk change listener that will be notified with snapshots of the map's state
     * before and after a bulk operation. This allows observers to analyze the overall impact of operations
     * that modify the map on a larger scale.
     *
     * @param listener the bulk change listener to add, receiving a snapshot of the map before and after the bulk operation
     */
    public void addBulkChangeListener(BiConsumer<Map<K, V>, Map<K, V>> listener) {
        bulkChangeListeners.add(listener);
    }

    /**
     * Removes the specified bulk change listener so that it no longer receives bulk change notifications.
     *
     * @param listener the bulk change listener to remove
     */
    public void removeBulkChangeListener(BiConsumer<Map<K, V>, Map<K, V>> listener) {
        bulkChangeListeners.remove(listener);
    }


    /**
     * Notifies all registered change listeners of a change.
     *
     * @param key
     *         the key that was changed
     * @param oldValue
     *         the previous value associated with {@code key}, or {@code null} if there was no mapping for {@code key}
     * @param newValue
     *         the new value associated with {@code key}, or {@code null} if the mapping has been removed
     */
    private void notifyChangeListeners(K key, V oldValue, V newValue) {
        if (!Objects.equals(oldValue, newValue)) {
            Map.Entry<K, V> oldEntry = oldValue != null ? MapUtils.simpleEntry(key, oldValue) : null;
            Map.Entry<K, V> newEntry = newValue != null ? MapUtils.simpleEntry(key, newValue) : null;
            onChangeListeners.forEach(listener -> listener.accept(oldEntry, newEntry));
        }
    }

    /**
     * Notifies all registered bulk change listeners of a change before and after bulk operations. This method should
     * be called with snapshots of the map's state before and after a bulk operation, such as {@code putAll}, {@code clear},
     * or any other method that results in multiple modifications to the map.
     *
     * <p>This is a conceptual method documentation, as the actual implementation details and method signatures
     * for invoking bulk operations and capturing map states would depend on specific bulk operation implementations
     * within this class.</p>
     *
     * @param beforeState the state of the map before the bulk operation, captured as an immutable snapshot
     * @param afterState the state of the map after the bulk operation, captured as an immutable snapshot
     */
    private void notifyBulkChangeListeners(Map<K, V> beforeState, Map<K, V> afterState) {
        bulkChangeListeners.forEach(listener -> listener.accept(beforeState, afterState));
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
        notifyChangeListeners(key, oldValue, value);
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
        V oldValue = super.remove(key);
        notifyChangeListeners((K) key, oldValue, null);
        return oldValue;
    }

    /**
     * Determines whether the eldest entry should be removed or not. If the eldest entry is removed, all registered change listeners are notified of this removal.
     *
     * @param eldest
     *         the least recently accessed entry
     *
     * @return {@code true} if the eldest entry should be removed from the map; {@code false} if it should be retained.
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        boolean shouldRemove = super.removeEldestEntry(eldest);
        if (shouldRemove) {
            notifyChangeListeners(eldest.getKey(), eldest.getValue(), null);
        }
        return shouldRemove;
    }

    /**
     * Copies all the mappings from the specified map to this map. The effect of this call is equivalent to that of calling {@code put(k, v)} on this map once for each mapping from key {@code k} to
     * value {@code v} in the specified map. All registered change listeners are notified for each insertion.
     *
     * @param m
     *         mappings to be stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Map<K, V> beforeState = bulkChangeListeners.isEmpty() ? null : new LinkedHashMap<>(this);
        m.forEach(this::put);
        if (!bulkChangeListeners.isEmpty()) {
            notifyBulkChangeListeners(beforeState, this);
        }
    }

    /**
     * Associates the specified value with the specified key in this map, if not already present. If the map previously did not contain a mapping for the key, the value is inserted and all registered
     * change listeners are notified of the new insertion. If the map already contained a mapping for the key, the existing value is retained, and no change listeners are notified.
     *
     * @param key
     *         key with which the specified value is to be associated
     * @param value
     *         value to be associated with the specified key
     *
     * @return the previous value associated with the specified key, or {@code null} if there was no mapping for the key. A {@code null} return can also indicate that the map previously associated
     *         {@code null} with the key, if the implementation supports {@code null} values.
     */
    @Override
    public V putIfAbsent(K key, V value) {
        V oldValue    = super.get(key);
        V returnValue = super.putIfAbsent(key, value);
        if (returnValue == null) { // A null return indicates the value was absent and is now added.
            notifyChangeListeners(key, oldValue, value);
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
            Map<K, V> beforeState = bulkChangeListeners.isEmpty() ? null : new LinkedHashMap<>(this);
            super.clear();
            // Notify that the map is being cleared by passing null for both old and new states.
            notifyChangeListeners(null, null, null);
            if (!bulkChangeListeners.isEmpty()) {
                notifyBulkChangeListeners(beforeState, this);
            }
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
            notifyChangeListeners(key, oldValue, newValue);
        }
        return replaced;
    }

    /**
     * Replaces the entry for the specified key only if it is currently mapped to some value.
     *
     * @param key
     *         key with which the specified value is associated
     * @param value
     *         value to be associated with the specified key
     *
     * @return the previous value associated with the specified key, or {@code null} if there was no mapping for the key
     */
    @Override
    public V replace(K key, V value) {
        V oldValue = super.replace(key, value);
        if (oldValue != null) {
            // Notify change listeners if the old value was successfully replaced.
            notifyChangeListeners(key, oldValue, value);
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
        Map<K, V> beforeState = bulkChangeListeners.isEmpty() ? null : new LinkedHashMap<>(this);
        AtomicBoolean updated = new AtomicBoolean(false);
        super.replaceAll((k, v) -> {
            V newValue = function.apply(k, v);
            if (!Objects.equals(v, newValue)) { // Only notify if the value actually changes
                updated.set(true);
                notifyChangeListeners(k, v, newValue);
            }

            return newValue;
        });

        if (!bulkChangeListeners.isEmpty() && updated.get()) {
            notifyBulkChangeListeners(beforeState, this);
        }
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
        V oldValue = super.get(key);
        super.putFirst(key, value);
        notifyChangeListeners(key, oldValue, value);
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
        V oldValue = super.get(key);
        super.putLast(key, value);
        notifyChangeListeners(key, oldValue, value);
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
            notifyChangeListeners(key, oldValue, newValue);
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
        notifyChangeListeners(key, oldValue, newValue);
        return newValue; // Return the newly computed value
    }

    /**
     * Attempts to compute a value for the specified key if it is not already associated with a value (or is mapped to {@code null}), and enters it into this map unless {@code null}. If the function
     * returns a non-null value, the map is updated and all registered change listeners are notified of the new or updated mapping.
     *
     * @param key
     *         the key with which the specified value is to be associated
     * @param mappingFunction
     *         the function to compute a value
     *
     * @return the current (existing or computed) value associated with the specified key, or null if the computed value is null
     *
     * @apiNote The method notifies registered change listeners if a new mapping is added to the map as a result of the computation. If the key is already associated with a value, and the
     *         computed value is different from the existing value, change listeners are also notified. The notification includes the old value (if any) and the new value.
     */
    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        V oldValue = super.get(key); // Capture old value for comparison
        V newValue = super.computeIfAbsent(key, mappingFunction); // Compute new value if absent
        // Notify change listeners if a new value was computed and added
        notifyChangeListeners(key, oldValue, newValue);
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
        notifyChangeListeners(key, oldValue, newValue);
        return newValue; // Return the newly computed value or null if the computation did not yield a mapping
    }
}
