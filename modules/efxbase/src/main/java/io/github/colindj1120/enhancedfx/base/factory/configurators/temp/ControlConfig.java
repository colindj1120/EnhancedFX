package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.ControlConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.Tooltip;

public interface ControlConfig<T extends ControlConfigurator<T>> extends RegionConfig<T> {
    @Override
    T getConfigurator();

    @Override
    Control getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    default T addSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        getNode().skinProperty().addListener(changeListener);
        return getConfigurator();
    }

    default T addSkinInvalidationListener(InvalidationListener invalidationListener) {
        getNode().skinProperty().addListener(invalidationListener);
        return getConfigurator();
    }

    default T addTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        getNode().tooltipProperty().addListener(changeListener);
        return getConfigurator();
    }

    default T addTooltipInvalidationListener(InvalidationListener invalidationListener) {
        getNode().tooltipProperty().addListener(invalidationListener);
        return getConfigurator();
    }

    default T addContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        getNode().contextMenuProperty().addListener(changeListener);
        return getConfigurator();
    }

    default T addContextMenuInvalidationListener(InvalidationListener invalidationListener) {
        getNode().contextMenuProperty().addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    default T removeSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        getNode().skinProperty().removeListener(changeListener);
        return getConfigurator();
    }

    default T removeSkinInvalidationListener(InvalidationListener invalidationListener) {
        getNode().skinProperty().removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        getNode().tooltipProperty().removeListener(changeListener);
        return getConfigurator();
    }

    default T removeTooltipInvalidationListener(InvalidationListener invalidationListener) {
        getNode().tooltipProperty().removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        getNode().contextMenuProperty().removeListener(changeListener);
        return getConfigurator();
    }

    default T removeContextMenuInvalidationListener(InvalidationListener invalidationListener) {
        getNode().contextMenuProperty().removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Skin Property

    default T bindSkinProperty(ObservableValue<? extends Skin<?>> observableValue) {
        getNode().skinProperty().bind(observableValue);
        return getConfigurator();
    }

    default T unbindSkinProperty() {
        getNode().skinProperty().unbind();
        return getConfigurator();
    }

    default T bindBidirectionalSkinProperty(Property<Skin<?>> property) {
        getNode().skinProperty().bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalSkinProperty(Property<Skin<?>> property) {
        getNode().skinProperty().unbindBidirectional(property);
        return getConfigurator();
    }

    // Tooltip Property

    default T bindTooltipProperty(ObservableValue<? extends Tooltip> observableValue) {
        getNode().tooltipProperty().bind(observableValue);
        return getConfigurator();
    }

    default T unbindTooltipProperty() {
        getNode().tooltipProperty().unbind();
        return getConfigurator();
    }

    default T bindBidirectionalTooltipProperty(Property<Tooltip> property) {
        getNode().tooltipProperty().bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalTooltipProperty(Property<Tooltip> property) {
        getNode().tooltipProperty().unbindBidirectional(property);
        return getConfigurator();
    }

    // Context Menu Property

    default T bindContextMenuProperty(ObservableValue<? extends ContextMenu> observableValue) {
        getNode().contextMenuProperty().bind(observableValue);
        return getConfigurator();
    }

    default T unbindContextMenuProperty() {
        getNode().contextMenuProperty().unbind();
        return getConfigurator();
    }

    default T bindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
        getNode().contextMenuProperty().bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
        getNode().contextMenuProperty().unbindBidirectional(property);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    default T setSkin(Skin<?> value) {
        getNode().setSkin(value);
        return getConfigurator();
    }

    default T setToolTip(Tooltip value) {
        getNode().setTooltip(value);
        return getConfigurator();
    }

    default T setContextMenu(ContextMenu value) {
        getNode().setContextMenu(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
