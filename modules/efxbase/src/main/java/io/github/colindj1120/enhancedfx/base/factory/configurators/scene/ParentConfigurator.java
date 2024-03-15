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
package io.github.colindj1120.enhancedfx.base.factory.configurators.scene;

import io.github.colindj1120.enhancedfx.base.factory.configurators.base.interfaces.scene.ParentConfig;
import io.github.colindj1120.enhancedfx.base.factory.configurators.base.ConfiguratorBase;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * The {@code ParentConfigurator} class extends the {@link NodeConfigurator} to provide specialized configuration functionalities for
 * {@link Parent} nodes in JavaFX. It implements the {@link ParentConfig} interface, designed to enhance and streamline the
 * configuration process of JavaFX nodes. This class encapsulates a variety of methods tailored specifically for the setup and
 * customization of {@code Parent} nodes, offering a more intuitive and less error-prone approach to manipulating their properties and
 * behaviors.
 *
 * <p>
 * <h2>Key functionalities provided by the {@code ParentConfigurator} include:</h2>
 * <ul>
 *   <li>Dynamic addition and removal of stylesheets, enabling real-time visual customization.</li>
 *   <li>Manipulation of the layout-related properties, ensuring that child nodes are arranged according to specific requirements.</li>
 *   <li>Binding and unbinding of properties to observable values, allowing for reactive UI updates in response to data changes.</li>
 *   <li>Efficient management of event listeners for layout changes, providing hooks for custom logic execution in response to
 *   layout updates.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The configurator promotes a fluent API design, enabling developers to chain method calls for a cleaner and more readable codebase.
 * It emphasizes safety and flexibility, allowing only properties inherent to the {@code Parent} node to be manipulated while
 * providing the ability to work with any property via custom member functions. This design ensures that the configurator can be
 * easily adapted to various UI development scenarios, from simple layout adjustments to complex UI behavior implementations.
 * </p>
 *
 * <p>
 * {@code ParentConfigurator} serves as a foundational tool for developers looking to leverage the full potential of JavaFX for
 * creating robust, responsive, and visually appealing user interfaces. By abstracting the intricacies of direct property manipulation
 * and event handling, it significantly simplifies JavaFX UI development, making it an indispensable asset in the JavaFX development
 * toolkit.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see NodeConfigurator
 * @see ConfiguratorBase
 * @see ParentConfig
 */
public abstract class ParentConfigurator<T extends NodeConfigurator> extends NodeConfigurator implements ParentConfig<T> {
    /**
     * The {@link Parent} instance that is being configured. This field holds a reference to the specific parent object upon which
     * configuration methods will act, enabling the modification and customization of its properties and behavior.
     * <p>
     * This private member ensures encapsulation of the parent, allowing changes to be made through the configurator's methods rather
     * than direct access, promoting a more structured and maintainable approach to UI customization. The configurator provides a
     * fluent API for configuring various aspects of the parent, including its appearance, behavior, and event handling.
     * </p>
     */
    private Parent parent;

    /**
     * Constructs a new {@code ParentConfigurator} with the specified {@link Parent}. This constructor ensures that the provided parent
     * is not null and is an instance of {@link Parent} class, leveraging a validation method to check these conditions. If the parent
     * fails to meet these criteria, an {@link IllegalArgumentException} is thrown, indicating either a null value or a type mismatch.
     * This ensures the integrity and applicability of the configurator to the provided parent.
     *
     * @param parent
     *         The {@link Parent} to be configured by this {@code ParentConfigurator}. Must not be null and must be an instance of
     *         {@link Parent}.
     *
     * @throws IllegalArgumentException
     *         if {@code parent} is null or not an instance of {@link Parent}, ensuring that the configurator is always initialized
     *         with a valid, non-null {@link Parent}.
     */
    protected ParentConfigurator(Parent parent) {
        super(checkNodeNotNullAndInstanceOf(parent, Parent.class, ParentConfigurator.class, "Constructor"));
        this.parent = parent;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        super.setNode(checkNodeNotNullAndInstanceOf(value, Parent.class, ParentConfigurator.class, "setNode"));
        this.parent = ((Parent) value);
    }

    //endregion Overridden Functions

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        parent.needsLayoutProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        parent.needsLayoutProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        parent.getChildrenUnmodifiable()
              .addListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        parent.getChildrenUnmodifiable()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        parent.getStylesheets()
              .addListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
        parent.getStylesheets()
              .addListener(invalidationListener);
        return this;
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        parent.needsLayoutProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        parent.needsLayoutProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        parent.getChildrenUnmodifiable()
              .removeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        parent.getChildrenUnmodifiable()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        parent.getStylesheets()
              .removeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
        parent.getStylesheets()
              .removeListener(invalidationListener);
        return this;
    }

    //endregion Remove Listener Functions

    //region Layout Functions
    //*****************************************************************
    // Layout Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator requestLayout() {
        parent.requestLayout();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator Layout() {
        parent.layout();
        return this;
    }

    //endregion Layout Functions

    //region Add Stylesheet Functions
    //*****************************************************************
    // Add Stylesheet Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addFirstStylesheet(String stylesheet) {
        parent.getStylesheets()
              .addFirst(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addLastStylesheet(String stylesheet) {
        parent.getStylesheets()
              .addLast(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addStylesheet(String stylesheet) {
        parent.getStylesheets()
              .add(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addStylesheet(int index, String stylesheet) {
        parent.getStylesheets()
              .add(index, stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addAllStylesheets(String... stylesheets) {
        parent.getStylesheets()
              .addAll(stylesheets);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addAllStylesheets(Collection<? extends String> c) {
        parent.getStylesheets()
              .addAll(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator addAllStylesheets(int index, Collection<? extends String> c) {
        parent.getStylesheets()
              .addAll(index, c);
        return this;
    }

    //endregion Add Stylesheet Functions

    //region Remove Stylesheet Functions
    //*****************************************************************
    // Remove Stylesheet Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeFirstStylesheet() {
        parent.getStylesheets()
              .removeFirst();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeLastStylesheet() {
        parent.getStylesheets()
              .removeLast();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeStylesheet(String stylesheet) {
        parent.getStylesheets()
              .remove(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeStylesheets(int from, int to) {
        parent.getStylesheets()
              .remove(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeStylesheetsIf(Predicate<? super String> filter) {
        parent.getStylesheets()
              .removeIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeAllStylesheets(String... stylesheets) {
        parent.getStylesheets()
              .removeAll(stylesheets);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator removeAllStylesheets(Collection<? extends String> c) {
        parent.getStylesheets()
              .removeAll(c);
        return this;
    }

    //endregion Remove Stylesheet Functions
}
