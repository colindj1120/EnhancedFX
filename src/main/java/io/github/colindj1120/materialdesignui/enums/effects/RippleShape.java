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
package io.github.colindj1120.materialdesignui.enums.effects;

/**
 * Enumerates the possible shapes for a ripple effect. This enumeration is used to define the shape of the graphical ripple that appears in response to user interactions, such as mouse clicks or
 * touches, on a UI component.
 * <p>
 * The shape of the ripple effect can significantly influence the visual feedback provided to the user, enhancing the user experience by matching the contour of the UI component it is applied to.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public enum RippleShape {
    RECTANGLE,
    ROUNDED_RECTANGLE,
    CIRCLE,
    VERTICAL_ELLIPSE,
    HORIZONTAL_ELLIPSE
}

