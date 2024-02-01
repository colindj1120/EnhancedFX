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
package io.github.colindj1120.materialdesignui.controls.base;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * The {@code TextFieldIcons} interface defines a set of functionalities for managing icons associated with a text field. This interface primarily focuses on handling events related to the leading and
 * trailing icons in the text field, enabling developers to easily add, remove, and manage event handlers and filters for these icons. It is particularly useful for enhancing text field interactions
 * in JavaFX user interfaces, such as adding click actions or hover effects on the icons.
 *
 * <p>Functionality:</p>
 * <ul>
 *   <li><b>Event Handling:</b> Methods to add, remove, and set event handlers for mouse and custom events
 *       on both leading and trailing icons. This allows for responsive and interactive icon behavior.</li>
 *   <li><b>Event Filtering:</b> Provides capabilities to add and remove event filters, enabling
 *       pre-processing of events before they reach the handler.</li>
 *   <li><b>Event Firing:</b> Allows for programmatic firing of events on the icons, useful for
 *       simulating user interactions or triggering actions based on other UI events.</li>
 *   <li><b>Icon Access:</b> Abstract methods to retrieve the labels used for the leading and trailing icons,
 *       offering customization and styling opportunities for the icons.</li>
 * </ul>
 *
 * <p>Usage:</p>
 * Implementing this interface in a text field control class enables the handling of events specific
 * to the icons within the text field. This interface abstracts the intricacies of event handling and
 * provides a structured approach to augmenting text field functionality with icon interactions.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see javafx.scene.control.Label
 * @see javafx.event.Event
 * @see javafx.event.EventHandler
 * @see javafx.event.EventType
 * @see javafx.scene.input.MouseEvent
 */
public interface TextFieldIcons {
    /**
     * Retrieves the label of the leading icon associated with this component.
     *
     * @return the label of the leading icon, or null if no leading icon is set
     */
    Label getLeadingIconLabel();

    /**
     * Returns the label of the trailing icon.
     *
     * @return the label of the trailing icon.
     */
    Label getTrailingIconLabel();

    /**
     * Adds an event handler to the leading icon in the text field.
     *
     * @param <T>
     *         the type of the event
     * @param eventType
     *         the type of the event to handle
     * @param eventHandler
     *         the event handler to add
     */
    default <T extends Event> void addLeadingIconEventHandler(EventType<T> eventType, EventHandler<? super T> eventHandler) {
        getLeadingIconLabel().addEventHandler(eventType, eventHandler);
    }

    /**
     * Adds an event handler to the trailing icon label for the specified event type.
     *
     * @param <T>
     *         the type of the event.
     * @param eventType
     *         the type of the event to handle.
     * @param eventHandler
     *         the event handler to handle the event.
     */
    default <T extends Event> void addTrailingIconEventHandler(EventType<T> eventType, EventHandler<? super T> eventHandler) {
        getTrailingIconLabel().addEventHandler(eventType, eventHandler);
    }

    /**
     * Adds an event filter for the leading icon label.
     *
     * @param <T>
     *         the type of the event
     * @param eventType
     *         the type of the event to filter
     * @param eventFilter
     *         the event filter to add
     */
    default <T extends Event> void addLeadingIconEventFilter(EventType<T> eventType, EventHandler<? super T> eventFilter) {
        getLeadingIconLabel().addEventFilter(eventType, eventFilter);
    }

    /**
     * Adds an event filter to the trailing icon in the text field. This event filter will be triggered when the specified event type occurs on the trailing icon.
     *
     * @param eventType
     *         the type of event to filter
     * @param eventFilter
     *         the event filter to apply
     * @param <T>
     *         the type of event
     */
    default <T extends Event> void addTrailingIconEventFilter(EventType<T> eventType, EventHandler<? super T> eventFilter) {
        getTrailingIconLabel().addEventFilter(eventType, eventFilter);
    }

    /**
     * Removes the specified event handler for the given event type from the leading icon label.
     *
     * @param eventType
     *         the type of the event
     * @param eventHandler
     *         the event handler to be removed
     * @param <T>
     *         the type of the event
     */
    default <T extends Event> void removeLeadingIconEventHandler(EventType<T> eventType, EventHandler<? super T> eventHandler) {
        getLeadingIconLabel().removeEventHandler(eventType, eventHandler);
    }

    /**
     * Removes the event handler for the specified event type from the trailing icon label.
     *
     * @param eventType
     *         the type of the event
     * @param eventHandler
     *         the event handler to be removed
     * @param <T>
     *         the type of the event
     */
    default <T extends Event> void removeTrailingIconEventHandler(EventType<T> eventType, EventHandler<? super T> eventHandler) {
        getTrailingIconLabel().removeEventHandler(eventType, eventHandler);
    }

    /**
     * Removes an event filter from the leading icon label of a text field associated with this class.
     *
     * @param eventType
     *         the type of event to remove the event filter for
     * @param eventFilter
     *         the event filter to be removed
     * @param <T>
     *         the type of event
     */
    default <T extends Event> void removeLeadingIconEventFilter(EventType<T> eventType, EventHandler<? super T> eventFilter) {
        getLeadingIconLabel().removeEventFilter(eventType, eventFilter);
    }

    /**
     * Removes an event filter for a specific event type from the trailing icon label.
     *
     * @param eventType
     *         the type of the event to remove the filter for
     * @param eventFilter
     *         the event filter to be removed
     * @param <T>
     *         the type of the event
     */
    default <T extends Event> void removeTrailingIconEventFilter(EventType<T> eventType, EventHandler<? super T> eventFilter) {
        getTrailingIconLabel().removeEventFilter(eventType, eventFilter);
    }

    /**
     * Sets an event handler for the mouse clicked event on the leading icon label.
     *
     * @param eventHandler
     *         the event handler to be set
     */
    default void setLeadingIconOnClicked(EventHandler<? super MouseEvent> eventHandler) {
        getLeadingIconLabel().setOnMouseClicked(eventHandler);
    }

    /**
     * Sets an event handler for the trailing icon's mouse clicked event.
     *
     * @param eventHandler
     *         the event handler to be set for the trailing icon's mouse-clicked event
     */
    default void setTrailingIconOnClicked(EventHandler<? super MouseEvent> eventHandler) {
        getTrailingIconLabel().setOnMouseClicked(eventHandler);
    }

    /**
     * Fires the given event on the leading icon label.
     *
     * @param event
     *         the event to be fired
     */
    default void leadingIconFireEvent(Event event) {
        getLeadingIconLabel().fireEvent(event);
    }

    /**
     * Fires the specified event on the trailing icon label.
     *
     * @param event
     *         the event to be fired
     */
    default void trailingIconFireEvent(Event event) {
        getTrailingIconLabel().fireEvent(event);
    }
}
