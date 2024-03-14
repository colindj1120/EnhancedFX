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
package io.github.colindj1120.enhancedfx.utils;

import javafx.geometry.Insets;
import javafx.scene.control.Control;

/**
 * This utility class provides a collection of static methods to create {@link Insets} objects for JavaFX UI components. The class
 * simplifies the process of defining padding for UI components by offering a variety of methods to specify padding on different
 * sides (top, bottom, right, left) individually, in combinations, or uniformly.
 *
 * <p>The utility methods cover scenarios including uniform padding across all sides, separate horizontal and vertical padding,
 * and individual padding values for each side. This class aims to enhance
 * readability and maintainability of code when setting padding in JavaFX layouts.</p>
 *
 * <p>Methods included:</p>
 * <ul>
 *   <li>Uniform padding on all sides</li>
 *   <li>Separate padding values for top, right, bottom, and left sides</li>
 *   <li>Combined padding for adjacent sides (e.g., top and right)</li>
 *   <li>Horizontal and vertical padding</li>
 *   <li>Method to return an empty {@link Insets} object</li>
 *   <li>Separate methods to return the top, bottom, left, and right insets of a {@link Control}</li>
 *   <li>Separate methods to return the borders top, bottom, left, and right insets of a {@link Control}</li>
 *   <li>Separate methods to return the top, bottom, left and right insets including the borders or a {@link Control}</li>
 * </ul>
 *
 * <p>This class is part of a suite of utility classes in the EnhancedFX package and is designed
 * to be used statically to create {@link Insets} objects wherever needed in the application.</p>
 *
 * <p>Example Usage:</p>
 * <pre>
 *   VBox vbox = new VBox();
 *   vbox.setPadding(InsetsUtils.uniform(10)); // sets uniform padding of 10 on all sides
 * </pre>
 *
 * @author Colin Jokisch
 * @version 1.0
 * @see Insets
 */
public class InsetUtils {
    /**
     * Private constructor to prevent instantiation of the {@code InsetUtils} class.
     *
     * <p>
     * This utility class is designed to provide static methods and should not be instantiated or extended. The private constructor enforces this design pattern by prohibiting instantiation of
     * {@code InsetUtils}.
     * </p>
     */
    private InsetUtils() {}

    /**
     * Creates an Insets object with uniform padding on all sides.
     *
     * @param padding
     *         The padding value for all sides.
     *
     * @return Insets with the specified uniform padding.
     */
    public static Insets uniform(double padding) {
        return new Insets(padding);
    }

    /**
     * Returns an Insets object representing empty padding on all sides.
     *
     * @return An Insets object with empty padding on all sides.
     */
    public static Insets empty() {
        return Insets.EMPTY;
    }

    /**
     * Creates an Insets object with the specified top padding.
     *
     * @param top
     *         The padding value for the top side.
     *
     * @return insets object with the specified top padding.
     */
    public static Insets top(double top) {
        return new Insets(top, 0, 0, 0);
    }

    /**
     * Creates an Insets object with the specified padding value for the right side.
     *
     * @param right
     *         The padding value for the right side.
     *
     * @return Insets with the specified padding value for the right side.
     */
    public static Insets right(double right) {
        return new Insets(0, right, 0, 0);
    }

    /**
     * Creates an Insets object with the specified bottom padding.
     *
     * @param bottom
     *         The padding value for the bottom side.
     *
     * @return Insets with the specified bottom padding.
     */
    public static Insets bottom(double bottom) {
        return new Insets(0, 0, bottom, 0);
    }

    /**
     * Creates an Insets object with the specified left padding.
     *
     * @param left
     *         The padding value for the left side.
     *
     * @return Insets with the specified left padding.
     */
    public static Insets left(double left) {
        return new Insets(0, 0, 0, left);
    }

    /**
     * Creates an Insets object with the specified top and right padding.
     *
     * @param top
     *         The padding value for the top side.
     * @param right
     *         The padding value for the right side.
     *
     * @return Insets object with the specified top and right padding.
     */
    public static Insets topRight(double top, double right) {
        return new Insets(top, right, 0, 0);
    }

    /**
     * Creates an Insets object with the specified value for top and left padding.
     *
     * @param top
     *         The value for top padding.
     * @param left
     *         The value for left padding.
     *
     * @return Insets with the specified top and left padding.
     */
    public static Insets topLeft(double top, double left) {
        return new Insets(top, 0, 0, left);
    }

    /**
     * Creates an Insets object with the specified padding values for the bottom and right sides.
     *
     * @param bottom
     *         The padding value for the bottom side.
     * @param right
     *         The padding value for the right side.
     *
     * @return Insets with the specified padding values for the bottom and right sides.
     */
    public static Insets bottomRight(double bottom, double right) {
        return new Insets(0, right, bottom, 0);
    }

    /**
     * Creates an Insets object with the specified bottom and left padding.
     *
     * @param bottom
     *         The padding value for the bottom.
     * @param left
     *         The padding value for the left.
     *
     * @return Insets with the specified bottom and left padding.
     */
    public static Insets bottomLeft(double bottom, double left) {
        return new Insets(0, 0, bottom, left);
    }

    /**
     * Creates an Insets object with the given top, right, and left padding.
     *
     * @param top
     *         The padding for the top side.
     * @param right
     *         The padding for the right side.
     * @param left
     *         The padding for the left side.
     *
     * @return Insets object with the specified top, right, and left padding.
     */
    public static Insets topRightLeft(double top, double right, double left) {
        return new Insets(top, right, 0, left);
    }

    /**
     * Creates an Insets object with the specified padding values for the top, right, and bottom sides.
     *
     * @param top
     *         The padding value for the top side.
     * @param right
     *         The padding value for the right side.
     * @param bottom
     *         The padding value for the bottom side.
     *
     * @return Insets with the specified padding values.
     */
    public static Insets topRightBottom(double top, double right, double bottom) {
        return new Insets(top, right, bottom, 0);
    }

    /**
     * Creates an Insets object with the specified top, left and bottom padding.
     *
     * @param top
     *         The padding value for the top side.
     * @param left
     *         The padding value for the left side.
     * @param bottom
     *         The padding value for the bottom side.
     *
     * @return Insets with the specified top, left and bottom padding.
     */
    public static Insets topLeftBottom(double top, double left, double bottom) {
        return new Insets(top, 0, bottom, left);
    }

    /**
     * Creates an Insets object with padding values for the bottom, right, and left sides.
     *
     * @param bottom
     *         The padding value for the bottom side.
     * @param right
     *         The padding value for the right side.
     * @param left
     *         The padding value for the left side.
     *
     * @return Insets with the specified padding values.
     */
    public static Insets bottomRightLeft(double bottom, double right, double left) {
        return new Insets(0, right, bottom, left);
    }

    /**
     * Creates an Insets object with padding specified for all sides.
     *
     * @param top
     *         The padding value for the top side.
     * @param right
     *         The padding value for the right side.
     * @param bottom
     *         The padding value for the bottom side.
     * @param left
     *         The padding value for the left side.
     *
     * @return Insets with the specified padding for all sides.
     */
    public static Insets all(double top, double right, double bottom, double left) {
        return new Insets(top, right, bottom, left);
    }

    /**
     * Creates an Insets object with the specified horizontal padding.
     *
     * @param horizontal
     *         The horizontal padding (applied to left and right).
     *
     * @return Insets with the specified horizontal padding.
     */
    public static Insets horizontal(double horizontal) {
        return new Insets(0, horizontal, 0, horizontal);
    }

    /**
     * Creates an Insets object with vertical padding.
     *
     * @param vertical
     *         The vertical padding applied to top and bottom sides.
     *
     * @return Insets with the specified vertical padding.
     */
    public static Insets vertical(double vertical) {
        return new Insets(vertical, 0, vertical, 0);
    }

    /**
     * Creates an Insets object with specified horizontal and vertical padding.
     *
     * @param vertical
     *         The vertical padding (applied to top and bottom).
     * @param horizontal
     *         The horizontal padding (applied to left and right).
     *
     * @return Insets with the specified horizontal and vertical padding.
     */
    public static Insets verticalHorizontal(double vertical, double horizontal) {
        return new Insets(vertical, horizontal, vertical, horizontal);
    }

    /**
     * Returns the top inset of the specified {@code Control} node.
     *
     * @param node
     *         The {@code Control} node to retrieve the top inset from.
     *
     * @return The top inset of the {@code Control} node.
     */
    public static double getTopInset(Control node) {
        return node.getPadding()
                   .getTop();
    }

    /**
     * Returns the bottom inset of the specified Control node.
     *
     * @param node
     *         The Control node to retrieve the bottom inset from.
     *
     * @return The bottom inset of the Control node.
     */
    public static double getBottomInset(Control node) {
        return node.getPadding()
                   .getBottom();
    }

    /**
     * Returns the left inset value of the specified Control node.
     *
     * @param node
     *         The Control node to retrieve the left inset value from.
     *
     * @return The left inset value of the Control node.
     */
    public static double getLeftInset(Control node) {
        return node.getPadding()
                   .getLeft();
    }

    /**
     * Returns the right inset value of the specified Control node.
     *
     * @param node
     *         The Control node to retrieve the right inset from.
     *
     * @return The right inset value of the Control node.
     */
    public static double getRightInset(Control node) {
        return node.getPadding()
                   .getRight();
    }

    /**
     * Returns the top border inset of the specified Control node.
     *
     * @param node
     *         The Control node to retrieve the top border inset from.
     *
     * @return The top border inset of the Control node.
     */
    public static double getTopBorderInset(Control node) {
        return node.getBorder()
                   .getInsets()
                   .getTop();
    }

    /**
     * Returns the bottom border inset of the specified {@code Control} node.
     *
     * @param node
     *         The {@code Control} node to retrieve the bottom border inset from.
     *
     * @return The bottom border inset of the {@code Control} node.
     */
    public static double getBottomBorderInset(Control node) {
        return node.getBorder()
                   .getInsets()
                   .getBottom();
    }

    /**
     * Returns the inset value for the left border of a control.
     *
     * @param node
     *         The control for which to get the left border inset.
     *
     * @return The inset value for the left border.
     */
    public static double getLeftBorderInset(Control node) {
        return node.getBorder()
                   .getInsets()
                   .getLeft();
    }

    /**
     * Returns the right border inset of the specified control node.
     *
     * @param node
     *         The control node for which to get the right border inset.
     *
     * @return The right border inset of the control node.
     */
    public static double getRightBorderInset(Control node) {
        return node.getBorder()
                   .getInsets()
                   .getRight();
    }

    /**
     * Calculates the top inset of a given {@code Control} node, including the border.
     *
     * @param node
     *         The {@code Control} node to calculate the top inset with border for.
     *
     * @return The top inset of the {@code Control} node, including the border.
     */
    public static double getTopInsetWithBorder(Control node) {
        return getTopInset(node) + getTopBorderInset(node);
    }

    /**
     * Returns the bottom inset value of the specified Control node, including any bottom border insets.
     *
     * @param node
     *         The Control node to retrieve the bottom inset value from.
     *
     * @return The bottom inset value of the Control node, including any bottom border insets.
     */
    public static double getBottomInsetWithBorder(Control node) {
        return getBottomInset(node) + getBottomBorderInset(node);
    }

    /**
     * Calculates the left inset of the specified Control node, including the left border inset.
     *
     * @param node
     *         The Control node to retrieve the left inset from.
     *
     * @return The left inset of the Control node including the left border inset.
     */
    public static double getLeftInsetWithBorder(Control node) {
        return getLeftInset(node) + getLeftBorderInset(node);
    }

    /**
     * Returns the right inset of the specified {@code Control} node, including any border.
     *
     * @param node
     *         The {@code Control} node to retrieve the right inset from.
     *
     * @return The right inset of the {@code Control} node.
     */
    public static double getRightInsetWithBorder(Control node) {
        return getRightInset(node) + getRightBorderInset(node);
    }
}
