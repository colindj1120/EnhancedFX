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
package io.github.colindj1120.enhancedfx.base.factory.configurators.base.interfaces.layout;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Region;
import javafx.scene.shape.Shape;

/**
 * Provides a comprehensive interface for configuring {@link Region} properties, including support for adding and removing listeners,
 * binding and unbinding properties, and setting various region-specific attributes. This interface facilitates the dynamic
 * customization and responsive design of UI components based on the {@link Region} class, enabling developers to create adaptable and
 * interactive JavaFX applications.
 *
 * <p>Key functionalities provided by the {@code RegionConfig} interface include:</p>
 * <ul>
 *     <li>Dynamic addition and removal of property change listeners and invalidation listeners for various {@link Region}
 *     properties.</li>
 *     <li>Capability to bind properties to observable values, enabling automatic UI updates in response to data changes.</li>
 *     <li>Support for bidirectional binding, allowing two properties to synchronize their values automatically.</li>
 *     <li>Convenience methods for setting properties such as snapToPixel, padding, background, border, and more.</li>
 *     <li>Advanced control over region layout parameters including minimum, preferred, and maximum sizes.</li>
 * </ul>
 *
 * <p>This interface is designed to be implemented by configurator classes that aim to enhance the flexibility and usability of
 * {@link Region} nodes. It serves as a foundational component of the EnhancedFX library, which seeks to provide
 * extended functionality and utilities beyond the core JavaFX library.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Region
 * @see Property
 * @see ObservableValue
 */
public interface RegionConfig {
    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a change listener to add when the snapToPixel property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the snapToPixel property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addSnapToPixelInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the padding property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addPaddingChangeListener(ChangeListener<? super Insets> changeListener);

    /**
     * Adds an invalidation listener to add when the padding property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addPaddingInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the background property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addBackgroundChangeListener(ChangeListener<? super Background> changeListener);

    /**
     * Adds an invalidation listener to add when the background property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addBackgroundInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the border property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addBorderChangeListener(ChangeListener<? super Border> changeListener);

    /**
     * Adds an invalidation listener to add when the border property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addBorderInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the opaque insets property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener);

    /**
     * Adds an invalidation listener to add when the opaque insets property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the inset property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addInsetChangeListener(ChangeListener<? super Insets> changeListener);

    /**
     * Adds an invalidation listener to add when the inset property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addInsetInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the width property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addWidthChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to add when the width property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addWidthInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the minWidth property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addMinWidthChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to add when the minWidth property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addMinWidthInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the prefWidth property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addPrefWidthChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to add when the prefWidth property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addPrefWidthInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the maxWidth property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addMaxWidthChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to add when the maxWidth property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addMaxWidthInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the height property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addHeightChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to add when the height property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addHeightInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the minHeight property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addMinHeightChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to add when the minHeight property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addMinHeightInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the prefHeight property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addPrefHeightChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to add when the prefHeight property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addPrefHeightInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the maxHeight property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addMaxHeightChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to add when the maxHeight property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addMaxHeightInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the shape property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addShapeChangeListener(ChangeListener<? super Shape> changeListener);

    /**
     * Adds an invalidation listener to add when the shape property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addShapeInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the scaleShape property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the scaleShape property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addScaleShapeInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the centerShape property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the centerShape property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addCenterShapeInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the cacheShape property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the cacheShape property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    RegionConfig addCacheShapeInvalidationListener(InvalidationListener invalidationListener);

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a change listener from the snapToPixel property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the snapToPixel property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeSnapToPixelInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the padding property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removePaddingChangeListener(ChangeListener<? super Insets> changeListener);

    /**
     * Removes an invalidation listener from the padding property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removePaddingInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the background property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeBackgroundChangeListener(ChangeListener<? super Background> changeListener);

    /**
     * Removes an invalidation listener from the background property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeBackgroundInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the border property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeBorderChangeListener(ChangeListener<? super Border> changeListener);

    /**
     * Removes an invalidation listener from the border property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeBorderInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the opaqueInsets property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener);

    /**
     * Removes an invalidation listener from the opaqueInsets property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the inset property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeInsetChangeListener(ChangeListener<? super Insets> changeListener);

    /**
     * Removes an invalidation listener from the inset property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeInsetInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the width property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeWidthChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the width property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeWidthInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the minWidth property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeMinWidthChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the minWidth property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeMinWidthInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the prefWidth property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removePrefWidthChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the prefWidth property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removePrefWidthInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the maxWidth property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeMaxWidthChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the maxWidth property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeMaxWidthInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the height property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeHeightChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the height property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeHeightInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the minHeight property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeMinHeightChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the minHeight property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeMinHeightInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the prefHeight property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removePrefHeightChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the prefHeight property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removePrefHeightInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the maxHeight property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeMaxHeightChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the maxHeight property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeMaxHeightInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the shape property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeShapeChangeListener(ChangeListener<? super Shape> changeListener);

    /**
     * Removes an invalidation listener from the shape property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeShapeInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the scaleShape property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the scaleShape property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeScaleShapeInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the centerShape property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the centerShape property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeCenterShapeInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the cacheShape property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the cacheShape property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    RegionConfig removeCacheShapeInvalidationListener(InvalidationListener invalidationListener);

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // SnapToPixelProperty

    /**
     * Binds the snap-to-pixel property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindSnapToPixelProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the snap-to-pixel property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindSnapToPixelProperty();

    /**
     * Binds the snap-to-pixel property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalSnapToPixelProperty(Property<Boolean> property);

    /**
     * Unbinds the bidirectional binding of the snap-to-pixel property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalSnapToPixelProperty(Property<Boolean> property);

    //Padding Property

    /**
     * Binds the padding property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindPaddingProperty(ObservableValue<? extends Insets> observableValue);

    /**
     * Unbinds the padding property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindPaddingProperty();

    /**
     * Binds the padding property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalPaddingProperty(Property<Insets> property);

    /**
     * Unbinds the bidirectional binding of the padding property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalPaddingProperty(Property<Insets> property);

    //Background Property

    /**
     * Binds the background property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBackgroundProperty(ObservableValue<? extends Background> observableValue);

    /**
     * Unbinds the background property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBackgroundProperty();

    /**
     * Binds the background property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalBackgroundProperty(Property<Background> property);

    /**
     * Unbinds the bidirectional binding of the background property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalBackgroundProperty(Property<Background> property);

    //Border Property

    /**
     * Binds the border property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBorderProperty(ObservableValue<? extends Border> observableValue);

    /**
     * Unbinds the border property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBorderProperty();

    /**
     * Binds the border property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalBorderProperty(Property<Border> property);

    /**
     * Unbinds the bidirectional binding of the border property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalBorderProperty(Property<Border> property);

    // OpaqueInsetsProperty

    /**
     * Binds the opaque insets property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindOpaqueInsetsProperty(ObservableValue<? extends Insets> observableValue);

    /**
     * Unbinds the opaque insets property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindOpaqueInsetsProperty();

    /**
     * Binds the opaque insets property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalOpaqueInsetsProperty(Property<Insets> property);

    /**
     * Unbinds the bidirectional binding of the opaque insets property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalOpaqueInsetsProperty(Property<Insets> property);

    //MinWidth Property

    /**
     * Binds the min width property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindMinWidthProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the min width property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindMinWidthProperty();

    /**
     * Binds the min width property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalMinWidthProperty(Property<Number> property);

    /**
     * Unbinds the bidirectional binding of the min width property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalMinWidthProperty(Property<Number> property);

    //PrefWidth Property

    /**
     * Binds the pref width property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindPrefWidthProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the pref width property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindPrefWidthProperty();

    /**
     * Binds the pref width property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalPrefWidthProperty(Property<Number> property);

    /**
     * Unbinds the bidirectional binding of the pref width property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalPrefWidthProperty(Property<Number> property);

    //MaxWidth Property

    /**
     * Binds the max width property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindMaxWidthProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the max width property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindMaxWidthProperty();

    /**
     * Binds the max width property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalMaxWidthProperty(Property<Number> property);

    /**
     * Unbinds the bidirectional binding of the max width property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalMaxWidthProperty(Property<Number> property);

    //MinHeight Property

    /**
     * Binds the min height property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindMinHeightProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the min height property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindMinHeightProperty();

    /**
     * Binds the min height property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalMinHeightProperty(Property<Number> property);

    /**
     * Unbinds the bidirectional binding of the min height property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalMinHeightProperty(Property<Number> property);

    // PrefHeightProperty

    /**
     * Binds the pref height property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindPrefHeightProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the pref height property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindPrefHeightProperty();

    /**
     * Binds the pref height property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalPrefHeightProperty(Property<Number> property);

    /**
     * Unbinds the bidirectional binding of the pref height property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalPrefHeightProperty(Property<Number> property);

    //MaxHeight Property

    /**
     * Binds the max height property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindMaxHeightProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the max height property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindMaxHeightProperty();

    /**
     * Binds the max height property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalMaxHeightProperty(Property<Number> property);

    /**
     * Unbinds the bidirectional binding of the max height property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalMaxHeightProperty(Property<Number> property);

    //Shape Property

    /**
     * Binds the shape property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindShapeProperty(ObservableValue<? extends Shape> observableValue);

    /**
     * Unbinds the shape property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindShapeProperty();

    /**
     * Binds the shape property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalShapeProperty(Property<Shape> property);

    /**
     * Unbinds the bidirectional binding of the shape property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalShapeProperty(Property<Shape> property);

    //Scale Shape Property

    /**
     * Binds the scale shape property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindScaleShapeProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the scale shape property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindScaleShapeProperty();

    /**
     * Binds the scale shape property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalScaleShapeProperty(Property<Boolean> property);

    /**
     * Unbinds the bidirectional binding of the scale shape property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalScaleShapeProperty(Property<Boolean> property);

    //Center Shape Property

    /**
     * Binds the center shape property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindCenterShapeProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the center shape property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindCenterShapeProperty();

    /**
     * Binds the center shape property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalCenterShapeProperty(Property<Boolean> property);

    /**
     * Unbinds the bidirectional binding of the center shape property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalCenterShapeProperty(Property<Boolean> property);

    //Cache Shape Property

    /**
     * Binds the cache shape property to an observable value.
     *
     * @param observableValue
     *         the observable value to bind to
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindCacheShapeProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the cache shape property.
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindCacheShapeProperty();

    /**
     * Binds the cache shape property bidirectionally with another property.
     *
     * @param property
     *         the property to bind with
     *
     * @return the current instance for method chaining
     */
    RegionConfig bindBidirectionalCacheShapeProperty(Property<Boolean> property);

    /**
     * Unbinds the bidirectional binding of the cache shape property.
     *
     * @param property
     *         the property to unbind
     *
     * @return the current instance for method chaining
     */
    RegionConfig unbindBidirectionalCacheShapeProperty(Property<Boolean> property);

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the snap-to-pixel property.
     *
     * @param value
     *         the new value for snap-to-pixel
     *
     * @return the current instance for method chaining
     */
    RegionConfig setSnapToPixel(boolean value);

    /**
     * Sets the padding.
     *
     * @param value
     *         the new padding value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setPadding(Insets value);

    /**
     * Sets the background.
     *
     * @param value
     *         the new background value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setBackground(Background value);

    /**
     * Sets the border.
     *
     * @param value
     *         the new border value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setBorder(Border value);

    /**
     * Sets the opaque insets.
     *
     * @param value
     *         the new opaque insets value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setOpaqueInsets(Insets value);

    /**
     * Sets the minimum width.
     *
     * @param value
     *         the new minimum width value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setMinWidth(double value);

    /**
     * Sets the preferred width.
     *
     * @param value
     *         the new preferred width value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setPrefWidth(double value);

    /**
     * Sets the maximum width.
     *
     * @param value
     *         the new maximum width value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setMaxWidth(double value);

    /**
     * Sets the minimum height.
     *
     * @param value
     *         the new minimum height value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setMinHeight(double value);

    /**
     * Sets the preferred height.
     *
     * @param value
     *         the new preferred height value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setPrefHeight(double value);

    /**
     * Sets the maximum height.
     *
     * @param value
     *         the new maximum height value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setMaxHeight(double value);

    /**
     * Sets the minimum size.
     *
     * @param width
     *         the new minimum width
     * @param height
     *         the new minimum height
     *
     * @return the current instance for method chaining
     */
    RegionConfig setMinSize(double width, double height);

    /**
     * Sets the preferred size.
     *
     * @param width
     *         the new preferred width
     * @param height
     *         the new preferred height
     *
     * @return the current instance for method chaining
     */
    RegionConfig setPrefSize(double width, double height);

    /**
     * Sets the maximum size.
     *
     * @param width
     *         the new maximum width
     * @param height
     *         the new maximum height
     *
     * @return the current instance for method chaining
     */
    RegionConfig setMaxSize(double width, double height);

    /**
     * Sets the shape.
     *
     * @param value
     *         the new shape value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setShape(Shape value);

    /**
     * Sets the scaleShape property.
     *
     * @param value
     *         the new scaleShape value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setScaleShape(boolean value);

    /**
     * Sets the centerShape property.
     *
     * @param value
     *         the new centerShape value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setCenterShape(boolean value);

    /**
     * Sets the cacheShape property.
     *
     * @param value
     *         the new cacheShape value
     *
     * @return the current instance for method chaining
     */
    RegionConfig setCacheShape(boolean value);

    //endregion Set Functions
}
