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
package io.github.colindj1120.materialdesignui.enums;

/**
 * Defines the operational status of a component or feature, indicating whether it is currently active and responsive to user interaction or inactive.
 * <p>
 * This enumeration is particularly useful for managing the state of UI components or features in an application, allowing for a straightforward way to toggle their availability or responsiveness
 * based on the application's logic or user permissions.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public enum Status {
    /**
     * Indicates that the component or feature is active and can interact with the user.
     *
     * <p>When a component or feature is enabled, it should be fully functional and responsive to user inputs.</p>
     */
    ENABLED,

    /**
     * Indicates that the component or feature is inactive and cannot interact with the user.
     *
     * <p>When a component or feature is disabled, it should not respond to any user inputs, and may be visually
     * distinct (e.g., grayed out) to communicate its state to the user or it could not be visible at all.</p>
     */
    DISABLED
}
