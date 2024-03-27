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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.parent.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.node.base.NodeConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Provides extensive configuration and customization capabilities for {@link Parent} nodes in JavaFX, facilitating advanced layout and style adjustments.
 *
 * <p>This interface extends {@code NodeConfig<T>}, enhancing {@link Parent} nodes with additional configuration methods. It enables developers to manage children nodes, listen for layout changes, and
 * manipulate stylesheets dynamically. This level of control is essential for creating complex and responsive JavaFX applications.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *   <li><b>Children Management:</b> Listen for changes in the children's list, including additions, removals, or updates, to dynamically adjust UI components.</li>
 *   <li><b>Layout Needs Monitoring:</b> Attach listeners to the needsLayout property to perform actions when the layout needs updating.</li>
 *   <li><b>Stylesheet Configuration:</b> Add or remove stylesheets dynamically, allowing for runtime changes to the application's appearance.</li>
 *   <li><b>Layout Execution:</b> Directly request or perform a layout pass, offering precise control over the rendering process.</li>
 *   <li><b>Dynamic Styling:</b> Utilize methods to manipulate the stylesheets list for dynamic UI theming and styling.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * Below is a simple example demonstrating how to configure a {@link Parent} node using an implementation of {@code ParentConfig}.
 * <pre>{@code
 * ParentConfig<ParentConfigurator> parentConfigurator = new ParentConfigurator(parent);
 * parentConfigurator.addNeedsLayoutChangeListener((observable, oldValue, newValue) -> System.out.println("Layout needs update: " + newValue))
 *                   .addStylesheetsListChangeListener(change -> System.out.println("Stylesheet list changed"))
 *                   .addStylesheet("style.css")
 *                   .requestLayout();
 * }</pre>
 *
 * <p>In this example, a {@code Parent} node is configured to listen for layout updates and stylesheet list changes. A new stylesheet is added, and a layout pass is requested to reflect changes immediately.</p>
 *
 * @param <T>
 *         The type of the configurator extending {@code ConfiguratorBase}, facilitating a fluent API style of configurations
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ParentConfig
 * @see ConfiguratorBase
 * @see Parent
 */
@SuppressWarnings("UnusedReturnValue")
public interface ParentConfig<T extends ConfiguratorBase> extends NodeConfig<T> {
    /*
     * Methods Available:
     *  - Parent getNode()
     *
     * Add Listener Functions
     *  - T addNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addNeedsLayoutInvalidationListener(InvalidationListener invalidationListener)
     *  - T addGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener)
     *  - T addGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener)
     *  - T addStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener)
     *  - T addStylesheetsListInvalidationListener(InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removeNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeNeedsLayoutInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener)
     *  - T removeGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener)
     *  - T removeStylesheetsListInvalidationListener(InvalidationListener invalidationListener)
     *
     * Layout Functions
     *  - T requestLayout()
     *  - T layout()
     *
     * Add Stylesheet Functions
     *  - T addFirstStylesheet(String stylesheet)
     *  - T addLastStylesheet(String stylesheet)
     *  - T addStylesheet(String stylesheet)
     *  - T addStylesheet(int index, String stylesheet)
     *  - T addAllStylesheets(String... elements)
     *  - T addAllStylesheets(Collection<? extends String> stylesheets)
     *  - T addAllStylesheets(int index, Collection<? extends String> stylesheets)
     *
     * Remove Stylesheet Functions
     *  - T removeFirstStylesheet()
     *  - T removeLastStylesheet()
     *  - T removeStylesheet(String stylesheet)
     *  - T removeStylesheets(int from, int to)
     *  - T removeStylesheetsIf(Predicate<? super String> filter)
     *  - T removeAllStylesheets(String... stylesheets)
     *  - T removeAllStylesheets(Collection<? extends String> c)
     *
     * Set Functions
     *  - T setStylesheets(int index, String element)
     *  - T replaceAllStylesheets(UnaryOperator<String> operator)
     *  - T setAllStylesheets(String... elements)
     *  - T setAllStylesheets(Collection<? extends String> col)
     */


    /**
     * Returns the {@link Parent} node associated with this configurator.
     *
     * <p>This method provides access to the parent node that is being configured, offering a foundation for further customization and functionality enhancement.</p>
     *
     * @return The current {@link Parent} associated with the current configurator instance
     */
    @Override
    Parent getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to listen for changes to the needsLayout property. This listener is notified whenever the layout needs update status of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().needsLayoutProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the needsLayout property. This listener is notified whenever the needsLayout property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        getNode().needsLayoutProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ListChangeListener} to listen for changes to the unmodifiable list of children. This listener is notified whenever there are additions, removals, or updates to the node's children.
     *
     * @param listChangeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        getNode().getChildrenUnmodifiable()
                 .addListener(listChangeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the unmodifiable children list. This listener is notified whenever the children list becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getChildrenUnmodifiable()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ListChangeListener} to listen for changes to the list of stylesheets. This listener is notified whenever there are additions, removals, or updates to the node's stylesheets.
     *
     * @param listChangeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        getNode().getStylesheets()
                 .addListener(listChangeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the stylesheets' list. This listener is notified whenever the stylesheets' list becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getStylesheets()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the needsLayout property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().needsLayoutProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the needsLayout property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        getNode().needsLayoutProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ListChangeListener} from the unmodifiable children list.
     *
     * @param listChangeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        getNode().getChildrenUnmodifiable()
                 .removeListener(listChangeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the unmodifiable children list.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getChildrenUnmodifiable()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ListChangeListener} from the stylesheets' list.
     *
     * @param listChangeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        getNode().getStylesheets()
                 .removeListener(listChangeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the stylesheets' list.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getStylesheets()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Layout Functions
    //*****************************************************************
    // Layout Functions
    //*****************************************************************

    /**
     * Requests a layout pass to be done for this node.
     *
     * <p>Calling this method indicates that the node needs to be laid out at the next opportunity. This is typically used when the node's size may have changed.</p>
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T requestLayout() {
        getNode().requestLayout();
        return getConfigurator();
    }

    /**
     * Performs a layout pass for this node.
     *
     * <p>This method is called to lay out the node. It can be used to manually invoke the layout if automatic layout is not desired.</p>
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T layout() {
        getNode().layout();
        return getConfigurator();
    }

    //endregion Layout Functions

    //region Add EFXStyle Functions
    //*****************************************************************
    // Add EFXStyle Functions
    //*****************************************************************

    /**
     * Adds a stylesheet to the beginning of the stylesheets' list.
     *
     * @param stylesheet
     *         The stylesheet URL to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addFirstStylesheet(String stylesheet) {
        getNode().getStylesheets()
                 .addFirst(stylesheet);
        return getConfigurator();
    }

    /**
     * Adds a stylesheet to the end of the stylesheets' list.
     *
     * @param stylesheet
     *         The stylesheet URL to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addLastStylesheet(String stylesheet) {
        getNode().getStylesheets()
                 .addLast(stylesheet);
        return getConfigurator();
    }

    /**
     * Adds a stylesheet to the stylesheets' list.
     *
     * @param stylesheet
     *         The stylesheet URL to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addStylesheet(String stylesheet) {
        getNode().getStylesheets()
                 .add(stylesheet);
        return getConfigurator();
    }

    /**
     * Adds a stylesheet at the specified position in the stylesheets' list.
     *
     * @param index
     *         The index at which the stylesheet should be added.
     * @param stylesheet
     *         The stylesheet URL to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addStylesheet(int index, String stylesheet) {
        getNode().getStylesheets()
                 .add(index, stylesheet);
        return getConfigurator();
    }

    /**
     * Adds multiple stylesheets to the node's list of stylesheets. This method allows for the bulk addition of stylesheets, which can be used to apply multiple CSS styles to the node in one operation.
     *
     * @param elements
     *         An array of stylesheet URLs to add to the node.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAllStylesheets(String... elements) {
        getNode().getStylesheets()
                 .addAll(elements);
        return getConfigurator();
    }

    /**
     * Adds all the stylesheets in the specified collection to the end of the stylesheets' list.
     *
     * @param stylesheets
     *         The collection containing stylesheets to be added.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAllStylesheets(Collection<? extends String> stylesheets) {
        getNode().getStylesheets()
                 .addAll(stylesheets);
        return getConfigurator();
    }

    /**
     * Inserts all the stylesheets in the specified collection into the stylesheets' list, starting at the specified position.
     *
     * @param index
     *         The index at which the collection should be inserted.
     * @param stylesheets
     *         The collection containing stylesheets to be added.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAllStylesheets(int index, Collection<? extends String> stylesheets) {
        getNode().getStylesheets()
                 .addAll(index, stylesheets);
        return getConfigurator();
    }

    //endregion Add EFXStyle Functions

    //region Remove EFXStyle Functions
    //*****************************************************************
    // Remove EFXStyle Functions
    //*****************************************************************

    /**
     * Removes the first stylesheet from the node's stylesheets' list. This method is useful when you want to maintain a dynamic list of stylesheets and need to remove the oldest (first added) stylesheet.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeFirstStylesheet() {
        getNode().getStylesheets()
                 .removeFirst();
        return getConfigurator();
    }

    /**
     * Removes the last stylesheet from the node's stylesheets' list. Useful for removing the most recently added stylesheet without affecting others.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeLastStylesheet() {
        if (!getNode().getStylesheets()
                      .isEmpty()) {
            getNode().getStylesheets()
                     .removeLast();
        }
        return getConfigurator();
    }

    /**
     * Removes a specific stylesheet from the node's stylesheets' list. If the stylesheet is present, it will be removed; otherwise, the list remains unchanged.
     *
     * @param stylesheet
     *         The URL of the stylesheet to be removed.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeStylesheet(String stylesheet) {
        getNode().getStylesheets()
                 .remove(stylesheet);
        return getConfigurator();
    }

    /**
     * Removes a range of stylesheets from the node's stylesheets' list. This method can be used to bulk remove stylesheets, specified by their indices.
     *
     * @param from
     *         The start index, inclusive.
     * @param to
     *         The end index, exclusive.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeStylesheets(int from, int to) {
        getNode().getStylesheets()
                 .subList(from, to)
                 .clear();
        return getConfigurator();
    }

    /**
     * Removes stylesheets that match a given predicate. This allows for conditional removal of stylesheets based on their properties, such as URL patterns.
     *
     * @param filter
     *         A predicate to apply to each stylesheet.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeStylesheetsIf(Predicate<? super String> filter) {
        getNode().getStylesheets()
                 .removeIf(filter);
        return getConfigurator();
    }

    /**
     * Removes all occurrences of the specified stylesheets from the node's stylesheets' list. If a stylesheet is listed multiple times, all its instances will be removed.
     *
     * @param stylesheets
     *         An array of stylesheet URLs to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeAllStylesheets(String... stylesheets) {
        getNode().getStylesheets()
                 .removeAll(stylesheets);
        return getConfigurator();
    }

    /**
     * Removes all occurrences of the stylesheets present in the specified collection from the node's stylesheets' list. Each matching stylesheet in the list is removed.
     *
     * @param c
     *         A collection containing stylesheet URLs to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeAllStylesheets(Collection<? extends String> c) {
        getNode().getStylesheets()
                 .removeAll(c);
        return getConfigurator();
    }

    //endregion Remove EFXStyle Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Replaces the stylesheet at the specified position in the stylesheets' list with the specified stylesheet.
     *
     * @param index
     *         The index of the stylesheet to replace.
     * @param element
     *         The stylesheet URL to be stored at the specified position.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setStylesheets(int index, String element) {
        getNode().getStylesheets()
                 .set(index, element);
        return getConfigurator();
    }

    /**
     * Replaces each element of the stylesheets' list with the result of applying the operator to that element.
     *
     * @param operator
     *         The operator to apply to each element.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T replaceAllStylesheets(UnaryOperator<String> operator) {
        getNode().getStylesheets()
                 .replaceAll(operator);
        return getConfigurator();
    }

    /**
     * Sets all the node's stylesheets to the specified elements.
     *
     * @param elements
     *         The elements to be stored in the stylesheets' list.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setAllStylesheets(String... elements) {
        getNode().getStylesheets()
                 .setAll(elements);
        return getConfigurator();
    }

    /**
     * Sets all the node's stylesheets to the elements in the specified collection.
     *
     * @param col
     *         The collection containing elements to be stored in the stylesheets' list.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setAllStylesheets(Collection<? extends String> col) {
        getNode().getStylesheets()
                 .setAll(col);
        return getConfigurator();
    }

    //endregion Set Functions
}