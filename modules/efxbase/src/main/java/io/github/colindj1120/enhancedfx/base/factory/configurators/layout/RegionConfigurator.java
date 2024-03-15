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
package io.github.colindj1120.enhancedfx.base.factory.configurators.layout;

import io.github.colindj1120.enhancedfx.base.factory.configurators.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.configurators.base.interfaces.layout.RegionConfig;
import io.github.colindj1120.enhancedfx.base.factory.configurators.scene.ParentConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.css.PseudoClass;
import javafx.event.Event;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Region;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Transform;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Provides a comprehensive configuration utility for {@link Region} objects within JavaFX applications implementing the functionality contained in {@link RegionConfig}. The {@code RegionConfigurator} class
 * extends the functionality of {@link ParentConfigurator} to offer specialized configuration options tailored to the needs of {@link Region} nodes. It encapsulates a wide range of configuration actions, from
 * basic property settings to advanced layout and styling adjustments, making it an essential tool for JavaFX UI development.
 *
 * <p>
 * This configurator facilitates the fluent API design pattern, enabling developers to chain configuration methods in a clear and concise manner. It supports dynamic binding and unbinding of properties,
 * addition and removal of various event listeners, and application of styles and transformations, thereby providing a powerful mechanism for programmatically customizing region behavior and appearance.
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
 * </p>
 *
 * <p>
 * Additionally, it extends the base functionality to include layout-related actions such as resizing, repositioning, and layout ordering, offering a high degree of control over the spatial arrangement of 
 * nodes within the scene graph.
 * </p>
 *
 * <p>
 * By abstracting away the intricacies of direct property manipulation and event handling, the {@code RegionConfigurator} significantly simplifies the process of customizing regions in JavaFX, making it an 
 * indispensable tool for developers looking to create highly responsive and aesthetically pleasing user interfaces.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ParentConfigurator
 * @see RegionConfig
 * @see ConfiguratorBase
 */
public class RegionConfigurator<T extends ParentConfigurator<T>> extends ParentConfigurator<T> implements RegionConfig {
    /**
     * Creates a new {@code RegionConfigurator} instance for the specified {@link TextField}. This factory method facilitates the creation of a configurator tailored to the provided region, enabling immediate
     * access to configuration methods.
     * <p>
     * This approach abstracts the instantiation process, allowing for fluent and intuitive setup of text fields through method chaining.
     * </p>
     *
     * @param region
     *         The {@link Region} to be configured by the newly created {@code RegionConfigurator}.
     *
     * @return A new instance of {@code RegionConfigurator} linked to the specified region.
     */
    public static RegionConfigurator create(Region region) {
        return new RegionConfigurator(region);
    }

    /**
     * The {@link Region} instance that is being configured. This field holds a reference to the specific region object upon which configuration methods will act, enabling the modification and customization of
     * its properties and behavior.
     * <p>
     * This private member ensures encapsulation of the region, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable approach to
     * UI customization. The configurator provides a fluent API for configuring various aspects of the region, including its appearance, behavior, and event handling.
     * </p>
     */
    private Region region;

    /**
     * Constructs a new {@code RegionConfigurator} with the specified {@link Region}. This constructor ensures that the provided region is not null and is an instance of {@link Region} class, leveraging a
     * validation method to check these conditions. If the region fails to meet these criteria, an {@link IllegalArgumentException} is thrown, indicating either a null value or a type mismatch. This ensures the
     * integrity and applicability of the configurator to the provided region.
     *
     * @param region
     *         The {@link Region} to be configured by this {@code RegionConfigurator}. Must not be null and must be an instance of {@link Region}.
     *
     * @throws IllegalArgumentException
     *         if {@code region} is null or not an instance of {@link Region}, ensuring that the configurator is always initialized with a valid, non-null {@link Region}.
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
    public Region getNode() {
        return this.region;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        super.setNode(checkNodeNotNullAndInstanceOf(value, Region.class, RegionConfigurator.class, "setNode"));
        this.region = ((Region) value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return region.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RegionConfigurator regionConfigurator) {
            return Objects.equals(regionConfigurator.getNode(), this.region);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("RegionConfigurator current region: %s", region.toString());
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

    //********************************************************************************************************//
    //                                                                                                        //
    //                                                                                                        //
    //    Below are the overridden parent class methods that just call super to allow for method chaining.    //
    //                                                                                                        //
    //                                                                                                        //
    //********************************************************************************************************//

    //region NodeConfig Functions
    //*****************************************************************
    // NodeConfig Functions
    //*****************************************************************

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleHelpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleHelpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleRoleDescriptionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleRoleDescriptionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        super.addAccessibleRoleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleRoleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        super.addBlendModeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        super.addBlendModeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addBoundsInLocalChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        super.addBoundsInLocalInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addBoundsInParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        super.addBoundsInParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        super.addCacheHintChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        super.addCacheHintInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCacheChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addCacheInvalidationListener(InvalidationListener invalidationListener) {
        super.addCacheInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addClipChangeListener(ChangeListener<? super Node> changeListener) {
        super.addClipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addClipInvalidationListener(InvalidationListener invalidationListener) {
        super.addClipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        super.addCursorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addCursorInvalidationListener(InvalidationListener invalidationListener) {
        super.addCursorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        super.addDepthTestChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        super.addDepthTestInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addDisabledChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addDisabledInvalidationListener(InvalidationListener invalidationListener) {
        super.addDisabledInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addDisableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addDisableInvalidationListener(InvalidationListener invalidationListener) {
        super.addDisableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.addEffectiveNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.addEffectiveNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        super.addEffectChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addEffectInvalidationListener(InvalidationListener invalidationListener) {
        super.addEffectInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        super.addEventDispatcherChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        super.addEventDispatcherInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addFocusedInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusTraversableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusTraversableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusVisibleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusWithinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusWithinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addHoverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addHoverInvalidationListener(InvalidationListener invalidationListener) {
        super.addHoverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addIdChangeListener(ChangeListener<? super String> changeListener) {
        super.addIdChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addIdInvalidationListener(InvalidationListener invalidationListener) {
        super.addIdInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        super.addInputMethodRequestsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        super.addInputMethodRequestsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addLayoutBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLayoutXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLayoutYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.addLocalToParentTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.addLocalToParentTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.addLocalToSceneTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.addLocalToSceneTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addManagedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addManagedInvalidationListener(InvalidationListener invalidationListener) {
        super.addManagedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addMouseTransparentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        super.addMouseTransparentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.addNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.addNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        super.addOnContextMenuRequestedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnContextMenuRequestedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnDragDetectedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDetectedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragDoneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDoneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragDroppedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDroppedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        super.addOnInputMethodTextChangedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnInputMethodTextChangedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyTypedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyTypedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseClickedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseClickedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseDraggedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDraggedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMousePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMousePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotationFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotationFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotationStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotationStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeDownChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeDownInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeLeftChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeLeftInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeRightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeRightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeUpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeUpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchStationaryChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchStationaryInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        super.addOpacityChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addOpacityInvalidationListener(InvalidationListener invalidationListener) {
        super.addOpacityInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addParentChangeListener(ChangeListener<? super Parent> changeListener) {
        super.addParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addParentInvalidationListener(InvalidationListener invalidationListener) {
        super.addParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addPickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addPickOnBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addPickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.addPickOnBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addPressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        super.addSceneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addSceneInvalidationListener(InvalidationListener invalidationListener) {
        super.addSceneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addRotateChangeListener(ChangeListener<? super Number> changeListener) {
        super.addRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.addRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        super.addRotationAxisChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        super.addRotationAxisInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addScaleXInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addScaleYInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addScaleZInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addStyleChangeListener(ChangeListener<? super String> changeListener) {
        super.addStyleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addStyleInvalidationListener(InvalidationListener invalidationListener) {
        super.addStyleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.addStyleListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addStyleListInvalidationListener(InvalidationListener invalidationListener) {
        super.addStyleListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        super.addTransformListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addTransformListInvalidationListener(InvalidationListener invalidationListener) {
        super.addTransformListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        super.addViewOrderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        super.addViewOrderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.addVisibleInvalidationListener(invalidationListener);
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
    public RegionConfigurator removeAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleHelpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleHelpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleRoleDescriptionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleRoleDescriptionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        super.removeAccessibleRoleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleRoleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        super.removeBlendModeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBlendModeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeBoundsInLocalChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBoundsInLocalInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeBoundsInParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBoundsInParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        super.removeCacheHintChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCacheHintInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCacheChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeCacheInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCacheInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeClipChangeListener(ChangeListener<? super Node> changeListener) {
        super.removeClipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeClipInvalidationListener(InvalidationListener invalidationListener) {
        super.removeClipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        super.removeCursorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeCursorInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCursorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        super.removeDepthTestChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDepthTestInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeDisabledChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeDisabledInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDisabledInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeDisableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeDisableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDisableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.removeEffectiveNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEffectiveNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        super.removeEffectChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeEffectInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEffectInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        super.removeEventDispatcherChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEventDispatcherInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeFocusedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusTraversableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusTraversableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusVisibleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusWithinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusWithinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeHoverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeHoverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeHoverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeIdChangeListener(ChangeListener<? super String> changeListener) {
        super.removeIdChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeIdInvalidationListener(InvalidationListener invalidationListener) {
        super.removeIdInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        super.removeInputMethodRequestsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeInputMethodRequestsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeLayoutBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLayoutXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLayoutYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.removeLocalToParentTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLocalToParentTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.removeLocalToSceneTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLocalToSceneTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeManagedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeManagedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeManagedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeMouseTransparentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMouseTransparentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.removeNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.removeNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        super.removeOnContextMenuRequestedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnContextMenuRequestedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnDragDetectedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDetectedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragDoneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDoneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragDroppedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDroppedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        super.removeOnInputMethodTextChangedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnInputMethodTextChangedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyTypedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyTypedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseClickedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseClickedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseDraggedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDraggedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMousePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMousePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotationFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotationFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotationStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotationStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeDownChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeDownInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeLeftChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeLeftInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeRightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeRightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeUpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeUpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchStationaryChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchStationaryInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeOpacityChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeOpacityInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOpacityInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeParentChangeListener(ChangeListener<? super Parent> changeListener) {
        super.removeParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeParentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removePickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removePickOnBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removePickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.removePickOnBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removePressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        super.removeSceneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeSceneInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSceneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeRotateChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.removeRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        super.removeRotationAxisChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        super.removeRotationAxisInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeScaleXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeScaleYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeScaleZInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeStyleChangeListener(ChangeListener<? super String> changeListener) {
        super.removeStyleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeStyleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeStyleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.removeStyleListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeStyleListInvalidationListener(InvalidationListener invalidationListener) {
        super.removeStyleListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        super.removeTransformListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeTransformListInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTransformListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeViewOrderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        super.removeViewOrderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeVisibleInvalidationListener(invalidationListener);
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
    public RegionConfigurator bindAccessibleHelpProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleHelpProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindAccessibleHelpProperty() {
        super.unbindAccessibleHelpProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalAccessibleHelpProperty(Property<String> property) {
        super.bindBidirectionalAccessibleHelpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalAccessibleHelpProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleHelpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindAccessibleRoleDescriptionProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleRoleDescriptionProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindAccessibleRoleDescriptionProperty() {
        super.unbindAccessibleRoleDescriptionProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property) {
        super.bindBidirectionalAccessibleRoleDescriptionProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleRoleDescriptionProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindAccessibleRoleProperty(ObservableValue<? extends AccessibleRole> observableValue) {
        super.bindAccessibleRoleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindAccessibleRoleProperty() {
        super.unbindAccessibleRoleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property) {
        super.bindBidirectionalAccessibleRoleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property) {
        super.unbindBidirectionalAccessibleRoleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindAccessibleTextProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindAccessibleTextProperty() {
        super.unbindAccessibleTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalAccessibleTextProperty(Property<String> property) {
        super.bindBidirectionalAccessibleTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalAccessibleTextProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBlendModeProperty(ObservableValue<? extends BlendMode> observableValue) {
        super.bindBlendModeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBlendModeProperty() {
        super.unbindBlendModeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalBlendModeProperty(Property<BlendMode> property) {
        super.bindBidirectionalBlendModeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalBlendModeProperty(Property<BlendMode> property) {
        super.unbindBidirectionalBlendModeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindCacheHintProperty(ObservableValue<? extends CacheHint> observableValue) {
        super.bindCacheHintProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindCacheHintProperty() {
        super.unbindCacheHintProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalCacheHintProperty(Property<CacheHint> property) {
        super.bindBidirectionalCacheHintProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalCacheHintProperty(Property<CacheHint> property) {
        super.unbindBidirectionalCacheHintProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindCacheProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCacheProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindCacheProperty() {
        super.unbindCacheProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalCacheProperty(Property<Boolean> property) {
        super.bindBidirectionalCacheProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalCacheProperty(Property<Boolean> property) {
        super.unbindBidirectionalCacheProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindClipProperty(ObservableValue<? extends Node> observableValue) {
        super.bindClipProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindClipProperty() {
        super.unbindClipProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalClipProperty(Property<Node> property) {
        super.bindBidirectionalClipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalClipProperty(Property<Node> property) {
        super.unbindBidirectionalClipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindCursorProperty(ObservableValue<? extends Cursor> observableValue) {
        super.bindCursorProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindCursorProperty() {
        super.unbindCursorProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalCursorProperty(Property<Cursor> property) {
        super.bindBidirectionalCursorProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalCursorProperty(Property<Cursor> property) {
        super.unbindBidirectionalCursorProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindDepthTestProperty(ObservableValue<? extends DepthTest> observableValue) {
        super.bindDepthTestProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindDepthTestProperty() {
        super.unbindDepthTestProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalDepthTestProperty(Property<DepthTest> property) {
        super.bindBidirectionalDepthTestProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalDepthTestProperty(Property<DepthTest> property) {
        super.unbindBidirectionalDepthTestProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindDisableProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindDisableProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindDisableProperty() {
        super.unbindDisableProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalDisableProperty(Property<Boolean> property) {
        super.bindBidirectionalDisableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalDisableProperty(Property<Boolean> property) {
        super.unbindBidirectionalDisableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindEffectProperty(ObservableValue<? extends Effect> observableValue) {
        super.bindEffectProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindEffectProperty() {
        super.unbindEffectProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalEffectProperty(Property<Effect> property) {
        super.bindBidirectionalEffectProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalEffectProperty(Property<Effect> property) {
        super.unbindBidirectionalEffectProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindEventDispatcherProperty(ObservableValue<? extends EventDispatcher> observableValue) {
        super.bindEventDispatcherProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindEventDispatcherProperty() {
        super.unbindEventDispatcherProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property) {
        super.bindBidirectionalEventDispatcherProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property) {
        super.unbindBidirectionalEventDispatcherProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindFocusTraversableProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindFocusTraversableProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindFocusTraversableProperty() {
        super.unbindFocusTraversableProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalFocusTraversableProperty(Property<Boolean> property) {
        super.bindBidirectionalFocusTraversableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalFocusTraversableProperty(Property<Boolean> property) {
        super.unbindBidirectionalFocusTraversableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindIdProperty(ObservableValue<? extends String> observableValue) {
        super.bindIdProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindIdProperty() {
        super.unbindIdProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalIdProperty(Property<String> property) {
        super.bindBidirectionalIdProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalIdProperty(Property<String> property) {
        super.unbindBidirectionalIdProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindInputMethodRequestsProperty(ObservableValue<? extends InputMethodRequests> observableValue) {
        super.bindInputMethodRequestsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindInputMethodRequestsProperty() {
        super.unbindInputMethodRequestsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property) {
        super.bindBidirectionalInputMethodRequestsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property) {
        super.unbindBidirectionalInputMethodRequestsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindLayoutXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindLayoutXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindLayoutXProperty() {
        super.unbindLayoutXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalLayoutXProperty(Property<Number> property) {
        super.bindBidirectionalLayoutXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalLayoutXProperty(Property<Number> property) {
        super.unbindBidirectionalLayoutXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindLayoutYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindLayoutYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindLayoutYProperty() {
        super.unbindLayoutYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalLayoutYProperty(Property<Number> property) {
        super.bindBidirectionalLayoutYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalLayoutYProperty(Property<Number> property) {
        super.unbindBidirectionalLayoutYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindManagedProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindManagedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindManagedProperty() {
        super.unbindManagedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalManagedProperty(Property<Boolean> property) {
        super.bindBidirectionalManagedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalManagedProperty(Property<Boolean> property) {
        super.unbindBidirectionalManagedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindMouseTransparentProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindMouseTransparentProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindMouseTransparentProperty() {
        super.unbindMouseTransparentProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalMouseTransparentProperty(Property<Boolean> property) {
        super.bindBidirectionalMouseTransparentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalMouseTransparentProperty(Property<Boolean> property) {
        super.unbindBidirectionalMouseTransparentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindNodeOrientationProperty(ObservableValue<? extends NodeOrientation> observableValue) {
        super.bindNodeOrientationProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindNodeOrientationProperty() {
        super.unbindNodeOrientationProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property) {
        super.bindBidirectionalNodeOrientationProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property) {
        super.unbindBidirectionalNodeOrientationProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnContextMenuRequestedProperty(ObservableValue<? extends EventHandler<? super ContextMenuEvent>> observableValue) {
        super.bindOnContextMenuRequestedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnContextMenuRequestedProperty() {
        super.unbindOnContextMenuRequestedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> property) {
        super.bindBidirectionalOnContextMenuRequestedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> property) {
        super.unbindBidirectionalOnContextMenuRequestedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnDragDetectedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnDragDetectedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnDragDetectedProperty() {
        super.unbindOnDragDetectedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnDragDetectedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnDragDetectedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnDragDoneProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragDoneProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnDragDoneProperty() {
        super.unbindOnDragDoneProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragDoneProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragDoneProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnDragDroppedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragDroppedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnDragDroppedProperty() {
        super.unbindOnDragDroppedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragDroppedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragDroppedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnDragEnteredProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnDragEnteredProperty() {
        super.unbindOnDragEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnDragExitedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnDragExitedProperty() {
        super.unbindOnDragExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnDragOverProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragOverProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnDragOverProperty() {
        super.unbindOnDragOverProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnInputMethodTextChangedProperty(ObservableValue<? extends EventHandler<? super InputMethodEvent>> observableValue) {
        super.bindOnInputMethodTextChangedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnInputMethodTextChangedProperty() {
        super.unbindOnInputMethodTextChangedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> property) {
        super.bindBidirectionalOnInputMethodTextChangedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> property) {
        super.unbindBidirectionalOnInputMethodTextChangedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnKeyPressedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyPressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnKeyPressedProperty() {
        super.unbindOnKeyPressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnKeyReleasedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnKeyReleasedProperty() {
        super.unbindOnKeyReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnKeyTypedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyTypedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnKeyTypedProperty() {
        super.unbindOnKeyTypedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyTypedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyTypedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnMouseClickedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseClickedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnMouseClickedProperty() {
        super.unbindOnMouseClickedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseClickedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseClickedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnMouseDragEnteredProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnMouseDragEnteredProperty() {
        super.unbindOnMouseDragEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnMouseDragExitedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnMouseDragExitedProperty() {
        super.unbindOnMouseDragExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnMouseDraggedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseDraggedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnMouseDraggedProperty() {
        super.unbindOnMouseDraggedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseDraggedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseDraggedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnMouseDragOverProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragOverProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnMouseDragOverProperty() {
        super.unbindOnMouseDragOverProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnMouseDragReleasedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnMouseDragReleasedProperty() {
        super.unbindOnMouseDragReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnMouseEnteredProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnMouseEnteredProperty() {
        super.unbindOnMouseEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnMouseExitedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnMouseExitedProperty() {
        super.unbindOnMouseExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnMouseMovedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseMovedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnMouseMovedProperty() {
        super.unbindOnMouseMovedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnMousePressedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMousePressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnMousePressedProperty() {
        super.unbindOnMousePressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMousePressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMousePressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnMouseReleasedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnMouseReleasedProperty() {
        super.unbindOnMouseReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnRotateProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotateProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnRotateProperty() {
        super.unbindOnRotateProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnRotationFinishedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotationFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnRotationFinishedProperty() {
        super.unbindOnRotationFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotationFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotationFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnRotationStartedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotationStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnRotationStartedProperty() {
        super.unbindOnRotationStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotationStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotationStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnScrollProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnScrollProperty() {
        super.unbindOnScrollProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnScrollFinishedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnScrollFinishedProperty() {
        super.unbindOnScrollFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnScrollStartedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnScrollStartedProperty() {
        super.unbindOnScrollStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnSwipeDownProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeDownProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnSwipeDownProperty() {
        super.unbindOnSwipeDownProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeDownProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeDownProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnSwipeLeftProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeLeftProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnSwipeLeftProperty() {
        super.unbindOnSwipeLeftProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeLeftProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeLeftProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnSwipeRightProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeRightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnSwipeRightProperty() {
        super.unbindOnSwipeRightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeRightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeRightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnSwipeUpProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeUpProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnSwipeUpProperty() {
        super.unbindOnSwipeUpProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeUpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeUpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnTouchMovedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchMovedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnTouchMovedProperty() {
        super.unbindOnTouchMovedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnTouchPressedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchPressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnTouchPressedProperty() {
        super.unbindOnTouchPressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnTouchReleasedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnTouchReleasedProperty() {
        super.unbindOnTouchReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnTouchStationaryProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchStationaryProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnTouchStationaryProperty() {
        super.unbindOnTouchStationaryProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchStationaryProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchStationaryProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnZoomProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnZoomProperty() {
        super.unbindOnZoomProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnZoomFinishedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnZoomFinishedProperty() {
        super.unbindOnZoomFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOnZoomStartedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOnZoomStartedProperty() {
        super.unbindOnZoomStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindOpacityProperty(ObservableValue<? extends Number> observableValue) {
        super.bindOpacityProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindOpacityProperty() {
        super.unbindOpacityProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalOpacityProperty(Property<Number> property) {
        super.bindBidirectionalOpacityProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalOpacityProperty(Property<Number> property) {
        super.unbindBidirectionalOpacityProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindPickOnBoundsProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindPickOnBoundsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindPickOnBoundsProperty() {
        super.unbindPickOnBoundsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalPickOnBoundsProperty(Property<Boolean> property) {
        super.bindBidirectionalPickOnBoundsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalPickOnBoundsProperty(Property<Boolean> property) {
        super.unbindBidirectionalPickOnBoundsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindRotateProperty(ObservableValue<? extends Number> observableValue) {
        super.bindRotateProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindRotateProperty() {
        super.unbindRotateProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalRotateProperty(Property<Number> property) {
        super.bindBidirectionalRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalRotateProperty(Property<Number> property) {
        super.unbindBidirectionalRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindRotationAxisProperty(ObservableValue<? extends Point3D> observableValue) {
        super.bindRotationAxisProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindRotationAxisProperty() {
        super.unbindRotationAxisProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalRotationAxisProperty(Property<Point3D> property) {
        super.bindBidirectionalRotationAxisProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalRotationAxisProperty(Property<Point3D> property) {
        super.unbindBidirectionalRotationAxisProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindScaleXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindScaleXProperty() {
        super.unbindScaleXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalScaleXProperty(Property<Number> property) {
        super.bindBidirectionalScaleXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalScaleXProperty(Property<Number> property) {
        super.unbindBidirectionalScaleXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindScaleYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindScaleYProperty() {
        super.unbindScaleYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalScaleYProperty(Property<Number> property) {
        super.bindBidirectionalScaleYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalScaleYProperty(Property<Number> property) {
        super.unbindBidirectionalScaleYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindScaleZProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleZProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindScaleZProperty() {
        super.unbindScaleZProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalScaleZProperty(Property<Number> property) {
        super.bindBidirectionalScaleZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalScaleZProperty(Property<Number> property) {
        super.unbindBidirectionalScaleZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindStyleProperty(ObservableValue<? extends String> observableValue) {
        super.bindStyleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindStyleProperty() {
        super.unbindStyleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalStyleProperty(Property<String> property) {
        super.bindBidirectionalStyleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalStyleProperty(Property<String> property) {
        super.unbindBidirectionalStyleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindTranslateXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindTranslateXProperty() {
        super.unbindTranslateXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalTranslateXProperty(Property<Number> property) {
        super.bindBidirectionalTranslateXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalTranslateXProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindTranslateYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindTranslateYProperty() {
        super.unbindTranslateYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalTranslateYProperty(Property<Number> property) {
        super.bindBidirectionalTranslateYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalTranslateYProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindTranslateZProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateZProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindTranslateZProperty() {
        super.unbindTranslateZProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalTranslateZProperty(Property<Number> property) {
        super.bindBidirectionalTranslateZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalTranslateZProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindViewOrderProperty(ObservableValue<? extends Number> observableValue) {
        super.bindViewOrderProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindViewOrderProperty() {
        super.unbindViewOrderProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalViewOrderProperty(Property<Number> property) {
        super.bindBidirectionalViewOrderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalViewOrderProperty(Property<Number> property) {
        super.unbindBidirectionalViewOrderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindVisibleProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindVisibleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindVisibleProperty() {
        super.unbindVisibleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator bindBidirectionalVisibleProperty(Property<Boolean> property) {
        super.bindBidirectionalVisibleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator unbindBidirectionalVisibleProperty(Property<Boolean> property) {
        super.unbindBidirectionalVisibleProperty(property);
        return this;
    }

    //endregion Binding Functions

    //region Event Functions
    //*****************************************************************
    // Event Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator fireEvent(Event event) {
        super.fireEvent(event);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> RegionConfigurator addEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        super.addEventFilter(eventType, eventFilter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> RegionConfigurator addEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        super.addEventHandler(eventType, eventHandler);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> RegionConfigurator removeEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        super.removeEventFilter(eventType, eventFilter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> RegionConfigurator removeEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        super.removeEventHandler(eventType, eventHandler);
        return this;
    }

    //endregion Event Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setAccessibleHelp(String value) {
        super.setAccessibleHelp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setAccessibleRole(AccessibleRole value) {
        super.setAccessibleRole(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setAccessibleRoleDescription(String value) {
        super.setAccessibleRoleDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setAccessibleText(String value) {
        super.setAccessibleText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setBlendMode(BlendMode value) {
        super.setBlendMode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setCache(boolean value) {
        super.setCache(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setCacheHint(CacheHint value) {
        super.setCacheHint(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setClip(Node value) {
        super.setClip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setCursor(Cursor value) {
        super.setCursor(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setDepthTest(DepthTest value) {
        super.setDepthTest(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setDisable(boolean value) {
        super.setDisable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setEffect(Effect value) {
        super.setEffect(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setEventDispatcher(EventDispatcher value) {
        super.setEventDispatcher(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setFocusTraversable(boolean value) {
        super.setFocusTraversable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setId(String value) {
        super.setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setInputMethodRequests(InputMethodRequests value) {
        super.setInputMethodRequests(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setLayoutX(double value) {
        super.setLayoutX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setLayoutY(double value) {
        super.setLayoutY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setManaged(boolean value) {
        super.setManaged(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setMouseTransparent(boolean value) {
        super.setMouseTransparent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setNodeOrientation(NodeOrientation value) {
        super.setNodeOrientation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnContextMenuRequested(EventHandler<? super ContextMenuEvent> value) {
        super.setOnContextMenuRequested(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnDragDetected(EventHandler<? super MouseEvent> value) {
        super.setOnDragDetected(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnDragDone(EventHandler<? super DragEvent> value) {
        super.setOnDragDone(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnDragDropped(EventHandler<? super DragEvent> value) {
        super.setOnDragDropped(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnDragEntered(EventHandler<? super DragEvent> value) {
        super.setOnDragEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnDragExited(EventHandler<? super DragEvent> value) {
        super.setOnDragExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnDragOver(EventHandler<? super DragEvent> value) {
        super.setOnDragOver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnInputMethodTextChanged(EventHandler<? super InputMethodEvent> value) {
        super.setOnInputMethodTextChanged(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnKeyPressed(EventHandler<? super KeyEvent> value) {
        super.setOnKeyPressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnKeyTyped(EventHandler<? super KeyEvent> value) {
        super.setOnKeyTyped(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnMouseClicked(EventHandler<? super MouseEvent> value) {
        super.setOnMouseClicked(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnMouseDragEntered(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnMouseDragExited(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnMouseDragOver(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragOver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnMouseDragReleased(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnMouseEntered(EventHandler<? super MouseEvent> value) {
        super.setOnMouseEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnMouseExited(EventHandler<? super MouseEvent> value) {
        super.setOnMouseExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnMouseMoved(EventHandler<? super MouseEvent> value) {
        super.setOnMouseMoved(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnMousePressed(EventHandler<? super MouseEvent> value) {
        super.setOnMousePressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnMouseReleased(EventHandler<? super MouseEvent> value) {
        super.setOnMouseReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnRotate(EventHandler<? super RotateEvent> value) {
        super.setOnRotate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnRotationFinished(EventHandler<? super RotateEvent> value) {
        super.setOnRotationFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnRotationStarted(EventHandler<? super RotateEvent> value) {
        super.setOnRotationStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnScroll(EventHandler<? super ScrollEvent> value) {
        super.setOnScroll(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnScrollFinished(EventHandler<? super ScrollEvent> value) {
        super.setOnScrollFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnScrollStarted(EventHandler<? super ScrollEvent> value) {
        super.setOnScrollStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOpacity(double value) {
        super.setOpacity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setStyle(String style) {
        super.setStyle(style);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnSwipeDown(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeDown(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnSwipeLeft(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeLeft(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnSwipeRight(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeRight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnSwipeUp(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeUp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnTouchMoved(EventHandler<? super TouchEvent> value) {
        super.setOnTouchMoved(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnTouchPressed(EventHandler<? super TouchEvent> value) {
        super.setOnTouchPressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnTouchReleased(EventHandler<? super TouchEvent> value) {
        super.setOnTouchReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnTouchStationary(EventHandler<? super TouchEvent> value) {
        super.setOnTouchStationary(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnZoom(EventHandler<? super ZoomEvent> value) {
        super.setOnZoom(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnZoomFinished(EventHandler<? super ZoomEvent> value) {
        super.setOnZoomFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setOnZoomStarted(EventHandler<? super ZoomEvent> value) {
        super.setOnZoomStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setPickOnBounds(boolean value) {
        super.setPickOnBounds(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setRotate(double value) {
        super.setRotate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setRotationAxis(Point3D value) {
        super.setRotationAxis(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setScaleX(double value) {
        super.setScaleX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setScaleY(double value) {
        super.setScaleY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setScaleZ(double value) {
        super.setScaleZ(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setTranslateX(double value) {
        super.setTranslateX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setTranslateY(double value) {
        super.setTranslateY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setTranslateZ(double value) {
        super.setTranslateZ(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setUserData(Object value) {
        super.setUserData(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setViewOrder(double value) {
        super.setViewOrder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setVisible(boolean value) {
        super.setVisible(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setStyleClass(int index, String element) {
        super.setStyleClass(index, element);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setAllStyleClasses(String... elements) {
        super.setAllStyleClasses(elements);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setAllStyleClasses(Collection<? extends String> col) {
        super.setAllStyleClasses(col);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setTransformClass(int index, Transform element) {
        super.setTransformClass(index,element);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setAllTransformClasses(Transform... elements) {
        super.setAllTransformClasses(elements);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator setAllTransformClasses(Collection<? extends Transform> col) {
        super.setAllTransformClasses(col);
        return this;
    }

    //endregion Set Functions

    //region Add EFXStyle Functions
    //*****************************************************************
    // Add EFXStyle Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addFirstStyleClass(String styleClass) {
        super.addFirstStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLastStyleClass(String styleClass) {
        super.addLastStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addStyleClass(String styleClass) {
        super.addStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addStyleClass(int index, String styleClass) {
        super.addStyleClass(index, styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAllStyleClasses(String... styleClasses) {
        super.addAllStyleClasses(styleClasses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAllStyleClasses(Collection<? extends String> c) {
        super.addAllStyleClasses(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAllStyleClasses(int index, Collection<? extends String> c) {
        super.addAllStyleClasses(index, c);
        return this;
    }

    //endregion Add EFXStyle Functions

    //region Remove EFXStyle Functions
    //*****************************************************************
    // Remove EFXStyle Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeFirstStyleClass() {
        super.removeFirstStyleClass();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLastStyleClass() {
        super.removeLastStyleClass();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeStyleClass(String styleClass) {
        super.removeStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeStyleClasses(int from, int to) {
        super.removeStyleClasses(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeStyleClassesIf(Predicate<? super String> filter) {
        super.removeStyleClassesIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAllStyleClasses(String... styleClasses) {
        super.removeAllStyleClasses(styleClasses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAllStyleClasses(Collection<? extends String> c) {
        super.removeAllStyleClasses(c);
        return this;
    }

    //endregion Remove EFXStyle Functions

    //region EFXStyle Functions
    //*****************************************************************
    // EFXStyle Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator pseudoClassStateChange(PseudoClass pseudoClass, boolean active) {
        super.pseudoClassStateChange(pseudoClass, active);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator applyCss() {
        super.applyCss();
        return this;
    }

    //endregion EFXStyle Functions

    //region Add Transform Functions
    //*****************************************************************
    // Add Transform Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addFirstTransform(Transform transform) {
        super.addFirstTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLastTransform(Transform transform) {
        super.addLastTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addTransform(Transform transform) {
        super.addTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addTransform(int index, Transform transform) {
        super.addTransform(index, transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAllTransforms(Transform... transforms) {
        super.addAllTransforms(transforms);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAllTransforms(Collection<? extends Transform> c) {
        super.addAllTransforms(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAllTransforms(int index, Collection<? extends Transform> c) {
        super.addAllTransforms(index, c);
        return this;
    }

    //endregion Add Transform Functions

    //region Remove Transform Functions
    //*****************************************************************
    // Remove Transform Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeFirstTransform() {
        super.removeFirstTransform();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLastTransform() {
        super.removeLastTransform();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeTransform(Transform transform) {
        super.removeTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeTransforms(int from, int to) {
        super.removeTransforms(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeTransformsIf(Predicate<? super Transform> filter) {
        super.removeTransformsIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAllTransforms(Transform... transforms) {
        super.removeAllTransforms(transforms);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAllTransforms(Collection<? extends Transform> c) {
        super.removeAllTransforms(c);
        return this;
    }

    //endregion Remove Transform Functions

    //region Layout Functions
    //*****************************************************************
    // Layout Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator toBack() {
        super.toBack();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator toFront() {
        super.toFront();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator resize(double width, double height) {
        super.resize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator autosize() {
        super.autosize();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator resizeRelocate(double x, double y, double width, double height) {
        super.resizeRelocate(x, y, width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator requestFocus() {
        super.requestFocus();
        return this;
    }

    //endregion Layout Functions

    //endregion NodeConfig Functions

    //region ParentConfig Functions
    //*****************************************************************
    // ParentConfig Functions
    //*****************************************************************

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addNeedsLayoutChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        super.addNeedsLayoutInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        super.addGetChildrenUnmodifiableChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        super.addGetChildrenUnmodifiableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.addStylesheetsListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
        super.addStylesheetsListInvalidationListener(invalidationListener);
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
    public RegionConfigurator removeNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeNeedsLayoutChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        super.removeNeedsLayoutInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        super.removeGetChildrenUnmodifiableChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeGetChildrenUnmodifiableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.removeStylesheetsListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
        super.removeStylesheetsListInvalidationListener(invalidationListener);
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
    public RegionConfigurator requestLayout() {
        super.requestLayout();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator Layout() {
        super.Layout();
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
    public RegionConfigurator addFirstStylesheet(String stylesheet) {
        super.addFirstStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addLastStylesheet(String stylesheet) {
        super.addLastStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addStylesheet(String stylesheet) {
        super.addStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addStylesheet(int index, String stylesheet) {
        super.addStylesheet(index, stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAllStylesheets(String... stylesheets) {
        super.addAllStylesheets(stylesheets);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAllStylesheets(Collection<? extends String> c) {
        super.addAllStylesheets(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator addAllStylesheets(int index, Collection<? extends String> c) {
        super.addAllStylesheets(index, c);
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
    public RegionConfigurator removeFirstStylesheet() {
        super.removeFirstStylesheet();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeLastStylesheet() {
        super.removeLastStylesheet();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeStylesheet(String stylesheet) {
        super.removeStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeStylesheets(int from, int to) {
        super.removeStylesheets(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeStylesheetsIf(Predicate<? super String> filter) {
        super.removeStylesheetsIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAllStylesheets(String... stylesheets) {
        super.removeAllStylesheets(stylesheets);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator removeAllStylesheets(Collection<? extends String> c) {
        super.removeAllStylesheets(c);
        return this;
    }

    //endregion Remove Stylesheet Functions

    //endregion ParentConfig Functions
}
