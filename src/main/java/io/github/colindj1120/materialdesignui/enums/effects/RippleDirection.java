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

import io.github.colindj1120.materialdesignui.effects.ripple.RippleEffect;

/**
 * Defines the direction of the ripple effect in animations or UI interactions.
 * This enumeration is used to specify whether the ripple effect should move
 * inward towards the center of the UI element or outward from the center.
 *
 * <p>The ripple effect is commonly used in material design to provide feedback
 * for user actions. The direction can enhance the visual cue provided to users,
 * indicating the type of action or transition occurring.</p>
 *
 * <p><strong>Usage:</strong></p>
 * <ul>
 *     <li>{@code RippleDirection.IN}: Indicates that the ripple effect should
 *     start from the outer edges and converge towards the center of the element.</li>
 *     <li>{@code RippleDirection.OUT}: Indicates that the ripple effect should
 *     start from the center of the element and expand towards the edges.</li>
 * </ul>
 *
 * <p><strong>Example:</strong></p>
 * <pre>
 * RippleEffect rippleEffect = new RippleEffect();
 * rippleEffect.setRippleDirection(RippleDirection.OUT);
 * </pre>
 *
 * <p>In this example, a {@code RippleEffect} is created and configured to
 * use an outward ripple direction, expanding from the center towards the edges.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see RippleEffect
 */
public enum RippleDirection {
    IN,
    OUT
}
