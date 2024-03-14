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
package io.github.colindj1120.enhancedfx.base.collections.base;

import java.util.List;

/**
 * The {@code ListChangeItem} class encapsulates information about changes made to a list, supporting a variety of update actions such as addition, removal, and replacement of elements, both
 * individually and in bulk. This class is part of the MaterialDesignUI library, designed to enhance the functionality of collections by providing detailed change notifications.
 *
 * <p>Each instance of {@code ListChangeItem} represents a specific change action, characterized by the type of action (defined in {@link UpdateActions}) and the elements or lists involved in the
 * change. The class is generic, allowing it to be used with any type of object stored within a list.</p>
 *
 * <p>Key features of {@code ListChangeItem} include:</p>
 * <ul>
 *     <li><em>Update Action</em>: Specifies the nature of the change to the list, such as adding, removing, or replacing elements. The action is defined by the {@link UpdateActions} enum, making
 *     it easy to identify and respond to different types of changes.</li>
 *     <li><em>Element-Level Changes</em>: For single-element updates (added, removed, replaced), {@code ListChangeItem} captures the relevant elements before and after the change, enabling precise
 *     modifications and updates to UI components or data structures.</li>
 *     <li><em>Bulk Changes</em>: Supports bulk operations, where multiple elements are added, removed, or replaced simultaneously. This is particularly useful for efficiently processing large data
 *     sets or batch updates.</li>
 *     <li><em>Old and New States</em>: Maintains references to both the old and new states of the list or the elements involved, providing a complete picture of the change and facilitating undo
 *     operations or comparative analyses.</li>
 * </ul>
 *
 * <p>This class is intended for use within event-driven or reactive programming models, where changes to data collections need to be tracked and responded to dynamically. It is particularly useful
 * in UI development scenarios with JavaFX, where list changes often result in immediate updates to the user interface.</p>
 *
 * <p>By encapsulating list changes in a detailed and structured manner, {@code ListChangeItem} enhances the maintainability and readability of code that responds to dynamic changes in data
 * collections, contributing to the overall robustness and responsiveness of applications.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see UpdateActions
 */
public class ListChangeItem<T> {
    private final UpdateActions updateAction;

    private final List<T> oldList;
    private final List<T> newList;

    private final T oldElement;
    private final T newElement;

    /**
     * Constructs a {@code ListChangeItem} instance to represent a single element change within a list.
     *
     * <p>This constructor is suitable for actions such as {@code ADDED}, {@code REMOVED}, and {@code REPLACED}, where specific elements are involved in the update action.</p>
     *
     * @param updateAction
     *         The type of update action being represented, as defined in {@link UpdateActions}.
     * @param oldElement
     *         The element that was in the list before the update action.
     * @param newElement
     *         The element that is in the list after the update action.
     */
    public ListChangeItem(UpdateActions updateAction, T oldElement, T newElement) {
        this.updateAction = updateAction;
        this.oldElement   = oldElement;
        this.newElement   = newElement;

        this.oldList = null;
        this.newList = null;
    }

    /**
     * Constructs a {@code ListChangeItem} instance to represent a bulk change within a list.
     *
     * <p>This constructor is suitable for bulk actions such as {@code BULK_ADD}, {@code BULK_REMOVE}, and {@code BULK_REPLACED}, where multiple elements are involved in the update action
     * simultaneously.</p>
     *
     * @param updateAction
     *         The type of update action being represented, as defined in {@link UpdateActions}.
     * @param oldList
     *         The list of elements that were in the list before the bulk update action.
     * @param newList
     *         The list of elements that are in the list after the bulk update action.
     */
    public ListChangeItem(UpdateActions updateAction, List<T> oldList, List<T> newList) {
        this.updateAction = updateAction;
        this.oldList      = oldList;
        this.newList      = newList;

        this.oldElement = null;
        this.newElement = null;
    }

    /**
     * Returns the update action associated with the list change item.
     *
     * @return the update action of the list change item
     */
    public UpdateActions getListAction() {
        return updateAction;
    }

    /**
     * Returns the old list associated with this ListChangeItem object.
     *
     * @return the old list.
     */
    public List<T> getOldList() {
        return oldList;
    }

    /**
     * Returns the new list.
     *
     * @return The new list.
     */
    public List<T> getNewList() {
        return newList;
    }

    /**
     * Returns the old element of a ListChangeItem.
     *
     * @return the old element
     */
    public T getOldElement() {
        return oldElement;
    }

    /**
     * Retrieves the new element of the list change item.
     *
     * @return The new element of the list change item.
     */
    public T getNewElement() {
        return newElement;
    }
}
