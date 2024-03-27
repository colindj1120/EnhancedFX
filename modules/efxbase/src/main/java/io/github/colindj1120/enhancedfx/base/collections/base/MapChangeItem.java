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
package io.github.colindj1120.enhancedfx.base.collections.base;

import java.util.Map;

/**
 * The {@code MapChangeItem} class captures details about changes made to a map, including additions, removals, and updates of entries. It is designed to facilitate the handling of dynamic data
 * changes within applications, particularly those with a UI component that needs to reflect changes to underlying data structures in real-time.
 *
 * <p>This class is generic, allowing it to accommodate any types of keys and values within the map. Each instance of {@code MapChangeItem} is associated with a specific {@link UpdateActions} enum
 * value that describes the nature of the change, such as {@code ADDED}, {@code REMOVED}, or {@code REPLACED}.</p>
 *
 * <p>Features of {@code MapChangeItem} include:</p>
 * <ul>
 *     <li><em>Update Actions</em>: Enumerates the type of change performed on the map, providing a clear description of the operation (addition, removal, bulk update, etc.).</li>
 *     <li><em>Element-Level Changes</em>: For changes affecting individual map entries, {@code MapChangeItem} stores both the old and new versions of the entry, if applicable, facilitating
 *     granular updates and rollback capabilities.</li>
 *     <li><em>Bulk Changes</em>: Supports capturing bulk changes to the map, where the entire map's content may be replaced. This is useful for efficiently processing large-scale updates.</li>
 *     <li><em>Old and New Map States</em>: Maintains references to the states of the map before and after the update, enabling comprehensive tracking of changes and supporting features such as
 *     undo functionality.</li>
 * </ul>
 *
 * <p>Usage scenarios for {@code MapChangeItem} include:</p>
 * <ul>
 *     <li>Updating UI components in response to changes in the data model, ensuring the UI remains in sync with the underlying data.</li>
 *     <li>Logging and auditing of changes to data structures, providing a clear record of how and when data changes occur.</li>
 *     <li>Implementing undo/redo functionality by leveraging the detailed change information captured by {@code MapChangeItem} instances.</li>
 * </ul>
 *
 * <p>By offering a structured way to represent changes to maps, {@code MapChangeItem} enhances the maintainability and clarity of code that interacts with dynamic data collections, contributing to
 * more robust and user-responsive applications.</p>
 *
 * @param <K>
 *         the type of keys maintained by the map
 * @param <V>
 *         the type of mapped values
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see UpdateActions
 */
public class MapChangeItem<K, V> {
    private final UpdateActions updateAction;

    private final Map<K, V> oldMap;
    private final Map<K, V> newMap;

    private final K               key;
    private final V oldElement;
    private final V newElement;

    /**
     * Constructs a {@code MapChangeItem} to represent a change involving individual map entries. This constructor is suitable for actions where specific entries are added, updated, or removed.
     *
     * @param key
     *         The key of the element that has been updated
     * @param updateAction
     *         The type of update action being represented, such as {@code ADDED}, {@code REMOVED}, or {@code REPLACED}.
     * @param oldElement
     *         The map element before the update, or {@code null} if the update action is {@code ADDED}.
     * @param newElement
     *         The map element after the update, or {@code null} if the update action is {@code REMOVED}. For {@code REPLACED} actions, this represents the updated entry.
     */
    public MapChangeItem(UpdateActions updateAction, K key, V oldElement, V newElement) {
        this.updateAction = updateAction;
        this.oldElement   = oldElement;
        this.newElement   = newElement;
        this.key          = key;

        // These are not applicable for individual entry changes.
        this.oldMap = null;
        this.newMap = null;
    }

    /**
     * Constructs a {@code MapChangeItem} to represent a bulk change to a map. This constructor is intended for actions that affect multiple entries at once, such as a complete replacement of map
     * contents.
     *
     * @param updateAction
     *         The type of update action being represented, such as {@code BULK_ADD}, {@code BULK_REMOVE}, or {@code BULK_REPLACED}.
     * @param oldMap
     *         The map's state before the bulk update. This should capture all entries that were present before the update.
     * @param newMap
     *         The map's state after the bulk update. This should capture all entries as they exist after the update.
     */
    public MapChangeItem(UpdateActions updateAction, Map<K, V> oldMap, Map<K, V> newMap) {
        this.updateAction = updateAction;
        this.oldMap       = oldMap;
        this.newMap       = newMap;

        // These are not applicable for bulk changes.
        this.oldElement = null;
        this.newElement = null;
        this.key        = null;
    }

    /**
     * Returns the {@code UpdateActions} associated with this {@code MapChangeItem}.
     *
     * @return the {@code UpdateActions} associated with this {@code MapChangeItem}.
     *
     * @see UpdateActions
     */
    public UpdateActions getUpdateAction() {
        return updateAction;
    }

    /**
     * Returns the old map associated with the map change item.
     *
     * @return the old map associated with the map change item.
     */
    public Map<K, V> getOldMap() {
        return oldMap;
    }

    /**
     * Retrieves a new instance of a map.
     *
     * @return A new instance of a map.
     */
    public Map<K, V> getNewMap() {
        return newMap;
    }

    /**
     * Retrieves the old element associated with this MapChangeItem.
     *
     * @return The old element associated with this MapChangeItem.
     */
    public V getOldElement() {
        return oldElement;
    }

    /**
     * Returns the new element associated with a collection update action.
     *
     * @return The new element as a Map.Entry<K,V> object.
     */
    public V getNewElement() {
        return newElement;
    }

    public K getKey() {
        return key;
    }
}
