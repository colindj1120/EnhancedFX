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
package io.github.colindj1120.enhancedfx.controls.factory.configurators.base.interfaces.scene;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * The {@code ParentConfig} interface defines a comprehensive set of methods for configuring {@link Parent} nodes within JavaFX
 * applications. This interface provides mechanisms for dynamically managing layout properties, stylesheets, and event listeners,
 * facilitating the creation of responsive and adaptable user interfaces.
 *
 * <p>Features include:</p>
 * <ul>
 *     <li>Adding and removing change and invalidation listeners for layout-related properties, enabling responsive UI updates.</li>
 *     <li>Managing the list of unmodifiable children nodes through listeners, allowing for the observation of changes in the child
 *     node structure.</li>
 *     <li>Dynamic manipulation of stylesheets, offering the ability to add, remove, or modify stylesheets at runtime for real-time
 *     UI styling.</li>
 *     <li>Requesting layout updates and performing layout operations, ensuring that the UI layout is refreshed as needed.</li>
 * </ul>
 *
 * <p>This interface is designed to be implemented by configurator classes that aim to enhance the flexibility and usability of
 * {@link Parent} nodes. It serves as a foundational component of the EnhancedFX library, which seeks to provide
 * extended functionality and utilities beyond the core JavaFX library.</p>
 *
 * <p>Implementations of {@code ParentConfig} should ensure thread-safety and consistency in the modification and management
 * of parent nodes, particularly when dealing with asynchronous updates and event handling.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Parent
 * @see Node
 * @see Property
 * @see ObservableList
 */
@SuppressWarnings("UnusedReturnValue")
public interface ParentConfig {
    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a change listener to be notified when there's a change in the layout needs.
     *
     * @param changeListener
     *         the listener to be notified of layout changes
     *
     * @return the current instance for method chaining
     */
    ParentConfig addNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to be notified when the layout needs are invalidated.
     *
     * @param invalidationListener
     *         the listener to be notified
     *
     * @return the current instance for method chaining
     */
    ParentConfig addNeedsLayoutInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a ListChangeListener to be notified when there's a change in the list of unmodifiable children.
     *
     * @param listChangeListener
     *         the listener to be notified of changes
     *
     * @return the current instance for method chaining
     */
    ParentConfig addGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener);

    /**
     * Adds an invalidation listener to be notified when the list of unmodifiable children is invalidated.
     *
     * @param invalidationListener
     *         the listener to be notified
     *
     * @return the current instance for method chaining
     */
    ParentConfig addGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a ListChangeListener to be notified when there's a change in the list of stylesheets.
     *
     * @param listChangeListener
     *         the listener to be notified of changes
     *
     * @return the current instance for method chaining
     */
    ParentConfig addStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener);

    /**
     * Adds an invalidation listener to be notified when the list of stylesheets is invalidated.
     *
     * @param invalidationListener
     *         the listener to be notified
     *
     * @return the current instance for method chaining
     */
    ParentConfig addStylesheetsListInvalidationListener(InvalidationListener invalidationListener);

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a change listener from the notification list for layout changes.
     *
     * @param changeListener
     *         the listener to remove
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the notification list for layout invalidation.
     *
     * @param invalidationListener
     *         the listener to remove
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeNeedsLayoutInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a ListChangeListener from the notification list for changes in unmodifiable children.
     *
     * @param listChangeListener
     *         the listener to remove
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener);

    /**
     * Removes an invalidation listener from the notification list for unmodifiable children invalidation.
     *
     * @param invalidationListener
     *         the listener to remove
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a ListChangeListener from the notification list for changes in the list of stylesheets.
     *
     * @param listChangeListener
     *         the listener to remove
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener);

    /**
     * Removes an invalidation listener from the notification list for stylesheet list invalidation.
     *
     * @param invalidationListener
     *         the listener to remove
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeStylesheetsListInvalidationListener(InvalidationListener invalidationListener);

    //endregion Remove Listener Functions

    //region Layout Functions
    //*****************************************************************
    // Layout Functions
    //*****************************************************************

    /**
     * Requests a layout update for the component.
     *
     * @return the current instance for method chaining
     */
    ParentConfig requestLayout();

    /**
     * Executes the layout pass for the component. Note: The method name `Layout` may be a typo or incorrect naming convention.
     * Typically, method names should be verbs and start with lowercase letters.
     *
     * @return the current instance for method chaining
     */
    ParentConfig Layout();

    //endregion Layout Functions

    //region Add EFXStyle Functions
    //*****************************************************************
    // Add EFXStyle Functions
    //*****************************************************************

    /**
     * Adds a stylesheet to the beginning of the component's list of stylesheets.
     *
     * @param stylesheet
     *         the stylesheet to add
     *
     * @return the current instance for method chaining
     */
    ParentConfig addFirstStylesheet(String stylesheet);

    /**
     * Adds a stylesheet to the end of the component's list of stylesheets.
     *
     * @param stylesheet
     *         the stylesheet to add
     *
     * @return the current instance for method chaining
     */
    ParentConfig addLastStylesheet(String stylesheet);

    /**
     * Adds a stylesheet to the component's list of stylesheets.
     *
     * @param stylesheet
     *         the stylesheet to add
     *
     * @return the current instance for method chaining
     */
    ParentConfig addStylesheet(String stylesheet);

    /**
     * Adds a stylesheet at the specified index in the component's list of stylesheets.
     *
     * @param index
     *         the index at which the stylesheet will be added
     * @param stylesheet
     *         the stylesheet to add
     *
     * @return the current instance for method chaining
     */
    ParentConfig addStylesheet(int index, String stylesheet);

    /**
     * Adds all the specified stylesheets to the component's list of stylesheets.
     *
     * @param stylesheets
     *         an array of stylesheets to add
     *
     * @return the current instance for method chaining
     */
    ParentConfig addAllStylesheets(String... stylesheets);

    /**
     * Adds all the stylesheets in the specified collection to the component's list of stylesheets.
     *
     * @param c
     *         a collection of stylesheets to add
     *
     * @return the current instance for method chaining
     */
    ParentConfig addAllStylesheets(Collection<? extends String> c);

    /**
     * Adds all the stylesheets in the specified collection at the specified index in the component's list of stylesheets.
     *
     * @param index
     *         the index at which the stylesheets will be added
     * @param c
     *         a collection of stylesheets to add
     *
     * @return the current instance for method chaining
     */
    ParentConfig addAllStylesheets(int index, Collection<? extends String> c);

    //endregion Add EFXStyle Functions

    //region Remove EFXStyle Functions
    //*****************************************************************
    // Remove EFXStyle Functions
    //*****************************************************************

    /**
     * Removes the first stylesheet from the component's list of stylesheets.
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeFirstStylesheet();

    /**
     * Removes the last stylesheet from the component's list of stylesheets.
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeLastStylesheet();

    /**
     * Removes a specific stylesheet from the component's list of stylesheets.
     *
     * @param stylesheet
     *         the stylesheet to remove
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeStylesheet(String stylesheet);

    /**
     * Removes a range of stylesheets from the component's list of stylesheets.
     *
     * @param from
     *         the start index, inclusive
     * @param to
     *         the end index, exclusive
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeStylesheets(int from, int to);

    /**
     * Removes stylesheets that satisfy the given predicate.
     *
     * @param filter
     *         a predicate which returns {@code true} for stylesheets to be removed
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeStylesheetsIf(Predicate<? super String> filter);

    /**
     * Removes all the specified stylesheets from the component's list of stylesheets.
     *
     * @param stylesheets
     *         an array of stylesheets to remove
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeAllStylesheets(String... stylesheets);

    /**
     * Removes all the stylesheets in the specified collection from the component's list of stylesheets.
     *
     * @param c
     *         a collection of stylesheets to remove
     *
     * @return the current instance for method chaining
     */
    ParentConfig removeAllStylesheets(Collection<? extends String> c);

    //endregion Remove EFXStyle Functions
}
