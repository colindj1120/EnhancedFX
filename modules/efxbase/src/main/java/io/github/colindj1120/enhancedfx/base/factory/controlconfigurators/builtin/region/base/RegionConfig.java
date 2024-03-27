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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.region.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.parent.base.ParentConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Shape;

/**
 * Offers comprehensive configuration capabilities for {@link Region} nodes within JavaFX.
 *
 * <p>This interface extends {@code ParentConfig<T>}, enabling advanced customization of Region-based UI components such as Panes, Charts, and Controls.</p>
 *
 * <p>Through {@code RegionConfig}, developers can fine-tune the appearance and layout of Region nodes by adjusting properties like padding, background, and borders. It also facilitates dynamic UI updates via
 * property bindings and listeners for property changes and invalidations, ensuring that the UI adapts to data or state changes efficiently.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *   <li><b>Layout Customization:</b> Modify snap-to-pixel, padding, and size constraints to control layout behavior.</li>
 *   <li><b>Appearance Modification:</b> Change the background, border, and shape to customize the look.</li>
 *   <li><b>Dynamic Adaptation:</b> Bind properties to observable values for real-time UI updates and add listeners to react to property changes.</li>
 *   <li><b>Performance Optimization:</b> Configure properties like cacheShape to improve rendering performance.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * Here's a simple example demonstrating how to configure a {@link Pane} using an implementation of {@code RegionConfig}:
 * <pre>{@code
 * RegionConfig<PaneConfigurator> paneConfigurator = new PaneConfigurator(pane);
 * paneConfigurator.setPadding(new Insets(10))
 *                 .setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)))
 *                 .setSnapToPixel(true)
 *                 .bindWidthProperty(viewModel.widthProperty())
 *                 .addBackgroundChangeListener((observable, oldValue, newValue) -> System.out.println("Background changed"));
 * }</pre>
 *
 * <p>In this example, a {@code Pane} is configured with padding, a blue background, snap-to-pixel enabled, and a binding for its width property. Additionally, a background change listener is added to
 * monitor changes.</p>
 *
 * @param <T>
 *         The type of the configurator extending {@code ConfiguratorBase}, enabling a fluent API style of configuration
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ParentConfig
 * @see ConfiguratorBase
 * @see Region
 */
@SuppressWarnings("UnusedReturnValue")
public interface RegionConfig<T extends ConfiguratorBase> extends ParentConfig<T> {
    /*
     * Methods Available:
     *  - Region getNode()
     *
     * Add Listener Functions
     *  - T addSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addSnapToPixelInvalidationListener(InvalidationListener invalidationListener)
     *  - T addPaddingChangeListener(ChangeListener<? super Insets> changeListener)
     *  - T addPaddingInvalidationListener(InvalidationListener invalidationListener)
     *  - T addBackgroundChangeListener(ChangeListener<? super Background> changeListener)
     *  - T addBackgroundInvalidationListener(InvalidationListener invalidationListener)
     *  - T addBorderChangeListener(ChangeListener<? super Border> changeListener)
     *  - T addBorderInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener)
     *  - T addOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener)
     *  - T addInsetChangeListener(ChangeListener<? super Insets> changeListener)
     *  - T addInsetInvalidationListener(InvalidationListener invalidationListener)
     *  - T addWidthChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addWidthInvalidationListener(InvalidationListener invalidationListener)
     *  - T addMinWidthChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addMinWidthInvalidationListener(InvalidationListener invalidationListener)
     *  - T addPrefWidthChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addPrefWidthInvalidationListener(InvalidationListener invalidationListener)
     *  - T addMaxWidthChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addMaxWidthInvalidationListener(InvalidationListener invalidationListener)
     *  - T addHeightChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addHeightInvalidationListener(InvalidationListener invalidationListener)
     *  - T addMinHeightChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addMinHeightInvalidationListener(InvalidationListener invalidationListener)
     *  - T addPrefHeightChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addPrefHeightInvalidationListener(InvalidationListener invalidationListener)
     *  - T addMaxHeightChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addMaxHeightInvalidationListener(InvalidationListener invalidationListener)
     *  - T addShapeChangeListener(ChangeListener<? super Shape> changeListener)
     *  - T addShapeInvalidationListener(InvalidationListener invalidationListener)
     *  - T addScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addScaleShapeInvalidationListener(InvalidationListener invalidationListener)
     *  - T addCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addCenterShapeInvalidationListener(InvalidationListener invalidationListener)
     *  - T addCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addCacheShapeInvalidationListener(InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removeSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeSnapToPixelInvalidationListener(InvalidationListener invalidationListener)
     *  - T removePaddingChangeListener(ChangeListener<? super Insets> changeListener)
     *  - T removePaddingInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeBackgroundChangeListener(ChangeListener<? super Background> changeListener)
     *  - T removeBackgroundInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeBorderChangeListener(ChangeListener<? super Border> changeListener)
     *  - T removeBorderInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener)
     *  - T removeOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeInsetChangeListener(ChangeListener<? super Insets> changeListener)
     *  - T removeInsetInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeWidthChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeWidthInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeMinWidthChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeMinWidthInvalidationListener(InvalidationListener invalidationListener)
     *  - T removePrefWidthChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removePrefWidthInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeMaxWidthChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeMaxWidthInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeHeightChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeHeightInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeMinHeightChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeMinHeightInvalidationListener(InvalidationListener invalidationListener)
     *  - T removePrefHeightChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removePrefHeightInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeMaxHeightChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeMaxHeightInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeShapeChangeListener(ChangeListener<? super Shape> changeListener)
     *  - T removeShapeInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeScaleShapeInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeCenterShapeInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeCacheShapeInvalidationListener(InvalidationListener invalidationListener)
     *
     * Bind Functions
     *  - T bindSnapToPixelProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindSnapToPixelProperty()
     *  - T bindBidirectionalSnapToPixelProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalSnapToPixelProperty(Property<Boolean> otherProperty)
     *  - T bindPaddingProperty(ObservableValue<? extends Insets> observableValue)
     *  - T unbindPaddingProperty()
     *  - T bindBidirectionalPaddingProperty(Property<Insets> otherProperty)
     *  - T unbindBidirectionalPaddingProperty(Property<Insets> otherProperty)
     *  - T bindBackgroundProperty(ObservableValue<? extends Background> observableValue)
     *  - T unbindBackgroundProperty()
     *  - T bindBidirectionalBackgroundProperty(Property<Background> otherProperty)
     *  - T unbindBidirectionalBackgroundProperty(Property<Background> otherProperty)
     *  - T bindBorderProperty(ObservableValue<? extends Border> observableValue)
     *  - T unbindBorderProperty()
     *  - T bindBidirectionalBorderProperty(Property<Border> otherProperty)
     *  - T unbindBidirectionalBorderProperty(Property<Border> otherProperty)
     *  - T bindOpaqueInsetsProperty(ObservableValue<? extends Insets> observableValue)
     *  - T unbindOpaqueInsetsProperty()
     *  - T bindBidirectionalOpaqueInsetsProperty(Property<Insets> otherProperty)
     *  - T unbindBidirectionalOpaqueInsetsProperty(Property<Insets> otherProperty)
     *  - T bindMinWidthProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindMinWidthProperty()
     *  - T bindBidirectionalMinWidthProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalMinWidthProperty(Property<Number> otherProperty)
     *  - T bindPrefWidthProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindPrefWidthProperty()
     *  - T bindBidirectionalPrefWidthProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalPrefWidthProperty(Property<Number> otherProperty)
     *  - T bindMaxWidthProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindMaxWidthProperty()
     *  - T bindBidirectionalMaxWidthProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalMaxWidthProperty(Property<Number> otherProperty)
     *  - T bindMinHeightProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindMinHeightProperty()
     *  - T bindBidirectionalMinHeightProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalMinHeightProperty(Property<Number> otherProperty)
     *  - T bindPrefHeightProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindPrefHeightProperty()
     *  - T bindBidirectionalPrefHeightProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalPrefHeightProperty(Property<Number> otherProperty)
     *  - T bindMaxHeightProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindMaxHeightProperty()
     *  - T bindBidirectionalMaxHeightProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalMaxHeightProperty(Property<Number> otherProperty)
     *  - T bindShapeProperty(ObservableValue<? extends Shape> observableValue)
     *  - T unbindShapeProperty()
     *  - T bindBidirectionalShapeProperty(Property<Shape> otherProperty)
     *  - T unbindBidirectionalShapeProperty(Property<Shape> otherProperty)
     *  - T bindScaleShapeProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindScaleShapeProperty()
     *  - T bindBidirectionalScaleShapeProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalScaleShapeProperty(Property<Boolean> otherProperty)
     *  - T bindCenterShapeProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindCenterShapeProperty()
     *  - T bindBidirectionalCenterShapeProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalCenterShapeProperty(Property<Boolean> otherProperty)
     *  - T bindCacheShapeProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindCacheShapeProperty()
     *  - T bindBidirectionalCacheShapeProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalCacheShapeProperty(Property<Boolean> otherProperty)
     *
     * Set Functions
     *  - T setSnapToPixel(boolean value)
     *  - T setPadding(Insets value)
     *  - T setBackground(Background value)
     *  - T setBorder(Border value)
     *  - T setOpaqueInsets(Insets value)
     *  - T setInsets(Insets value)
     *  - T setWidth(Number value)
     *  - T setMinWidth(Number value)
     *  - T setPrefWidth(Number value)
     *  - T setMaxWidth(Number value)
     *  - T setHeight(Number value)
     *  - T setMinHeight(Number value)
     *  - T setPrefHeight(Number value)
     *  - T setMaxHeight(Number value)
     *  - T setShape(Shape value)
     *  - T setScaleShape(boolean value)
     *  - T setCenterShape(boolean value)
     *  - T setCacheShape(boolean value)
     */

    /**
     * Returns the {@link Region} node associated with this configurator.
     *
     * <p>This method provides access to the region node that is being configured, offering a foundation for further customization and functionality enhancement.</p>
     *
     * @return The current {@link Region} associated with the current configurator instance
     */
    @Override
    Region getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to listen for changes to the snapToPixel property. This listener is notified whenever the snapToPixel status of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().snapToPixelProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the snapToPixel property. This listener is notified whenever the snapToPixel property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        getNode().snapToPixelProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the padding property. This listener is notified whenever the padding of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().paddingProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the padding property. This listener is notified whenever the padding property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addPaddingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().paddingProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the background property. This listener is notified whenever the background of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        getNode().backgroundProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the background property. This listener is notified whenever the background property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        getNode().backgroundProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the border property. This listener is notified whenever the border of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addBorderChangeListener(ChangeListener<? super Border> changeListener) {
        getNode().borderProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the border property. This listener is notified whenever the border property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addBorderInvalidationListener(InvalidationListener invalidationListener) {
        getNode().borderProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the opaqueInsets property. This listener is notified whenever the opaque insets of the node change.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().opaqueInsetsProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the opaqueInsets property. This listener is notified whenever the opaqueInsets property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().opaqueInsetsProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the insets property. This listener is notified whenever the insets of the node change.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().insetsProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the insets' property. This listener is notified whenever the insets' property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addInsetInvalidationListener(InvalidationListener invalidationListener) {
        getNode().insetsProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the width property. This listener is notified whenever the width of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().widthProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the width property. This listener is notified whenever the width property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().widthProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the minWidth property. This listener is notified whenever the minimum width of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().minWidthProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the minWidth property. This listener is notified whenever the minWidth property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().minWidthProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the prefWidth property. This listener is notified whenever the preferred width of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addPrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefWidthProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the prefWidth property. This listener is notified whenever the prefWidth property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addPrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefWidthProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the maxWidth property. This listener is notified whenever the maximum width of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().maxWidthProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the maxWidth property. This listener is notified whenever the maxWidth property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().maxWidthProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the height property. This listener is notified whenever the height of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().heightProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the height property. This listener is notified whenever the height property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().heightProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the minHeight property. This listener is notified whenever the minimum height of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().minHeightProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the minHeight property. This listener is notified whenever the minHeight property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().minHeightProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the prefHeight property. This listener is notified whenever the preferred height of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addPrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefHeightProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the prefHeight property. This listener is notified whenever the prefHeight property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addPrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefHeightProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the maxHeight property. This listener is notified whenever the maximum height of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().maxHeightProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the maxHeight property. This listener is notified whenever the maxHeight property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().maxHeightProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the shape property. This listener is notified whenever the shape of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        getNode().shapeProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the shape property. This listener is notified whenever the shape property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().shapeProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the scaleShape property. This listener is notified whenever the scaleShape status of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().scaleShapeProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the scaleShape property. This listener is notified whenever the scaleShape property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleShapeProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the centerShape property. This listener is notified whenever the centerShape status of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().centerShapeProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the centerShape property. This listener is notified whenever the centerShape property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().centerShapeProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the cacheShape property. This listener is notified whenever the cacheShape status of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().cacheShapeProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the cacheShape property. This listener is notified whenever the cacheShape property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addCacheShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cacheShapeProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the snapToPixel property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().snapToPixelProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the snapToPixel property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        getNode().snapToPixelProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the padding property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removePaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().paddingProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the padding property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removePaddingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().paddingProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the background property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        getNode().backgroundProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the background property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        getNode().backgroundProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the border property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeBorderChangeListener(ChangeListener<? super Border> changeListener) {
        getNode().borderProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the border property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeBorderInvalidationListener(InvalidationListener invalidationListener) {
        getNode().borderProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the opaqueInsets property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().opaqueInsetsProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the opaqueInsets property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().opaqueInsetsProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the insets property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().insetsProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the insets property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeInsetInvalidationListener(InvalidationListener invalidationListener) {
        getNode().insetsProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the width property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().widthProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the width property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().widthProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the minHeight property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().minHeightProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the minHeight property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().minHeightProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the prefHeight property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removePrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefHeightProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the prefHeight property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removePrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefHeightProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the maxHeight property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().maxHeightProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the maxHeight property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().maxHeightProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the shape property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        getNode().shapeProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the shape property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().shapeProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the scaleShape property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().scaleShapeProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the scaleShape property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleShapeProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the centerShape property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().centerShapeProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the centerShape property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().centerShapeProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the cacheShape property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().cacheShapeProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the cacheShape property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeCacheShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cacheShapeProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Snap To Pixel Property

    /**
     * Binds the snapToPixel property of the node to an observable value. When the observable value changes, the snapToPixel property is automatically updated.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindSnapToPixelProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().snapToPixelProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the snapToPixel property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindSnapToPixelProperty() {
        getNode().snapToPixelProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the snapToPixel property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalSnapToPixelProperty(Property<Boolean> otherProperty) {
        getNode().snapToPixelProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the snapToPixel property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalSnapToPixelProperty(Property<Boolean> otherProperty) {
        getNode().snapToPixelProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Padding Property

    /**
     * Binds the padding property of the node to an observable value. When the observable value changes, the padding of the node is automatically updated.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindPaddingProperty(ObservableValue<? extends Insets> observableValue) {
        getNode().paddingProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the padding property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindPaddingProperty() {
        getNode().paddingProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the padding property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalPaddingProperty(Property<Insets> otherProperty) {
        getNode().paddingProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the padding property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalPaddingProperty(Property<Insets> otherProperty) {
        getNode().paddingProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Background Property

    /**
     * Binds the background property of the node to an observable value. When the observable value changes, the background of the node is automatically updated.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBackgroundProperty(ObservableValue<? extends Background> observableValue) {
        getNode().backgroundProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the background property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBackgroundProperty() {
        getNode().backgroundProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the background property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalBackgroundProperty(Property<Background> otherProperty) {
        getNode().backgroundProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the background property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalBackgroundProperty(Property<Background> otherProperty) {
        getNode().backgroundProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Border Property

    /**
     * Binds the border property of the node to an observable value. When the observable value changes, the border of the node is automatically updated.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBorderProperty(ObservableValue<? extends Border> observableValue) {
        getNode().borderProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the border property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBorderProperty() {
        getNode().borderProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the border property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalBorderProperty(Property<Border> otherProperty) {
        getNode().borderProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the border property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalBorderProperty(Property<Border> otherProperty) {
        getNode().borderProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Opaque Insets Property

    /**
     * Binds the opaqueInsets property of the node to an observable value. When the observable value changes, the opaqueInsets of the node is automatically updated.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindOpaqueInsetsProperty(ObservableValue<? extends Insets> observableValue) {
        getNode().opaqueInsetsProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the opaqueInsets property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindOpaqueInsetsProperty() {
        getNode().opaqueInsetsProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the opaqueInsets property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOpaqueInsetsProperty(Property<Insets> otherProperty) {
        getNode().opaqueInsetsProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the opaqueInsets property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOpaqueInsetsProperty(Property<Insets> otherProperty) {
        getNode().opaqueInsetsProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Min Width Property

    /**
     * Binds the minWidth property of the node to an observable value. The minimum width of the node will automatically update to reflect the observable value's changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindMinWidthProperty(ObservableValue<? extends Number> observableValue) {
        getNode().minWidthProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the minWidth property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindMinWidthProperty() {
        getNode().minWidthProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the minWidth property of the node bidirectionally with another property. The node's minWidth and the other property will update to reflect each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalMinWidthProperty(Property<Number> otherProperty) {
        getNode().minWidthProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the minWidth property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalMinWidthProperty(Property<Number> otherProperty) {
        getNode().minWidthProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Pref Width Property

    /**
     * Binds the prefWidth property of the node to an observable value. The preferred width of the node will automatically update to reflect the observable value's changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindPrefWidthProperty(ObservableValue<? extends Number> observableValue) {
        getNode().prefWidthProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the prefWidth property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindPrefWidthProperty() {
        getNode().prefWidthProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the prefWidth property of the node bidirectionally with another property. The node's prefWidth and the other property will update to reflect each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalPrefWidthProperty(Property<Number> otherProperty) {
        getNode().prefWidthProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the prefWidth property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalPrefWidthProperty(Property<Number> otherProperty) {
        getNode().prefWidthProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Max Width Property

    /**
     * Binds the maxWidth property of the node to an observable value. The maximum width of the node will automatically update to reflect the observable value's changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindMaxWidthProperty(ObservableValue<? extends Number> observableValue) {
        getNode().maxWidthProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the maxWidth property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindMaxWidthProperty() {
        getNode().maxWidthProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the maxWidth property of the node bidirectionally with another property. The node's maxWidth and the other property will update to reflect each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalMaxWidthProperty(Property<Number> otherProperty) {
        getNode().maxWidthProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the maxWidth property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalMaxWidthProperty(Property<Number> otherProperty) {
        getNode().maxWidthProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Min Height Property

    /**
     * Binds the minHeight property of the node to an observable value. The minimum height of the node will automatically update to reflect the observable value's changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindMinHeightProperty(ObservableValue<? extends Number> observableValue) {
        getNode().minHeightProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the minHeight property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindMinHeightProperty() {
        getNode().minHeightProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the minHeight property of the node bidirectionally with another property. The node's minHeight and the other property will update to reflect each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalMinHeightProperty(Property<Number> otherProperty) {
        getNode().minHeightProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the minHeight property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalMinHeightProperty(Property<Number> otherProperty) {
        getNode().minHeightProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Pref Height Property

    /**
     * Binds the prefHeight property of the node to an observable value. The preferred height of the node will automatically update to reflect the observable value's changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindPrefHeightProperty(ObservableValue<? extends Number> observableValue) {
        getNode().prefHeightProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the prefHeight property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindPrefHeightProperty() {
        getNode().prefHeightProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the prefHeight property of the node bidirectionally with another property. The node's prefHeight and the other property will update to reflect each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalPrefHeightProperty(Property<Number> otherProperty) {
        getNode().prefHeightProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the prefHeight property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalPrefHeightProperty(Property<Number> otherProperty) {
        getNode().prefHeightProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Max Height Property

    /**
     * Binds the maxHeight property of the node to an observable value. The maximum height of the node will automatically update to reflect the observable value's changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindMaxHeightProperty(ObservableValue<? extends Number> observableValue) {
        getNode().maxHeightProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the maxHeight property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindMaxHeightProperty() {
        getNode().maxHeightProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the maxHeight property of the node bidirectionally with another property. The node's maxHeight and the other property will update to reflect each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalMaxHeightProperty(Property<Number> otherProperty) {
        getNode().maxHeightProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the maxHeight property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalMaxHeightProperty(Property<Number> otherProperty) {
        getNode().maxHeightProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Shape Property

    /**
     * Binds the shape property of the node to an observable value. The shape of the node will automatically update to reflect the observable value's changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindShapeProperty(ObservableValue<? extends Shape> observableValue) {
        getNode().shapeProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the shape property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindShapeProperty() {
        getNode().shapeProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the shape property of the node bidirectionally with another property. The node's shape and the other property will update to reflect each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalShapeProperty(Property<Shape> otherProperty) {
        getNode().shapeProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the shape property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalShapeProperty(Property<Shape> otherProperty) {
        getNode().shapeProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Scale Shape Property

    /**
     * Binds the scaleShape property of the node to an observable value. Determines whether the shape of the node is scaled along with the node.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindScaleShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().scaleShapeProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the scaleShape property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindScaleShapeProperty() {
        getNode().scaleShapeProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the scaleShape property of the node bidirectionally with another property. Determines whether the shape of the node is scaled along with the node and the other property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalScaleShapeProperty(Property<Boolean> otherProperty) {
        getNode().scaleShapeProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the scaleShape property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalScaleShapeProperty(Property<Boolean> otherProperty) {
        getNode().scaleShapeProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Center Shape Property

    /**
     * Binds the centerShape property of the node to an observable value. Determines whether the shape of the node is centered within the node.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindCenterShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().centerShapeProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the centerShape property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindCenterShapeProperty() {
        getNode().centerShapeProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the centerShape property of the node bidirectionally with another property. Determines whether the shape of the node is centered within the node and the other property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalCenterShapeProperty(Property<Boolean> otherProperty) {
        getNode().centerShapeProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the centerShape property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalCenterShapeProperty(Property<Boolean> otherProperty) {
        getNode().centerShapeProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Cache Shape Property

    /**
     * Binds the cacheShape property of the node to an observable value. Determines whether the shape of the node is cached for performance.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindCacheShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().cacheShapeProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the cacheShape property of the node, stopping it from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindCacheShapeProperty() {
        getNode().cacheShapeProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the cacheShape property of the node bidirectionally with another property. Determines whether the shape of the node is cached for performance and the other property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalCacheShapeProperty(Property<Boolean> otherProperty) {
        getNode().cacheShapeProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the cacheShape property of the node from a bidirectional binding with another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalCacheShapeProperty(Property<Boolean> otherProperty) {
        getNode().cacheShapeProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets whether snap-to-pixel is enabled for the node.
     *
     * @param value
     *         {@code true} to enable snap-to-pixel, {@code false} to disable.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setSnapToPixel(boolean value) {
        getNode().setSnapToPixel(value);
        return getConfigurator();
    }

    /**
     * Sets the padding around the node.
     *
     * @param value
     *         The new padding value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setPadding(Insets value) {
        getNode().setPadding(value);
        return getConfigurator();
    }

    /**
     * Sets the background of the node.
     *
     * @param value
     *         The new background value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setBackground(Background value) {
        getNode().setBackground(value);
        return getConfigurator();
    }

    /**
     * Sets the border of the node.
     *
     * @param value
     *         The new border value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setBorder(Border value) {
        getNode().setBorder(value);
        return getConfigurator();
    }

    /**
     * Sets the opaque insets for the node.
     *
     * @param value
     *         The new opaque insets value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setOpaqueInsets(Insets value) {
        getNode().setOpaqueInsets(value);
        return getConfigurator();
    }

    /**
     * Sets the minimum width of the node.
     *
     * @param value
     *         The new minimum width value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setMinWidth(double value) {
        getNode().setMinWidth(value);
        return getConfigurator();
    }

    /**
     * Sets the preferred width of the node.
     *
     * @param value
     *         The new preferred width value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setPrefWidth(double value) {
        getNode().setPrefWidth(value);
        return getConfigurator();
    }

    /**
     * Sets the maximum width of the node.
     *
     * @param value
     *         The new maximum width value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setMaxWidth(double value) {
        getNode().setMaxWidth(value);
        return getConfigurator();
    }

    /**
     * Sets the minimum height of the node.
     *
     * @param value
     *         The new minimum height value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setMinHeight(double value) {
        getNode().setMinHeight(value);
        return getConfigurator();
    }

    /**
     * Sets the preferred height of the node.
     *
     * @param value
     *         The new preferred height value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setPrefHeight(double value) {
        getNode().setPrefHeight(value);
        return getConfigurator();
    }

    /**
     * Sets the maximum height of the node.
     *
     * @param value
     *         The new maximum height value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setMaxHeight(double value) {
        getNode().setMaxHeight(value);
        return getConfigurator();
    }

    /**
     * Sets the minimum size of the node.
     *
     * @param width
     *         The new minimum width value.
     * @param height
     *         The new minimum height value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setMinSize(double width, double height) {
        getNode().setMinSize(width, height);
        return getConfigurator();
    }

    /**
     * Sets the preferred size of the node.
     *
     * @param width
     *         The new preferred width value.
     * @param height
     *         The new preferred height value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setPrefSize(double width, double height) {
        getNode().setPrefSize(width, height);
        return getConfigurator();
    }

    /**
     * Sets the maximum size of the node.
     *
     * @param width
     *         The new maximum width value.
     * @param height
     *         The new maximum height value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setMaxSize(double width, double height) {
        getNode().setMaxSize(width, height);
        return getConfigurator();
    }

    /**
     * Sets the shape of the node.
     *
     * @param value
     *         The new shape value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setShape(Shape value) {
        getNode().setShape(value);
        return getConfigurator();
    }

    /**
     * Sets whether the shape of the node is scaled along with the node.
     *
     * @param value
     *         {@code true} to scale the shape, {@code false} to not scale.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setScaleShape(boolean value) {
        getNode().setScaleShape(value);
        return getConfigurator();
    }

    /**
     * Sets whether the shape of the node is centered within the node.
     *
     * @param value
     *         {@code true} to center the shape, {@code false} to not center.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setCenterShape(boolean value) {
        getNode().setCenterShape(value);
        return getConfigurator();
    }

    /**
     * Sets whether the caching of the node's shape is enabled.
     *
     * <p>When enabled, the shape of the node is cached to improve performance. This is particularly useful for complex shapes or when performing animations.</p>
     *
     * @param value
     *         {@code true} to enable caching of the shape, {@code false} to disable it.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setCacheShape(boolean value) {
        getNode().setCacheShape(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
