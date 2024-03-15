package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.layout.RegionConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Region;
import javafx.scene.shape.Shape;

public interface RegionConfig<T extends RegionConfigurator<T>> extends ParentConfig<T> {
    @Override
    T getConfigurator();

    @Override
    Region getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    default T addSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().snapToPixelProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        getNode().snapToPixelProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().paddingProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addPaddingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().paddingProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        getNode().backgroundProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        getNode().backgroundProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addBorderChangeListener(ChangeListener<? super Border> changeListener) {
        getNode().borderProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addBorderInvalidationListener(InvalidationListener invalidationListener) {
        getNode().borderProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().opaqueInsetsProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().opaqueInsetsProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().insetsProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addInsetInvalidationListener(InvalidationListener invalidationListener) {
        getNode().insetsProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().widthProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().widthProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().minWidthProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().minWidthProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addPrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefWidthProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addPrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefWidthProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().maxWidthProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().maxWidthProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().heightProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().heightProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().minHeightProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().minHeightProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addPrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefHeightProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addPrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefHeightProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().maxHeightProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().maxHeightProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        getNode().shapeProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().shapeProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().scaleShapeProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleShapeProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().centerShapeProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().centerShapeProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().cacheShapeProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

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

    default T removeSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().snapToPixelProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        getNode().snapToPixelProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removePaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().paddingProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removePaddingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().paddingProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        getNode().backgroundProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        getNode().backgroundProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeBorderChangeListener(ChangeListener<? super Border> changeListener) {
        getNode().borderProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeBorderInvalidationListener(InvalidationListener invalidationListener) {
        getNode().borderProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().opaqueInsetsProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().opaqueInsetsProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().insetsProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeInsetInvalidationListener(InvalidationListener invalidationListener) {
        getNode().insetsProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().widthProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().widthProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().minWidthProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().minWidthProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removePrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefWidthProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removePrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefWidthProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().maxWidthProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().maxWidthProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().heightProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().heightProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().minHeightProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().minHeightProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removePrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefHeightProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removePrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefHeightProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().maxHeightProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().maxHeightProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        getNode().shapeProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().shapeProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().scaleShapeProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleShapeProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().centerShapeProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().centerShapeProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().cacheShapeProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

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

    default T bindSnapToPixelProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().snapToPixelProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindSnapToPixelProperty() {
        getNode().snapToPixelProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalSnapToPixelProperty(Property<Boolean> otherProperty) {
        getNode().snapToPixelProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalSnapToPixelProperty(Property<Boolean> otherProperty) {
        getNode().snapToPixelProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Padding Property

    default T bindPaddingProperty(ObservableValue<? extends Insets> observableValue) {
        getNode().paddingProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindPaddingProperty() {
        getNode().paddingProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalPaddingProperty(Property<Insets> otherProperty) {
        getNode().paddingProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalPaddingProperty(Property<Insets> otherProperty) {
        getNode().paddingProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Background Property

    default T bindBackgroundProperty(ObservableValue<? extends Background> observableValue) {
        getNode().backgroundProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindBackgroundProperty() {
        getNode().backgroundProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalBackgroundProperty(Property<Background> otherProperty) {
        getNode().backgroundProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalBackgroundProperty(Property<Background> otherProperty) {
        getNode().backgroundProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Border Property
    default T bindBorderProperty(ObservableValue<? extends Border> observableValue) {
        getNode().borderProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindBorderProperty() {
        getNode().borderProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalBorderProperty(Property<Border> otherProperty) {
        getNode().borderProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalBorderProperty(Property<Border> otherProperty) {
        getNode().borderProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Opaque Insets Property
    default T bindOpaqueInsetsProperty(ObservableValue<? extends Insets> observableValue) {
        getNode().opaqueInsetsProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOpaqueInsetsProperty() {
        getNode().opaqueInsetsProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOpaqueInsetsProperty(Property<Insets> otherProperty) {
        getNode().opaqueInsetsProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOpaqueInsetsProperty(Property<Insets> otherProperty) {
        getNode().opaqueInsetsProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Min Width Property
    default T bindMinWidthProperty(ObservableValue<? extends Number> observableValue) {
        getNode().minWidthProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindMinWidthProperty() {
        getNode().minWidthProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalMinWidthProperty(Property<Number> otherProperty) {
        getNode().minWidthProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalMinWidthProperty(Property<Number> otherProperty) {
        getNode().minWidthProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Pref Width Property
    default T bindPrefWidthProperty(ObservableValue<? extends Number> observableValue) {
        getNode().prefWidthProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindPrefWidthProperty() {
        getNode().prefWidthProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalPrefWidthProperty(Property<Number> otherProperty) {
        getNode().prefWidthProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalPrefWidthProperty(Property<Number> otherProperty) {
        getNode().prefWidthProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Max Width Property
    default T bindMaxWidthProperty(ObservableValue<? extends Number> observableValue) {
        getNode().maxWidthProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindMaxWidthProperty() {
        getNode().maxWidthProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalMaxWidthProperty(Property<Number> otherProperty) {
        getNode().maxWidthProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalMaxWidthProperty(Property<Number> otherProperty) {
        getNode().maxWidthProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Min Height Property
    default T bindMinHeightProperty(ObservableValue<? extends Number> observableValue) {
        getNode().minHeightProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindMinHeightProperty() {
        getNode().minHeightProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalMinHeightProperty(Property<Number> otherProperty) {
        getNode().minHeightProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalMinHeightProperty(Property<Number> otherProperty) {
        getNode().minHeightProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Pref Height Property
    default T bindPrefHeightProperty(ObservableValue<? extends Number> observableValue) {
        getNode().prefHeightProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindPrefHeightProperty() {
        getNode().prefHeightProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalPrefHeightProperty(Property<Number> otherProperty) {
        getNode().prefHeightProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalPrefHeightProperty(Property<Number> otherProperty) {
        getNode().prefHeightProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //Max Height Property
    default T bindMaxHeightProperty(ObservableValue<? extends Number> observableValue) {
        getNode().maxHeightProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindMaxHeightProperty() {
        getNode().maxHeightProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalMaxHeightProperty(Property<Number> otherProperty) {
        getNode().maxHeightProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalMaxHeightProperty(Property<Number> otherProperty) {
        getNode().maxHeightProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Shape Property
    default T bindShapeProperty(ObservableValue<? extends Shape> observableValue) {
        getNode().shapeProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindShapeProperty() {
        getNode().shapeProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalShapeProperty(Property<Shape> otherProperty) {
        getNode().shapeProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalShapeProperty(Property<Shape> otherProperty) {
        getNode().shapeProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Scale Shape Property
    default T bindScaleShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().scaleShapeProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindScaleShapeProperty() {
        getNode().scaleShapeProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalScaleShapeProperty(Property<Boolean> otherProperty) {
        getNode().scaleShapeProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalScaleShapeProperty(Property<Boolean> otherProperty) {
        getNode().scaleShapeProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Center Shape Property
    default T bindCenterShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().centerShapeProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindCenterShapeProperty() {
        getNode().centerShapeProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalCenterShapeProperty(Property<Boolean> otherProperty) {
        getNode().centerShapeProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalCenterShapeProperty(Property<Boolean> otherProperty) {
        getNode().centerShapeProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Cache Shape Property
    default T bindCacheShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().cacheShapeProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindCacheShapeProperty() {
        getNode().cacheShapeProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalCacheShapeProperty(Property<Boolean> otherProperty) {
        getNode().cacheShapeProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

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

    default T setSnapToPixel(boolean value) {
        getNode().setSnapToPixel(value);
        return getConfigurator();
    }

    default T setPadding(Insets value) {
        getNode().setPadding(value);
        return getConfigurator();
    }

    default T setBackground(Background value) {
        getNode().setBackground(value);
        return getConfigurator();
    }

    default T setBorder(Border value) {
        getNode().setBorder(value);
        return getConfigurator();
    }

    default T setOpaqueInsets(Insets value) {
        getNode().setOpaqueInsets(value);
        return getConfigurator();
    }

    default T setMinWidth(double value) {
        getNode().setMinWidth(value);
        return getConfigurator();
    }

    default T setPrefWidth(double value) {
        getNode().setPrefWidth(value);
        return getConfigurator();
    }

    default T setMaxWidth(double value) {
        getNode().setMaxWidth(value);
        return getConfigurator();
    }

    default T setMinHeight(double value) {
        getNode().setMinHeight(value);
        return getConfigurator();
    }

    default T setPrefHeight(double value) {
        getNode().setPrefHeight(value);
        return getConfigurator();
    }

    default T setMaxHeight(double value) {
        getNode().setMaxHeight(value);
        return getConfigurator();
    }

    default T setMinSize(double width, double height) {
        getNode().setMinSize(width, height);
        return getConfigurator();
    }

    default T setPrefSize(double width, double height) {
        getNode().setPrefSize(width, height);
        return getConfigurator();
    }

    default T setMaxSize(double width, double height) {
        getNode().setMaxSize(width, height);
        return getConfigurator();
    }

    default T setShape(Shape value) {
        getNode().setShape(value);
        return getConfigurator();
    }

    default T setScaleShape(boolean value) {
        getNode().setScaleShape(value);
        return getConfigurator();
    }

    default T setCenterShape(boolean value) {
        getNode().setCenterShape(value);
        return getConfigurator();
    }

    default T setCacheShape(boolean value) {
        getNode().setCacheShape(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
