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
package io.github.colindj1120.materialdesignui.enums.collections;

/**
 * Enumerates the types of update actions that can occur within observable collections in the MaterialDesignUI framework, facilitating the precise identification and handling of changes.
 *
 * <p>These actions enable components or data models to react appropriately to modifications in the collections they observe.</p>
 *
 * <h2>Available Actions:</h2>
 * <ul>
 *     <li><em>ADDED:</em> A single item has been added to the collection.</li>
 *     <li><em>BULK_ADD:</em> Multiple items have been added to the collection at once, through bulk operations.</li>
 *     <li><em>REMOVED:</em> A single item has been removed from the collection.</li>
 *     <li><em>BULK_REMOVE:</em> Multiple items have been removed from the collection in a single operation.</li>
 *     <li><em>REPLACED:</em> One item in the collection has been replaced by another.</li>
 *     <li><em>BULK_REPLACED:</em> Multiple items in the collection have been replaced in a bulk operation.</li>
 *     <li><em>CLEARED:</em> All items in the collection have been removed, clearing the collection.</li>
 * </ul>
 *
 * <h2>Context and Application:</h2>
 * <p>These enumeration values are crucial for event-driven programming, particularly in scenarios where it's essential to monitor and respond to dynamic changes in data collections, such as in UI
 * components that display lists of data.</p>
 *
 * <p>By categorizing collection update actions, {@code UpdateActions} streamlines the implementation of responsive interfaces, aiding in the seamless synchronization between data models and their
 * visual presentations. This classification ensures that applications can efficiently process and react to modifications, enhancing the user experience through dynamic and interactive interfaces.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public enum UpdateActions {
    ADDED,
    BULK_ADD,
    REMOVED,
    BULK_REMOVE,
    REPLACED,
    BULK_REPLACED,
    CLEARED
}
