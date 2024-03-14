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
package io.github.colindj1120.enhancedfx.controls.factory.configurators.layout;

import io.github.colindj1120.enhancedfx.controls.factory.configurators.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.base.interfaces.layout.RegionConfig;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.scene.ParentConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Region;
import javafx.scene.shape.Shape;

/**
 * Provides a comprehensive configuration utility for {@link Region} objects within JavaFX applications implementing the functionality
 * contained in {@link RegionConfig}. The {@code RegionConfigurator} class extends the functionality of {@link ParentConfigurator} to
 * offer specialized configuration options tailored to the needs of {@link Region} nodes. It encapsulates a wide range of configuration
 * actions, from basic property settings to advanced layout and styling adjustments, making it an essential tool for JavaFX UI
 * development.
 *
 * <p>
 * This configurator facilitates the fluent API design pattern, enabling developers to chain configuration methods in a clear and
 * concise manner. It supports dynamic binding and unbinding of properties, addition and removal of various event listeners, and
 * application of styles and transformations, thereby providing a powerful mechanism for programmatically customizing region behavior
 * and appearance.
 * </p>
 *
 * <h2>Capabilities:</h2>>
 * <p>
 * <ul>
 *   <li>Setting size constraints (minimum, preferred, and maximum sizes) to control the sizing behavior of the region.</li>
 *   <li>Configuring padding, background, and border properties to enhance the visual styling.</li>
 *   <li>Managing snap-to-pixel properties to ensure crisp rendering of the region.</li>
 *   <li>Applying shape transformations and defining clipping shapes to achieve complex visual effects.</li>
 *   <li>Binding region properties to observable values, facilitating dynamic UI updates in response to data changes.</li>
 * </ul>
 * <p>
 * Additionally, it extends the base functionality to include layout-related actions such as resizing, repositioning, and layout
 * ordering, offering a high degree of control over the spatial arrangement of nodes within the scene graph.
 * </p>
 *
 * <p>
 * By abstracting away the intricacies of direct property manipulation and event handling, the {@code RegionConfigurator} significantly
 * simplifies the process of customizing regions in JavaFX, making it an indispensable tool for developers looking to create highly
 * responsive and aesthetically pleasing user interfaces.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ParentConfigurator
 * @see RegionConfig
 * @see ConfiguratorBase
 */
public abstract class RegionConfigurator extends ParentConfigurator implements RegionConfig {
    /**
     * The {@link Region} instance that is being configured. This field holds a reference to the specific region object upon which
     * configuration methods will act, enabling the modification and customization of its properties and behavior.
     * <p>
     * This private member ensures encapsulation of the region, allowing changes to be made through the configurator's methods rather
     * than direct access, promoting a more structured and maintainable approach to UI customization. The configurator provides a
     * fluent API for configuring various aspects of the region, including its appearance, behavior, and event handling.
     * </p>
     */
    private Region region;

    /**
     * Constructs a new {@code RegionConfigurator} with the specified {@link Region}. This constructor ensures that the provided region
     * is not null and is an instance of {@link Region} class, leveraging a validation method to check these conditions. If the region
     * fails to meet these criteria, an {@link IllegalArgumentException} is thrown, indicating either a null value or a type mismatch.
     * This ensures the integrity and applicability of the configurator to the provided region.
     *
     * @param region
     *         The {@link Region} to be configured by this {@code RegionConfigurator}. Must not be null and must be an instance of
     *         {@link Region}.
     *
     * @throws IllegalArgumentException
     *         if {@code region} is null or not an instance of {@link Region}, ensuring that the configurator is always initialized
     *         with a valid, non-null {@link Region}.
     */
    protected RegionConfigurator(Region region) {
        super(checkNodeNotNullAndInstanceOf(region, Region.class, RegionConfigurator.class, "Constructor"));
        this.region = region;
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
        super.setNode(checkNodeNotNullAndInstanceOf(value, Region.class, RegionConfigurator.class, "setNode"));
        this.region = ((Region) value);
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
    public RegionConfigurator addSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        region.snapToPixelProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        region.snapToPixelProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        region.paddingProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addPaddingInvalidationListener(InvalidationListener invalidationListener) {
        region.paddingProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        region.backgroundProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        region.backgroundProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addBorderChangeListener(ChangeListener<? super Border> changeListener) {
        region.borderProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addBorderInvalidationListener(InvalidationListener invalidationListener) {
        region.borderProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        region.opaqueInsetsProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        region.opaqueInsetsProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        region.insetsProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addInsetInvalidationListener(InvalidationListener invalidationListener) {
        region.insetsProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addWidthChangeListener(ChangeListener<? super Number> changeListener) {
        region.widthProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addWidthInvalidationListener(InvalidationListener invalidationListener) {
        region.widthProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        region.minWidthProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        region.minWidthProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addPrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        region.prefWidthProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addPrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        region.prefWidthProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        region.maxHeightProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        region.maxHeightProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addHeightChangeListener(ChangeListener<? super Number> changeListener) {
        region.heightProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addHeightInvalidationListener(InvalidationListener invalidationListener) {
        region.heightProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        region.minHeightProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        region.minHeightProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addPrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        region.prefHeightProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addPrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        region.prefHeightProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        region.maxHeightProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        region.maxHeightProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        region.shapeProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addShapeInvalidationListener(InvalidationListener invalidationListener) {
        region.shapeProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        region.scaleShapeProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        region.scaleShapeProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        region.centerShapeProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        region.centerShapeProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        region.cacheShapeProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addCacheShapeInvalidationListener(InvalidationListener invalidationListener) {
        region.cacheShapeProperty()
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
    public RegionConfigurator removeSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        region.snapToPixelProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        region.snapToPixelProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removePaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        region.paddingProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removePaddingInvalidationListener(InvalidationListener invalidationListener) {
        region.paddingProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        region.backgroundProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        region.backgroundProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeBorderChangeListener(ChangeListener<? super Border> changeListener) {
        region.borderProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeBorderInvalidationListener(InvalidationListener invalidationListener) {
        region.borderProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        region.opaqueInsetsProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        region.opaqueInsetsProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        region.insetsProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeInsetInvalidationListener(InvalidationListener invalidationListener) {
        region.insetsProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeWidthChangeListener(ChangeListener<? super Number> changeListener) {
        region.widthProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeWidthInvalidationListener(InvalidationListener invalidationListener) {
        region.widthProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        region.minWidthProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        region.minWidthProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removePrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        region.prefWidthProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removePrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        region.prefWidthProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        region.maxWidthProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        region.maxWidthProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeHeightChangeListener(ChangeListener<? super Number> changeListener) {
        region.heightProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeHeightInvalidationListener(InvalidationListener invalidationListener) {
        region.heightProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        region.minHeightProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        region.minHeightProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removePrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        region.prefHeightProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removePrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        region.prefHeightProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        region.maxHeightProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        region.maxHeightProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        region.shapeProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeShapeInvalidationListener(InvalidationListener invalidationListener) {
        region.shapeProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        region.scaleShapeProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        region.scaleShapeProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        region.centerShapeProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        region.centerShapeProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        region.cacheShapeProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeCacheShapeInvalidationListener(InvalidationListener invalidationListener) {
        region.cacheShapeProperty()
              .removeListener(invalidationListener);
        return this;
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindSnapToPixelProperty(ObservableValue<? extends Boolean> observableValue) {
        region.snapToPixelProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindSnapToPixelProperty() {
        region.snapToPixelProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalSnapToPixelProperty(Property<Boolean> property) {
        region.snapToPixelProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalSnapToPixelProperty(Property<Boolean> property) {
        region.snapToPixelProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindPaddingProperty(ObservableValue<? extends Insets> observableValue) {
        region.paddingProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindPaddingProperty() {
        region.paddingProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalPaddingProperty(Property<Insets> property) {
        region.paddingProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalPaddingProperty(Property<Insets> property) {
        region.paddingProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBackgroundProperty(ObservableValue<? extends Background> observableValue) {
        region.backgroundProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBackgroundProperty() {
        region.backgroundProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalBackgroundProperty(Property<Background> property) {
        region.backgroundProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalBackgroundProperty(Property<Background> property) {
        region.backgroundProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBorderProperty(ObservableValue<? extends Border> observableValue) {
        region.borderProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBorderProperty() {
        region.borderProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalBorderProperty(Property<Border> property) {
        region.borderProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalBorderProperty(Property<Border> property) {
        region.borderProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOpaqueInsetsProperty(ObservableValue<? extends Insets> observableValue) {
        region.opaqueInsetsProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOpaqueInsetsProperty() {
        region.opaqueInsetsProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOpaqueInsetsProperty(Property<Insets> property) {
        region.opaqueInsetsProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOpaqueInsetsProperty(Property<Insets> property) {
        region.opaqueInsetsProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindMinWidthProperty(ObservableValue<? extends Number> observableValue) {
        region.minWidthProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindMinWidthProperty() {
        region.minWidthProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalMinWidthProperty(Property<Number> property) {
        region.minWidthProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalMinWidthProperty(Property<Number> property) {
        region.minWidthProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindPrefWidthProperty(ObservableValue<? extends Number> observableValue) {
        region.prefWidthProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindPrefWidthProperty() {
        region.prefWidthProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalPrefWidthProperty(Property<Number> property) {
        region.prefWidthProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalPrefWidthProperty(Property<Number> property) {
        region.prefWidthProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindMaxWidthProperty(ObservableValue<? extends Number> observableValue) {
        region.maxWidthProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindMaxWidthProperty() {
        region.maxWidthProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalMaxWidthProperty(Property<Number> property) {
        region.maxWidthProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalMaxWidthProperty(Property<Number> property) {
        region.maxWidthProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindMinHeightProperty(ObservableValue<? extends Number> observableValue) {
        region.minHeightProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindMinHeightProperty() {
        region.minHeightProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalMinHeightProperty(Property<Number> property) {
        region.minHeightProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalMinHeightProperty(Property<Number> property) {
        region.minHeightProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindPrefHeightProperty(ObservableValue<? extends Number> observableValue) {
        region.prefHeightProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindPrefHeightProperty() {
        region.prefHeightProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalPrefHeightProperty(Property<Number> property) {
        region.prefHeightProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalPrefHeightProperty(Property<Number> property) {
        region.prefHeightProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindMaxHeightProperty(ObservableValue<? extends Number> observableValue) {
        region.maxHeightProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindMaxHeightProperty() {
        region.maxHeightProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalMaxHeightProperty(Property<Number> property) {
        region.maxHeightProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalMaxHeightProperty(Property<Number> property) {
        region.maxHeightProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindShapeProperty(ObservableValue<? extends Shape> observableValue) {
        region.shapeProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindShapeProperty() {
        region.shapeProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalShapeProperty(Property<Shape> property) {
        region.shapeProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalShapeProperty(Property<Shape> property) {
        region.shapeProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindScaleShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        region.scaleShapeProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindScaleShapeProperty() {
        region.scaleShapeProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalScaleShapeProperty(Property<Boolean> property) {
        region.scaleShapeProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalScaleShapeProperty(Property<Boolean> property) {
        region.scaleShapeProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindCenterShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        region.centerShapeProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindCenterShapeProperty() {
        region.centerShapeProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalCenterShapeProperty(Property<Boolean> property) {
        region.centerShapeProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalCenterShapeProperty(Property<Boolean> property) {
        region.centerShapeProperty()
              .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindCacheShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        region.cacheShapeProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindCacheShapeProperty() {
        region.cacheShapeProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalCacheShapeProperty(Property<Boolean> property) {
        region.cacheShapeProperty()
              .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalCacheShapeProperty(Property<Boolean> property) {
        region.cacheShapeProperty()
              .unbindBidirectional(property);
        return this;
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setSnapToPixel(boolean value) {
        region.setSnapToPixel(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setPadding(Insets value) {
        region.setPadding(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setBackground(Background value) {
        region.setBackground(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setBorder(Border value) {
        region.setBorder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOpaqueInsets(Insets value) {
        region.setOpaqueInsets(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setMinWidth(double value) {
        region.setMinWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setPrefWidth(double value) {
        region.setPrefWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setMaxWidth(double value) {
        region.setMaxWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setMinHeight(double value) {
        region.setMinHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setPrefHeight(double value) {
        region.setPrefHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setMaxHeight(double value) {
        region.setMaxHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setMinSize(double width, double height) {
        region.setMinSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setPrefSize(double width, double height) {
        region.setPrefSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setMaxSize(double width, double height) {
        region.setMaxSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setShape(Shape value) {
        region.setShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setScaleShape(boolean value) {
        region.setScaleShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setCenterShape(boolean value) {
        region.setCenterShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setCacheShape(boolean value) {
        region.setCacheShape(value);
        return this;
    }

    //endregion Set Functions
}
